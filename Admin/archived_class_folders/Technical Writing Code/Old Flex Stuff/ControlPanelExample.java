package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanelExample {

    public static void main(String[] args) {
        // Ensure GUI creation is on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // 1. Create the JFrame
        JFrame frame = new JFrame("Control Panel Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use BorderLayout for the frame (default, but explicit for clarity)
        frame.setLayout(new BorderLayout()); 

        // 2. Create the control JPanel
        JPanel controlPanel = new JPanel();
        
        // 3. Set the layout for the panel (FlowLayout is default for JPanel)
        // We can change it if needed, e.g., controlPanel.setLayout(new GridLayout(1, 2));

        // 4. Add components to the JPanel
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JLabel statusLabel = new JLabel("Status: Ready");

        // Add action listeners (optional, for functionality)
        button1.addActionListener(e -> statusLabel.setText("Status: Button 1 clicked"));
        button2.addActionListener(e -> statusLabel.setText("Status: Button 2 clicked"));

        controlPanel.add(button1);
        controlPanel.add(button2);
        controlPanel.add(statusLabel);
        
        // Set a background color for visibility
        controlPanel.setBackground(Color.LIGHT_GRAY); 

        // 5. Add the JPanel to the JFrame
        // Placing it at the bottom (SOUTH)
        frame.add(controlPanel, BorderLayout.SOUTH);
        
        // Add another panel to the center for other content (e.g., drawing area)
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBackground(Color.WHITE);
        frame.add(mainContentPanel, BorderLayout.CENTER);

        // Pack the frame to size it according to the components' preferred sizes
        frame.pack();
        // Make the frame visible
        frame.setVisible(true);
    }
}
