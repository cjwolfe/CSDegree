package Admin;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class FlexConfigEnhanced {
    private static final String CONFIG_PATH = "./Admin/config.ini"; 
    private static Map<String, JTextField> pathSettings = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Flex Launcher Advanced Config");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // 1. Asset Panel (For Background and Fonts)
        JPanel assetPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        assetPanel.setBorder(BorderFactory.createTitledBorder("System Assets"));

        // We target these specific keys from your config.ini
        addAssetRow(assetPanel, "Image", "Select Background");
        addAssetRow(assetPanel, "Font", "Select Font");

        // 2. Load current values
        loadConfig(CONFIG_PATH);

        // 3. Control Panel (Save/Reload)
        JPanel controlPanel = new JPanel();
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> saveConfig(CONFIG_PATH));
        
        controlPanel.add(saveButton);
        controlPanel.setBackground(Color.LIGHT_GRAY);

        frame.add(assetPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addAssetRow(JPanel panel, String key, String buttonText) {
        JPanel row = new JPanel(new BorderLayout(5, 5));
        JTextField textField = new JTextField(30);
        JButton browseBtn = new JButton("...");
        
        pathSettings.put(key, textField);
        
        browseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                textField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        row.add(new JLabel(key + ": "), BorderLayout.WEST);
        row.add(textField, BorderLayout.CENTER);
        row.add(browseBtn, BorderLayout.EAST);
        panel.add(row);
    }

    private static void loadConfig(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                for (String key : pathSettings.keySet()) {
                    if (line.startsWith(key + "=")) {
                        pathSettings.get(key).setText(line.split("=")[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading config: " + e.getMessage());
        }
    }

    private static void saveConfig(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<String> newLines = new ArrayList<>();

            for (String line : lines) {
                boolean matched = false;
                for (String key : pathSettings.keySet()) {
                    if (line.startsWith(key + "=")) {
                        newLines.add(key + "=" + pathSettings.get(key).getText());
                        matched = true;
                        break;
                    }
                }
                if (!matched) newLines.add(line);
            }
            Files.write(Paths.get(path), newLines);
            JOptionPane.showMessageDialog(null, "Asset paths updated!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving config.");
        }
    }
}