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
    private DefaultComboBoxModel<String> menuListModel = new DefaultComboBoxModel<>();
    private JComboBox<String> defaultMenuCombo;
    private JTable menuTable;
    private String activeMenu = "Main";

    public FlexLauncherEditor(String path) {
        this.configPath = path;
        setTitle("FlexLauncherEditor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadConfig(); 

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("General Settings", createGeneralTab());
        tabs.addTab("Background and Layout", createVisualsTab());
        tabs.addTab("Titles and Widgets", createTab(new String[][]{
            {"Enabled", "Boolean"}, {"Font", "String"}, {"FontSize", "Integer"},
            {"ClockEnabled", "Boolean"}, {"ShowDate", "Boolean"}, {"IdleTime", "Integer"}
        }));
        tabs.addTab("Menu Configurations", createMenuManagerTab());

        add(tabs, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton saveBtn = new JButton("Save Configuration");
        saveBtn.addActionListener(e -> saveConfig());
        footer.add(saveBtn);
        add(footer, BorderLayout.SOUTH);

        setSize(850, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createMiscTab(){
        JPanel p = new JPanel(new GridLayout(0,2,10,5));
        

        return wrapInPanel(p);
    }

    private JPanel createGeneralTab() {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        p.add(new JLabel("DefaultMenu:"));
        defaultMenuCombo = new JComboBox<>(menuListModel);
        defaultMenuCombo.setSelectedItem(generalSettings.getOrDefault("DefaultMenu", "Main"));
        p.add(defaultMenuCombo);

        String[][] otherSettings = {
            {"VSync", "Boolean"}, {"WrapEntries", "Boolean"}, {"ResetOnBack", "Boolean"},
            {"MouseSelect", "Boolean"}, {"InhibitOSScreensaver", "Boolean"},
            {"OnLaunch", "String"}, {"ApplicationTimeout", "Integer"},
            {"FPSLimit", "Integer"}, {"StartupCmd", "String"}, {"QuitCmd", "String"}
        };
        for (String[] s : otherSettings) addSetting(p, s[0], s[1]);
        return wrapInPanel(p);
    }

    private JPanel createVisualsTab() {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        p.add(new JLabel("Mode:"));
        String[] modes = {"Color", "Image", "Slideshow", "Transparent"};
        JComboBox<String> modeCombo = new JComboBox<>(modes);
        modeCombo.setSelectedItem(generalSettings.getOrDefault("Mode", "Image"));
        fieldMap.put("Mode", modeCombo);
        p.add(modeCombo);

        p.add(new JLabel("Color:"));
        String currentHex = generalSettings.getOrDefault("Color", "#000000");
        JButton colorBtn = new JButton(currentHex);
        colorBtn.setBackground(decodeHex(currentHex));
        colorBtn.addActionListener(e -> {
            Color selected = JColorChooser.showDialog(this, "Select Background Color", decodeHex(colorBtn.getText()));
            if (selected != null) {
                String hex = String.format("#%02x%02x%02x", selected.getRed(), selected.getGreen(), selected.getBlue()).toUpperCase();
                colorBtn.setText(hex);
                colorBtn.setBackground(selected);
            }
        });
        fieldMap.put("Color", colorBtn);
        p.add(colorBtn);

        String[][] otherVisuals = {
            {"Image", "String"}, {"MaxButtons", "Integer"}, {"IconSize", "Integer"}, 
            {"IconSpacing", "String"}, {"VCenter", "String"}, {"Overlay", "Boolean"}, 
            {"OverlayOpacity", "String"}
        };
        for (String[] s : otherVisuals) addSetting(p, s[0], s[1]);
        return wrapInPanel(p);
    }

    private JPanel createMenuManagerTab() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBorder(BorderFactory.createTitledBorder("Available Menus"));
        JList<String> sectionList = new JList<>(menuListModel);
        
        JButton addSubmenuBtn = new JButton("Create Submenu");
        addSubmenuBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Submenu Name:");
            if (name != null && !name.trim().isEmpty() && !menuModels.containsKey(name)) {
                menuModels.put(name, createNewTableModel());
                menuListModel.addElement(name);
            }
        });

        sidebar.add(new JScrollPane(sectionList), BorderLayout.CENTER);
        sidebar.add(addSubmenuBtn, BorderLayout.SOUTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        menuTable = new JTable();
        menuTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablePanel.add(new JScrollPane(menuTable), BorderLayout.CENTER);

        sectionList.addListSelectionListener(e -> {
            String selected = sectionList.getSelectedValue();
            if (selected != null) {
                activeMenu = selected;
                menuTable.setModel(menuModels.get(selected));
            }
        });
        sectionList.setSelectedValue("Main", true);

        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add Entry");
        JButton delBtn = new JButton("Remove Selected");
        addBtn.addActionListener(e -> menuModels.get(activeMenu).addRow(new Object[]{"", "New Entry", "", ""}));
        delBtn.addActionListener(e -> {
            int[] rows = menuTable.getSelectedRows();
            DefaultTableModel model = menuModels.get(activeMenu);
            for (int i = rows.length - 1; i >= 0; i--) model.removeRow(rows[i]);
        });
        btnPanel.add(addBtn); btnPanel.add(delBtn);
        tablePanel.add(btnPanel, BorderLayout.SOUTH);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private void addSetting(JPanel p, String key, String type) {
        p.add(new JLabel(key + ":"));
        String val = generalSettings.getOrDefault(key, "");
        JComponent comp;
        if (type.equals("Boolean")) {
            JCheckBox cb = new JCheckBox();
            cb.setSelected(val.equalsIgnoreCase("true"));
            comp = cb;
        } else if (type.equals("Integer")) {
            int startVal = (val.isEmpty() || !val.matches("\\d+")) ? 0 : Integer.parseInt(val);
            comp = new JSpinner(new SpinnerNumberModel(startVal, 0, 9999, 1));
        } else {
            comp = new JTextField(val);
        }
        fieldMap.put(key, comp);
        p.add(comp);
    }

    private void loadConfig() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(configPath));
            String currentSection = "General";
            menuModels.put("Main", createNewTableModel());
            menuModels.put("System", createNewTableModel());
            menuListModel.addElement("Main");
            menuListModel.addElement("System");

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentSection = line.substring(1, line.length() - 1);
                    if (!menuModels.containsKey(currentSection)) {
                        menuModels.put(currentSection, createNewTableModel());
                        menuListModel.addElement(currentSection);
                    }
                    continue;
                }
                if (line.contains("=") && !line.startsWith("#")) {
                    String[] parts = line.split("=", 2);
                    String k = parts[0].trim(), v = parts[1].trim();
                    if (k.startsWith("Entry")) {
                        String[] d = v.split(";");
                        if (d.length >= 3) menuModels.get(currentSection).addRow(new Object[]{k, d[0], d[1], d[2]});
                    } else {
                        generalSettings.put(k, v);
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void saveConfig() {
        try {
            List<String> inputLines = Files.readAllLines(Paths.get(configPath));
            List<String> outputLines = new ArrayList<>();
            Set<String> processedKeys = new HashSet<>();
            Set<String> processedMenus = new HashSet<>();
            String currentSection = "General";

            for (String line : inputLines) {
                String trimmed = line.trim();
                
                // Track sections
                if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
                    // Before leaving a menu section, we don't do much because menus are handled as a block
                    currentSection = trimmed.substring(1, trimmed.length() - 1);
                    outputLines.add(line);
                    continue;
                }

                // Handle commented out lines or blank lines: Keep them!
                if (trimmed.startsWith("#") || trimmed.isEmpty()) {
                    outputLines.add(line);
                    continue;
                }

                // Handle Settings
                if (trimmed.contains("=") && !trimmed.startsWith("Entry")) {
                    String key = trimmed.split("=")[0].trim();
                    if (key.equals("DefaultMenu")) {
                        outputLines.add("DefaultMenu=" + defaultMenuCombo.getSelectedItem());
                    } else if (fieldMap.containsKey(key)) {
                        outputLines.add(key + "=" + getComponentValue(fieldMap.get(key)));
                        processedKeys.add(key);
                    } else {
                        outputLines.add(line);
                    }
                } 
                // Skip original entries; we will append the updated menu block at the end of the section
                else if (trimmed.startsWith("Entry")) {
                    if (!processedMenus.contains(currentSection)) {
                        DefaultTableModel model = menuModels.get(currentSection);
                        if (model != null) {
                            for (int i = 0; i < model.getRowCount(); i++) {
                                outputLines.add("Entry" + (i + 1) + "=" + model.getValueAt(i, 1) + ";" + model.getValueAt(i, 2) + ";" + model.getValueAt(i, 3));
                            }
                        }
                        processedMenus.add(currentSection);
                    }
                } else {
                    outputLines.add(line);
                }
            }

            Files.write(Paths.get(configPath), outputLines);
            JOptionPane.showMessageDialog(this, "Settings Saved (Comments Preserved).");
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Save Error: " + e.getMessage()); }
    }

    private String getComponentValue(JComponent v) {
        if (v instanceof JCheckBox) return String.valueOf(((JCheckBox)v).isSelected());
        if (v instanceof JSpinner) return String.valueOf(((JSpinner)v).getValue());
        if (v instanceof JComboBox) return (String)((JComboBox<?>)v).getSelectedItem();
        if (v instanceof JButton) return ((JButton)v).getText();
        return ((JTextField)v).getText();
    }

    private JPanel createTab(String[][] settings) {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (String[] setting : settings) addSetting(p, setting[0], setting[1]);
        return wrapInPanel(p);
    }

    private JPanel wrapInPanel(JPanel p) { return new JPanel(new BorderLayout()){{add(p, BorderLayout.NORTH);}}; }
    private DefaultTableModel createNewTableModel() { return new DefaultTableModel(new String[]{"ID", "Name", "Icon Path", "Command"}, 0); }
    private Color decodeHex(String hex) { try { return Color.decode(hex); } catch (Exception e) { return Color.BLACK; } }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            File localFile = new File("config.ini");
            String path = null;
            if (localFile.exists() && JOptionPane.showConfirmDialog(null, "Use local config.ini?", "FlexLauncherEditor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                path = localFile.getAbsolutePath();
            } else {
                JFileChooser c = new JFileChooser(new File("."));
                if (c.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) path = c.getSelectedFile().getAbsolutePath();
            }
            if (path != null) new FlexLauncherEditor(path).setVisible(true);
        });
    }
}