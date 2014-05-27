package gui;

import logic.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {

    private final Field field = new Field();
    private final FieldPanel fieldPanel = new FieldPanel(field.getField());
    private final SupportPanel supportPanel = new SupportPanel();

    public Frame() {
        super("Tetris");
        init();
        keyListener();
        buttonListener();
        initThreads();
    }

    private void buttonListener() {
        supportPanel.getPause().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field.isRunning()) {
                    supportPanel.getPause().setText("Start");
                    field.pause();
                } else {
                    supportPanel.getPause().setText("Pause");
                    field.resume();
                    fieldPanel.setFocusable(true);
                    fieldPanel.requestFocusInWindow();
                }
            }
        });
    }

    private void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        setSize((screenSize.height - taskBarSize) * field.getField()[0].length /
                field.getField().length * 2, screenSize.height - taskBarSize);
        setLayout(new GridLayout(1, 2));
        add(fieldPanel);
        add(supportPanel.getSupportPanel());
    }

    private void keyListener() {
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
    }

    private void initThreads() {
        Thread threadDraw = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        fieldPanel.drawField();
                        supportPanel.getLevel().setText("Level: " + field.getLevel());
                        supportPanel.getPoints().setText("Points: " + field.getPoints());
                        Thread.sleep(1000 / 24);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadDraw.start();
        Thread threadTimeSteep = new Thread() {
            @Override
            public void run() {
                while (!field.gameOver()) {
                    try {
                        field.timeStep();
                        Thread.sleep(field.getAcceleration());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("game over");
            }
        };
        threadTimeSteep.start();
    }
}
