package gui;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame("Tetris");
        frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        frame.setSize((int)((screenSize.height-taskBarSize)*frame.getField().getField()[0].length/
                frame.getField().getField().length), screenSize.height-taskBarSize);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
