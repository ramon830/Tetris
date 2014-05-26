package logic;

public class Shape {

    protected boolean[][] arrayShape;
    private boolean[][] field;
    protected int x=0;
    protected int y=0;

    public Shape(boolean[][] field) {
        this.field = field;
        generateShape();

    }

    public void moveShapeLeft() {
        if (!touchLeft()) {
            clearField();
            x--;
            move();
        }
    }

    public  void moveShapeRight() {
        if (!touchRight()) {
            clearField();
            x++;
            move();
        }

    }

    public boolean touchBottom() {
        if (field.length == y) {
           return true;
        }
        for (int i=0; i<arrayShape[0].length; i++) {
            int j=arrayShape.length-1;
            while (!arrayShape[j][i]) {
                j--;
            }
            if (y-arrayShape.length+j+1 >= 0 && field[y-arrayShape.length+j+1][x + i]) {
                return true;
            }
        }
        return false;
    }

    private boolean touchLeft() {
        if (x==0) {
        return true;
        }
            for (int i=0; i<arrayShape.length; i++) {
                int j=0;
                while (!arrayShape[i][j]) {
                    j++;
                }
                if (y-arrayShape.length+i >= 0 && field[y-arrayShape.length+i][x + j-1]) {
                    return true;
                }
            }

        return false;
    }

    private boolean touchRight() {
        if (x==field[0].length-arrayShape[0].length) {
            return true;
        }
            for (int i=0; i<arrayShape.length; i++) {
                int j=arrayShape[0].length-1;
                while (!arrayShape[i][j]) {
                    j--;
                }
                if (y-arrayShape.length+i >= 0 && x+j+1<field[0].length-arrayShape[0].length && field[y-arrayShape.length+i][x + j+1]) {
                    return true;
                }
            }

        return false;
    }

    public void clearField() {

        for (int i=arrayShape.length-1; i>=0; i--){
            for (int j=0; j<arrayShape[0].length; j++) {

                if (y-arrayShape.length+i >= 0 && arrayShape[i][j]) {
                    field[y-arrayShape.length+i][x + j]=!arrayShape[i][j];
                }
            }
        }

    }

    public void moveDown() {
        if (!touchBottom()) {
            clearField();
            y++;
            move();
        }

    }

    private void move() {
        for (int i=arrayShape.length-1; i>=0; i--){

            for (int j=0; j<arrayShape[0].length; j++) {

                if (y-arrayShape.length+i >= 0 && arrayShape[i][j]) {
                    field[y-arrayShape.length+i][x + j]=arrayShape[i][j];
                }
            }
        }


    }

    public int getY() {
        return y;
    }

    public void rotate() {
        if (x+arrayShape.length>field[0].length) {
            return;
        }
        clearField();
        boolean[][] rotationArray = new boolean[arrayShape[0].length][arrayShape.length];
        int ii = 0;

        for(int i=0; i<arrayShape.length; i++){
            int jj = 0;
            for(int j=arrayShape[0].length-1; j>=0; j--){
                rotationArray[jj][ii] = arrayShape[i][j];

                jj++;
            }
            ii++;
        }

        arrayShape = rotationArray;
        move();
    }

    private void generateShape() {
        double d =  Math.random()*6;
        switch ((int)d) {
            case 0 :arrayShape = new boolean[][]{{true, true, false}, {false, true, true}};
                break;
            case 1:    arrayShape =new boolean[][]{{false, true, false}, {true, true, true}};
                break;
            case 2 : arrayShape = new boolean[][]{{true,true,true,true}};
                break;
            case 3 : arrayShape = new boolean[][]{{true, false, false}, {true, true, true}};
                break;
            case 4 : arrayShape = new boolean[][]{{false, false, true}, {true, true, true}};
                break;
            case 5 : arrayShape = new boolean[][]{{true,true},{true,true}};
                break;
            case 6 : arrayShape = new boolean[][]{{false, true, true}, {true, true, false}};
                break;

        }
        x=((int)(Math.random()*(field[0].length-arrayShape[0].length)));
    }
}
