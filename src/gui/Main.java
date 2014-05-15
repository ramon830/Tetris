package gui;

public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame("Tetris");
        frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(100,100);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
