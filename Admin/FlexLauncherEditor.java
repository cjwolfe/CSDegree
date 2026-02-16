package Admin;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class FlexLauncherEditor {
    private static final String CONFIG_PATH = "./Admin/config.ini"; 
    private static Map<String, JCheckBox> toggleSettings = new HashMap<>();
    private static Map<String, JTextField> pathSettings = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Flex Launcher: Full Configuration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Main container for all settings
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        // 1. Boolean Toggles Section
        JPanel togglePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        togglePanel.setBorder(BorderFactory.createTitledBorder("General Preferences"));
        String[] booleanKeys = {"VSync", "WrapEntries", "ResetOnBack", "MouseSelect", "InhibitOSScreensaver"};
        for (String key : booleanKeys) {
            JCheckBox cb = new JCheckBox(key);
            toggleSettings.put(key, cb);
            togglePanel.add(cb);
        }

        // 2. Asset Paths Section
        JPanel assetPanel = new JPanel();
        assetPanel.setLayout(new BoxLayout(assetPanel, BoxLayout.Y_AXIS));
        assetPanel.setBorder(BorderFactory.createTitledBorder("System Assets (Images/Fonts)"));
        addAssetRow(assetPanel, "Image", "Background");
        addAssetRow(assetPanel, "Font", "Title Font");

        mainContainer.add(togglePanel);
        mainContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        mainContainer.add(assetPanel);

        // 3. Control Panel (The Footer)
        JPanel controlPanel = new JPanel();
        JButton saveBtn = new JButton("Save Configuration");
        JButton reloadBtn = new JButton("Reload File");
        
        saveBtn.addActionListener(e -> saveConfig(CONFIG_PATH));
        reloadBtn.addActionListener(e -> loadConfig(CONFIG_PATH));

        controlPanel.add(saveBtn);
        controlPanel.add(reloadBtn);
        controlPanel.setBackground(Color.LIGHT_GRAY);

        // Initial Load
        loadConfig(CONFIG_PATH);

        frame.add(new JScrollPane(mainContainer), BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addAssetRow(JPanel panel, String key, String label) {
        JPanel row = new JPanel(new BorderLayout(5, 5));
        JTextField textField = new JTextField(25);
        JButton browseBtn = new JButton("Browse");
        
        pathSettings.put(key, textField);
        browseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                textField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        row.add(new JLabel(label + ": "), BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        row.add(browseBtn, BorderLayout.EAST);
        panel.add(row);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private static void loadConfig(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                String cleanLine = line.trim();
                // Match Booleans
                for (String key : toggleSettings.keySet()) {
                    if (cleanLine.startsWith(key + "=")) {
                        toggleSettings.get(key).setSelected(Boolean.parseBoolean(cleanLine.split("=")[1].trim()));
                    }
                }
                // Match Paths
                for (String key : pathSettings.keySet()) {
                    if (cleanLine.startsWith(key + "=")) {
                        pathSettings.get(key).setText(cleanLine.split("=")[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading " + path);
        }
    }

    private static void saveConfig(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<String> newLines = new ArrayList<>();

            for (String line : lines) {
                String cleanLine = line.trim();
                boolean updated = false;

                // Check toggles
                for (String key : toggleSettings.keySet()) {
                    if (cleanLine.startsWith(key + "=")) {
                        newLines.add(key + "=" + toggleSettings.get(key).isSelected());
                        updated = true; break;
                    }
                }
                // Check paths
                if (!updated) {
                    for (String key : pathSettings.keySet()) {
                        if (cleanLine.startsWith(key + "=")) {
                            newLines.add(key + "=" + pathSettings.get(key).getText());
                            updated = true; break;
                        }
                    }
                }

                if (!updated) newLines.add(line);
            }
            Files.write(Paths.get(path), newLines);
            JOptionPane.showMessageDialog(null, "Settings successfully written to config.ini!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Write Error: " + e.getMessage());
        }
    }
}