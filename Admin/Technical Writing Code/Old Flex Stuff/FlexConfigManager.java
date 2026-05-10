package Admin;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class FlexConfigManager {
    private static final String CONFIG_PATH = "./Admin/config.ini"; // Ensure this matches your file location
    private static Map<String, JCheckBox> settingsMap = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Flex Launcher Config Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // 1. Center Panel for Settings (GridLayout)
        JPanel settingsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        settingsPanel.setBorder(BorderFactory.createTitledBorder("General Settings"));

        // Define the booleans we want to control from your config.ini
        String[] booleanKeys = {"VSync", "WrapEntries", "ResetOnBack", "MouseSelect", "InhibitOSScreensaver"};
        
        for (String key : booleanKeys) {
            JCheckBox checkBox = new JCheckBox(key);
            settingsMap.put(key, checkBox);
            settingsPanel.add(checkBox);
        }

        // 2. Load current values from config.ini
        loadConfig(CONFIG_PATH);

        // 3. Bottom Control Panel
        JPanel controlPanel = new JPanel();
        JButton saveButton = new JButton("Save Changes");
        JButton refreshButton = new JButton("Reload");
        JLabel statusLabel = new JLabel(" Ready");

        saveButton.addActionListener(e -> {
            saveConfig(CONFIG_PATH);
            statusLabel.setText(" Status: Config Saved!");
        });

        refreshButton.addActionListener(e -> {
            loadConfig(CONFIG_PATH);
            statusLabel.setText(" Status: Config Reloaded");
        });

        controlPanel.add(saveButton);
        controlPanel.add(refreshButton);
        controlPanel.setBackground(Color.LIGHT_GRAY);

        frame.add(settingsPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(statusLabel, BorderLayout.NORTH);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loadConfig(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                for (String key : settingsMap.keySet()) {
                    if (line.startsWith(key + "=")) {
                        boolean value = Boolean.parseBoolean(line.split("=")[1].trim());
                        settingsMap.get(key).setSelected(value);
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not read config.ini");
        }
    }

    private static void saveConfig(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<String> newLines = new ArrayList<>();

            for (String line : lines) {
                boolean matched = false;
                for (String key : settingsMap.keySet()) {
                    if (line.startsWith(key + "=")) {
                        newLines.add(key + "=" + settingsMap.get(key).isSelected());
                        matched = true;
                        break;
                    }
                }
                if (!matched) newLines.add(line);
            }
            Files.write(Paths.get(path), newLines);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file.");
        }
    }
}