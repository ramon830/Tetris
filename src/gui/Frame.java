package gui;

import logic.Field;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements Runnable {

    Field field = new Field();
    FieldPanel fieldPanel = new FieldPanel();

    public Frame(String title) throws HeadlessException {
        super(title);
        setLayout(new GridLayout(1,2));

        add(fieldPanel);
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        while (true) {
            drawField();
            field.timeStep();

            try {
                Thread.sleep(field.getTimeSleep());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void drawField() {
        boolean[][] arrayField = field.getField();
        Cell[][] arrayCell = fieldPanel.getArrayCell();
        for (int i=0; i<arrayField.length; i++) {
            for (int j=0; j<arrayField[0].length; j++) {
                if (arrayField[i][j] == true) {
                    arrayCell[i+1][j+1].setColor(Color.PINK);
                }
            }
        }
    }
}
