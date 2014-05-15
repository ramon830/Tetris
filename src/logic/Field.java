package logic;

public class Field {
    private int countCellsWidth = 10;
    private int countCellsHeight = 20;
    private boolean[][] field = new boolean[countCellsHeight][countCellsWidth];
    private long timeSleep=1000;
    private Shape shape;

    public Field() {
        generateShape();
    }

    public long getTimeSleep() {
        return timeSleep;
    }

    public boolean[][] getField() {
        return field;
    }

    public void timeStep() {
        if (shape.touchBottom()==true) {
            generateShape();
            return;
        }
        field = shape.moveDown(field);
    }

    private void generateShape() {
        shape = new TShape();
        shape.setX((int)(Math.random()*(countCellsWidth-shape.getArrayShape()[0].length)));
    }
}
