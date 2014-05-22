package logic;

public class Field implements Runnable {
    private int countCellsWidth = 10;
    private int countCellsHeight = 20;
    private boolean[][] field = new boolean[countCellsHeight][countCellsWidth];
    private long timeSleep=1000;
    private long acceleration=timeSleep;
    private Shape shape = new Shape(field);

    public Field() {

        Thread threadField = new Thread(this);
        threadField.start();

    }

    public boolean[][] getField() {
        return field;
    }

    public void timeStep() {
        if (shape.touchBottom()==true) {
            searchFullLine();
            shape = new Shape(field);
            acceleration = timeSleep;
            return;
        }
        shape.moveDown();
    }


    public Shape getShape() {
        return shape;
    }

    @Override
    public void run() {
        while (true) {

            timeStep();

            try {
                Thread.sleep(acceleration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void acceleration() {
        acceleration = timeSleep/10;
    }

    public void searchFullLine() {
        for (int i=0; i<field.length; i++) {
            int count = 0;
            for (int j=0; j<field[0].length; j++) {
                if (field[i][j]) {
                    count++;
                }
            }
            if (count==field[0].length) {
                deleteFullLine(i);

            }
        }
    }

    private void deleteFullLine(int row) {
        for (int i=row; i>0; i--) {
            for (int j=0; j<field[0].length; j++) {
                field[i][j]=field[i-1][j];
            }
        }
    }
}
