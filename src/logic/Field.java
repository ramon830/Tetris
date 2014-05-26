package logic;

public class Field {
    private int countCellsWidth = 10;
    private int countCellsHeight = 20;
    private boolean[][] field = new boolean[countCellsHeight][countCellsWidth];
    private long timeSleep=1000;
    private long acceleration=timeSleep;
    private Shape shape = new Shape(field);
    private volatile boolean running = true;
    private int points = 0;
    private int level =1;
    private final int pointsOnLevel =100;

   public boolean[][] getField() {
        return field;
    }


    public void timeStep() {

            if (running) {

                if (shape.touchBottom()==true) {
                    searchFullLine();
                    shape = new Shape(field);
                    acceleration = timeSleep;
                    return;
                }
                shape.moveDown();


            }
        level = 1+ points/pointsOnLevel;


    }

    public boolean gameOver() {
        if (shape.getY()==0 && shape.touchBottom()) {
            return true;
        }
        return false;
    }


    public Shape getShape() {
        return shape;
    }

    public void acceleration() {
        acceleration = timeSleep/10;
    }

    public void searchFullLine() {
        int countFullLine = 0;
        for (int i=0; i<field.length; i++) {
            int count = 0;
            for (int j=0; j<field[0].length; j++) {
                if (field[i][j]) {
                    count++;
                }
            }
            if (count==field[0].length) {
                deleteFullLine(i);
                countFullLine++;

            }
        }
        if (countFullLine>0) {
            points += Math.pow(10, countFullLine);
        }

    }

    private void deleteFullLine(int row) {
        for (int i=row; i>0; i--) {
            for (int j=0; j<field[0].length; j++) {
                field[i][j]=field[i-1][j];
            }
        }
    }

    public void pause() throws InterruptedException
    {
        running = false;
    }

    public void resume()
    {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }

    public long getAcceleration() {
        return acceleration;
    }
}
