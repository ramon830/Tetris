package gui;

import logic.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame implements Runnable {

    Field field = new Field();
    FieldPanel fieldPanel = new FieldPanel();

    public Frame(String title) {
        super(title);
        setLayout(new GridLayout(1,2));

        add(fieldPanel);
        fieldPanel.setFocusable(true);
        fieldPanel.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {

                    field.getShape().moveShapeLeft();

                }

                if (key == KeyEvent.VK_RIGHT) {

                    field.getShape().moveShapeRight();

                }

                if (key == KeyEvent.VK_UP) {

                    field.getShape().rotate();

                }

                if (key == KeyEvent.VK_DOWN) {
                    field.acceleration();

                }
            }

        });

        Thread threadDraw = new Thread(this);
        threadDraw.start();

    }

    @Override
    public void run(){
        while(true) {
            drawField();
            try {
                Thread.sleep(1000/24);
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
                    arrayCell[i][j].setColor(Color.PINK);
                } else {
                    arrayCell[i][j].setColor(Color.BLACK);
                }
            }
        }
    }

}
