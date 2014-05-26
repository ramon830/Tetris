package gui;

import javax.swing.*;

public class SupportPanel {
    private JButton pause;
    private JLabel level;
    private JLabel points;
    private JPanel supportPanel;
    private JPanel futurePanel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("SupportPanel");
        frame.setContentPane(new SupportPanel().supportPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



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
