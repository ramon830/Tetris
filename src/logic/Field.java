package logic;

public class Field {
    private final int countCellsWidth = 10;
    private final int countCellsHeight = 20;
    private final boolean[][] field = new boolean[countCellsHeight][countCellsWidth];
    private Shape shape = new Shape(field);
    private final long timeSleep = 1000;
    private long acceleration = timeSleep;
    private volatile boolean running = true;
    private int points = 0;
    private int level = 0;

    public boolean[][] getField() {
        return field;
    }

    public void timeStep() {
        if (running) {
            if (shape.touchBottom()) {
                searchFullLine();
                shape = new Shape(field);
                acceleration = (long) (timeSleep - level * 0.1 * timeSleep);
                return;
            }
            shape.moveDown();
        }
        int pointsOnLevel = 100;
        level = points / pointsOnLevel;
    }

    public boolean gameOver() {
        return shape.getY() == 0 && shape.touchBottom();
    }

    public Shape getShape() {
        return shape;
    }

    public void acceleration() {
        acceleration = timeSleep / 10;
    }

    private void searchFullLine() {
        int countFullLine = 0;
        for (int i = 0; i < field.length; i++) {
            int count = 0;
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j]) {
                    count++;
                }
            }
            if (count == field[0].length) {
                deleteFullLine(i);
                countFullLine++;
            }
        }
        if (countFullLine > 0) {
            points += addPoints(countFullLine);
        }
    }

    private void deleteFullLine(int row) {
        for (int i = row; i > 0; i--) {
            System.arraycopy(field[i - 1], 0, field[i], 0, field[0].length);
        }
    }

    public void pause() {
        running = false;
    }

    public void resume() {
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

    private int addPoints(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = 10;
                break;
            case 2:
                result = 40;
                break;
            case 3:
                result = 100;
                break;
            case 4:
                result = 500;
                break;
        }
        return result;
    }
}
