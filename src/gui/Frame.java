package gui;

import logic.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame implements Runnable {

    Field field = new Field();
    FieldPanel fieldPanel = new FieldPanel();

    public Frame(String title) throws HeadlessException {
        super(title);
        setLayout(new GridLayout(1,2));

        add(fieldPanel);
        fieldPanel.setFocusable(true);
        fieldPanel.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    field.clearField();
                    field.getShape().moveShapeLeft();
                    drawField();
                    System.out.println("left");



                }

                if (key == KeyEvent.VK_RIGHT) {
                    field.clearField();
                    field.getShape().moveShapeRight();
                    drawField();
                    System.out.println("right");
                }

                if (key == KeyEvent.VK_UP) {
                    System.out.println("up");
                }

                if (key == KeyEvent.VK_DOWN) {
                    System.out.println("down");
                }
            }

        });
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
                    arrayCell[i][j].setColor(Color.PINK);
                } else {
                    arrayCell[i][j].setColor(Color.BLACK);
                }
            }
        }
    }


}
