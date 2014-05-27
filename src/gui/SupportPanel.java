package gui;

import javax.swing.*;

public class SupportPanel {
    private JButton pause;
    private JLabel level;
    private JLabel points;
    private JPanel supportPanel;

    public JPanel getSupportPanel() {
        return supportPanel;
    }

    public JButton getPause() {
        return pause;
    }

    public JLabel getPoints() {
        return points;
    }

    public JLabel getLevel() {
        return level;
    }
}
