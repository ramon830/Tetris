package logic;

public class Field {
    private int countCellsWidth = 10;
    private int countCellsHeight = 20;
    private boolean[][] field = new boolean[countCellsHeight][countCellsWidth];
    private long timeSleep=3000;
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
        clearField();
        shape.setY(shape.getY()+1);
        field = shape.moveDown(field);
    }

    private void generateShape() {
        shape = new Shape(new boolean[][]{{false, true, false}, {true, true, true}});
        shape.setX((int)(Math.random()*(countCellsWidth-shape.getArrayShape()[0].length)));
    }

    public Shape getShape() {
        return shape;
    }

    public void clearField() {
        boolean[][] arrayShape = shape.getArrayShape();
        for (int i=arrayShape.length-1; i>=0; i--){
            for (int j=0; j<arrayShape[0].length; j++) {

                if (shape.getY()-arrayShape.length+i-1 >= 0) {
                    field[shape.getY()-arrayShape.length+i-1][shape.getX() + j]=false;
                }
            }
        }

    }
}
