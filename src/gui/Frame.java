package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(String title) throws HeadlessException {
        super(title);
        setLayout(new GridLayout(1,2));
        FieldPanel fieldPanel = new FieldPanel();
        add(fieldPanel);

    }
}
