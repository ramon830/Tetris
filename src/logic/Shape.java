package logic;

public class Shape {
    private int countCellsWidth = 10;
    private int countCellsHeight = 20;

    protected boolean[][] arrayShape;
    protected int x=0;
    protected int y=0;

    public Shape(boolean[][] arrayShape) {
        this.arrayShape = arrayShape;
    }
    public void moveShapeLeft() {
        if (x>0) {
            x--;
        }

    }

    public  void moveShapeRight() {
        if (x<countCellsWidth) {
            x++;
        }
    }


    public boolean touchBottom() {
        if (countCellsHeight == y) {
           return true;
        }
        return false;
    }

    public boolean[][] moveDown(boolean[][] field) {

        for (int i=arrayShape.length-1; i>=0; i--){

            for (int j=0; j<arrayShape[0].length; j++) {

                if (y-arrayShape.length+i >= 0) {
                    field[y-arrayShape.length+i][x + j]=arrayShape[i][j];
                }
            }
        }
       // y++;
        return field;
    }


    public boolean[][] rotate() {
        boolean[][] rotationArray = new boolean[arrayShape[0].length][arrayShape.length];
        int ii = 0;
        int jj = 0;
        for(int i=0; i<arrayShape[0].length; i++){
            for(int j=arrayShape.length-1; j>=0; j--){
                rotationArray[ii][jj] = arrayShape[i][j];

                jj++;
            }
            ii++;
        }

        return rotationArray;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean[][] getArrayShape() {
        return arrayShape;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
