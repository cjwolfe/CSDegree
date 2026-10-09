package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class FlexLauncherEditor extends JFrame {
    private String configPath;
    private final Map<String, String> generalSettings = new LinkedHashMap<>();
    private final Map<String, DefaultTableModel> menuModels = new LinkedHashMap<>();
    private final Map<String, JComponent> fieldMap = new HashMap<>();
    private final DefaultComboBoxModel<String> menuListModel = new DefaultComboBoxModel<>();
    
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JList<String> sidebarList;
    private JComboBox<String> defaultMenuCombo;

    public FlexLauncherEditor(String path) {
        this.configPath = path;
        setTitle("FlexLauncherEditor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadConfig();

        sidebarList = new JList<>(menuListModel);
        sidebarList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sidebarList.setPreferredSize(new Dimension(240, 0));
        sidebarList.setBorder(BorderFactory.createEtchedBorder());

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        setupAllMenus();

        sidebarList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cardLayout.show(contentPanel, sidebarList.getSelectedValue());
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(sidebarList), contentPanel);
        add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(240); // Matches the new sidebar width

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveBtn = new JButton("Save Configuration");
        saveBtn.addActionListener(e -> saveConfig());
        footer.add(saveBtn);
        add(footer, BorderLayout.SOUTH);

        setSize(1100, 750);
        setLocationRelativeTo(null);
        if (menuListModel.getSize() > 0) sidebarList.setSelectedIndex(0);
    }

    private void setupAllMenus() {
        contentPanel.add(createGeneralPanel(), "General");
        contentPanel.add(createVisualsPanel(), "Background");

        contentPanel.add(createSimpleTab("Layout", new String[][]{{"MaxButtons", "Integer"}, {"IconSize", "Integer"}, {"IconSpacing", "String"}, {"VCenter", "String"}}), "Layout");
        contentPanel.add(createSimpleTab("Titles", new String[][]{{"Enabled", "Boolean"}, {"Font", "String"}, {"FontSize", "Integer"}, {"Color", "String"}, {"Opacity", "String"}}), "Titles");
        contentPanel.add(createSimpleTab("Highlight", new String[][]{{"Enabled", "Boolean"}, {"FillColor", "String"}, {"FillOpacity", "String"}, {"OutlineSize", "Integer"}}), "Highlight");
        contentPanel.add(createSimpleTab("Scroll Indicators", new String[][]{{"Enabled", "Boolean"}, {"FillColor", "String"}}), "Scroll Indicators");
        contentPanel.add(createSimpleTab("Clock", new String[][]{{"Enabled", "Boolean"}, {"ShowDate", "Boolean"}, {"FontSize", "Integer"}}), "Clock");
        contentPanel.add(createSimpleTab("Screensaver", new String[][]{{"Enabled", "Boolean"}, {"IdleTime", "Integer"}}), "Screensaver");
        contentPanel.add(createSimpleTab("Hotkeys", new String[][]{{"Hotkey1", "String"}}), "Hotkeys");
        contentPanel.add(createSimpleTab("Gamepad", new String[][]{{"Enabled", "Boolean"}, {"DeviceIndex", "Integer"}}), "Gamepad");

        refreshMenuCards();
    }

    private void refreshMenuCards() {
        for (int i = 0; i < menuListModel.getSize(); i++) {
            String mName = menuListModel.getElementAt(i);
            if (!isDefaultSection(mName) && menuModels.containsKey(mName)) {
                contentPanel.add(createMenuEditor(mName), mName);
            }
        }
    }

    private JPanel createGeneralPanel() {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 5));
        p.setBorder(BorderFactory.createTitledBorder("General Settings"));
        p.add(new JLabel("DefaultMenu:"));
        defaultMenuCombo = new JComboBox<>(menuListModel);
        defaultMenuCombo.setSelectedItem(generalSettings.getOrDefault("DefaultMenu", "Main"));
        p.add(defaultMenuCombo);
        addSetting(p, "VSync", "Boolean");
        addSetting(p, "FPSLimit", "Integer");
        addSetting(p, "ApplicationTimeout", "Integer");
        return wrapInScroll(p);
    }

    private JPanel createVisualsPanel() {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 5));
        p.setBorder(BorderFactory.createTitledBorder("Background Settings"));
        p.add(new JLabel("Mode:"));
        JComboBox<String> modeCombo = new JComboBox<>(new String[]{"Color", "Image", "Slideshow", "Transparent"});
        modeCombo.setSelectedItem(generalSettings.getOrDefault("Mode", "Image"));
        fieldMap.put("Mode", modeCombo);
        p.add(modeCombo);
        
        p.add(new JLabel("Color:"));
        JButton colorBtn = new JButton(generalSettings.getOrDefault("Color", "#000000"));
        colorBtn.setBackground(decodeHex(colorBtn.getText()));
        colorBtn.addActionListener(e -> {
            Color s = JColorChooser.showDialog(this, "Select Color", decodeHex(colorBtn.getText()));
            if (s != null) {
                String hex = String.format("#%02x%02x%02x", s.getRed(), s.getGreen(), s.getBlue()).toUpperCase();
                colorBtn.setText(hex); colorBtn.setBackground(s);
            }
        });
        fieldMap.put("Color", colorBtn);
        p.add(colorBtn);
        addSetting(p, "Image", "String");
        return wrapInScroll(p);
    }

    private JPanel createMenuEditor(String menuName) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(menuName + " Configuration"));
        JTable table = new JTable(menuModels.get(menuName));
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel btns = new JPanel();
        JButton add = new JButton("Add Entry");
        add.addActionListener(e -> menuModels.get(menuName).addRow(new Object[]{"", "New Entry", "", ""}));
        JButton del = new JButton("Remove Selected");
        del.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            for(int i=rows.length-1; i>=0; i--) menuModels.get(menuName).removeRow(rows[i]);
        });
        JButton sub = new JButton("Add Submenu");
        sub.addActionListener(e -> createNewSubmenu());
        btns.add(add); btns.add(del); btns.add(sub);
        p.add(btns, BorderLayout.SOUTH);
        return p;
    }

    private void createNewSubmenu() {
        String name = JOptionPane.showInputDialog(this, "Submenu Name:");
        if (name != null && !name.trim().isEmpty() && !menuModels.containsKey(name)) {
            menuModels.put(name, createNewTableModel());
            menuListModel.addElement(name);
            refreshMenuCards();
        }
    }

    private void loadConfig() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(configPath));
            String[] defaults = {"General", "Background", "Layout", "Titles", "Highlight", "Scroll Indicators", "Clock", "Screensaver", "Hotkeys", "Gamepad"};
            for(String s : defaults) menuListModel.addElement(s);

            String currentSection = "General";
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.substring(1, line.length() - 1);
                    if (!menuModels.containsKey(currentSection) && !isDefaultSection(currentSection)) {
                        menuModels.put(currentSection, createNewTableModel());
                        menuListModel.addElement(currentSection);
                    } else if ((currentSection.equals("Main") || currentSection.equals("System")) && !menuModels.containsKey(currentSection)) {
                        menuModels.put(currentSection, createNewTableModel());
                    }
                    continue;
                }
                if (line.contains("=") && !line.startsWith("#")) {
                    String[] p = line.split("=", 2);
                    String key = p[0].trim();
                    String val = p[1].trim();
                    if (key.startsWith("Entry") && menuModels.containsKey(currentSection)) {
                        String[] d = val.split(";");
                        if(d.length >= 3) menuModels.get(currentSection).addRow(new Object[]{key, d[0], d[1], d[2]});
                    } else {
                        generalSettings.put(key, val);
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void saveConfig() {
        try {
            List<String> inputLines = Files.readAllLines(Paths.get(configPath));
            List<String> output = new ArrayList<>();
            String curSec = "General";
            Set<String> writtenMenus = new HashSet<>();

            for (String line : inputLines) {
                String t = line.trim();
                if (t.startsWith("[") && t.endsWith("]")) {
                    curSec = t.substring(1, t.length()-1);
                    output.add(line); continue;
                }
                if (t.startsWith("#") || t.isEmpty()) { output.add(line); continue; }
                
                if (t.contains("=") && !t.startsWith("Entry")) {
                    String k = t.split("=")[0].trim();
                    if(k.equals("DefaultMenu")) output.add("DefaultMenu=" + defaultMenuCombo.getSelectedItem());
                    else if(fieldMap.containsKey(k)) output.add(k + "=" + getVal(fieldMap.get(k)));
                    else output.add(line);
                } else if (t.startsWith("Entry") && !writtenMenus.contains(curSec)) {
                    DefaultTableModel m = menuModels.get(curSec);
                    if(m != null) {
                        for(int i=0; i<m.getRowCount(); i++) 
                            output.add("Entry"+(i+1)+"="+m.getValueAt(i,1)+";"+m.getValueAt(i,2)+";"+m.getValueAt(i,3));
                    }
                    writtenMenus.add(curSec);
                }
            }
            Files.write(Paths.get(configPath), output);
            JOptionPane.showMessageDialog(this, "Saved Successfully.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private boolean isDefaultSection(String s) { 
        return List.of("General", "Background", "Layout", "Titles", "Highlight", "Scroll Indicators", "Clock", "Screensaver", "Hotkeys", "Gamepad").contains(s); 
    }

    private String getVal(JComponent c) {
        if(c instanceof JCheckBox) return String.valueOf(((JCheckBox)c).isSelected());
        if(c instanceof JSpinner) return String.valueOf(((JSpinner)c).getValue());
        if(c instanceof JComboBox) return (String)((JComboBox<?>)c).getSelectedItem();
        if(c instanceof JButton) return ((JButton)c).getText();
        return ((JTextField)c).getText();
    }

    private JPanel createSimpleTab(String title, String[][] s) {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 5));
        p.setBorder(BorderFactory.createTitledBorder(title));
        for(String[] set : s) addSetting(p, set[0], set[1]);
        return wrapInScroll(p);
    }

    private void addSetting(JPanel p, String k, String t) {
        p.add(new JLabel(k + ":"));
        String v = generalSettings.getOrDefault(k, "");
        JComponent c;
        if (t.equals("Boolean")) {
            c = new JCheckBox("", v.equalsIgnoreCase("true"));
        } else if (t.equals("Integer")) {
            try {
                // Only use a Spinner if the value is a clean integer
                int parsed = Integer.parseInt(v.replaceAll("[^0-9-]", ""));
                c = new JSpinner(new SpinnerNumberModel(parsed, 0, 9999, 1));
            } catch (Exception e) {
                // Fallback to text for percentages or empty values
                c = new JTextField(v);
            }
        } else {
            c = new JTextField(v);
        }
        fieldMap.put(k, c); 
        p.add(c);
    }

    private JPanel wrapInScroll(JPanel p) { return new JPanel(new BorderLayout()){{add(p, BorderLayout.NORTH);}}; }
    private DefaultTableModel createNewTableModel() { return new DefaultTableModel(new String[]{"ID", "Name", "Icon", "Cmd"}, 0); }
    private Color decodeHex(String h) { try { return Color.decode(h); } catch(Exception e) { return Color.BLACK; } }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            File f = new File("config.ini");
            String p = null;
            if(f.exists() && JOptionPane.showConfirmDialog(null, "Use local config.ini?", "FlexLauncherEditor", 0) == 0) p = f.getAbsolutePath();
            else {
                JFileChooser c = new JFileChooser(new File("."));
                if(c.showOpenDialog(null) == 0) p = c.getSelectedFile().getAbsolutePath();
            }
            if(p != null) {
                FlexLauncherEditor editor = new FlexLauncherEditor(p);
                editor.setVisible(true);
                editor.toFront();
            }
        });
    }
}