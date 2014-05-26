package gui;

import logic.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame  {

    volatile private Field field = new Field();
    volatile private FieldPanel fieldPanel = new FieldPanel(field.getField());
    volatile private SupportPanel supportPanel = new SupportPanel();
    private Thread threadTimeSteep = new Thread() {
        @Override
        public void run() {
            while(!field.gameOver()) {
                try {
                field.timeStep();
                    Thread.sleep(field.getAcceleration());
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("game over");
        }
    };
    private Thread threadDraw = new Thread() {
        @Override
        public void run() {
            while(true) {
                try {
                    fieldPanel.drawField();
                    supportPanel.getLevel().setText("Level: " + field.getLevel());
                    supportPanel.getPoints().setText("Points: "+field.getPoints());
                    Thread.sleep(1000/24);
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    };

    public Frame(String title) {
        super(title);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        setSize((int) ((screenSize.height - taskBarSize) * field.getField()[0].length /
                field.getField().length * 2), screenSize.height - taskBarSize);
        setLayout(new GridLayout(1,2));
        add(fieldPanel);
        add(supportPanel.getSupportPanel());
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
        supportPanel.getPause().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field.isRunning()) {
                    supportPanel.getPause().setText("Start");
                    try {
                        field.pause();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    supportPanel.getPause().setText("Pause");
                    field.resume();
                    fieldPanel.setFocusable(true);
                }
            }
        });


        threadDraw.start();
        threadTimeSteep.start();

    }



}
