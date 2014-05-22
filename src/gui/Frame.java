package gui;

import logic.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame implements Runnable {

    private Field field = new Field();
    private FieldPanel fieldPanel = new FieldPanel(field.getField());

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
               fieldPanel.drawField();
            try {
                Thread.sleep(1000/24);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Field getField() {
        return field;
    }
}
