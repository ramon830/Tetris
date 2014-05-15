package logic;

public abstract class Shape {
    private int countCellsWidth = 10;
    private int countCellsHeight = 20;

    protected boolean[][] arrayShape;
    protected int x=0;
    protected int y=0;

    public abstract void moveShapeLeft();
    public abstract void moveShapeRight();
    public abstract void touchLeft();
    public abstract void touchRight();


    public boolean touchBottom() {
        if (countCellsHeight == y) {
            return true;
        }
        return false;
    }

    public boolean[][] moveDown(boolean[][] field) {
        y++;

        for (int i=arrayShape.length-1; i>=0; i--){
            field = clearField(field);
            for (int j=0; j<arrayShape[0].length; j++) {
                //System.out.println(y-arrayShape.length+i);
                if (y-arrayShape.length+i >= 0) {
                    field[y-arrayShape.length+i][x + j]=true;
                }
            }
        }
        return field;
    }

    private boolean[][] clearField(boolean[][] field) {
        for (int i=arrayShape.length-1; i>=0; i--){
            for (int j=0; j<arrayShape[0].length; j++) {

                if (y-arrayShape.length+i-1 >= 0) {
                    field[y-arrayShape.length+i-1][x + j]=false;
                }
            }
        }
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


}
