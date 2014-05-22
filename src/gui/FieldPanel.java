package gui;

import javax.swing.*;
import java.awt.*;


public class FieldPanel extends JPanel {

    private boolean[][] array;

    public FieldPanel(boolean[][] array) {
        this.array = array;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int widhtPanel = getWidth();
        int heightPanel = getHeight();
        int border = 5;
        int widthCell = (widhtPanel-(array[0].length+1)*border)/array[0].length;
        int heightCell = (heightPanel-(array.length+1)*border)/array.length;
        setBackground(Color.LIGHT_GRAY);
        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array[0].length; j++) {
                if (array[i][j]) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.BLACK);
                }

                int x=j*(border+widthCell)+border;
                int y= i*(border+heightCell)+border;
               g.fillRect(x,y, widthCell, heightCell);

            }

        }

    }

    public void drawField() {
        repaint();
    }

}
