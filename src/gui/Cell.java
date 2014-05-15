package gui;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {



    public void setColor(Color color) {

        setBackground(color);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
       super.paint(g);
       setBorder(BorderFactory.createLineBorder(Color.WHITE));


    }
}
