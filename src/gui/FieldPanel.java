package gui;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {

    private int countCellsWidth = 12;
    private int countCellsHeight = 22;

    public FieldPanel() {

       init();

    }

    private void init() {

        setLayout(new GridLayout(countCellsHeight, countCellsWidth));
        Cell[][] arrayCell = new Cell[countCellsHeight][countCellsWidth];
        for (int i=0; i<arrayCell.length; i++) {
            for (int j=0; j<arrayCell[0].length; j++) {
                arrayCell[i][j] = new Cell();
                add(arrayCell[i][j]);
            }
        }
        for (int i=0; i<countCellsWidth; i++) {
            arrayCell[0][i].setColor(Color.GRAY);
            arrayCell[countCellsHeight-1][i].setColor(Color.GRAY);

        }
        for (int i=0; i<countCellsHeight; i++) {
            arrayCell[i][0].setColor(Color.GRAY);
            arrayCell[i][countCellsWidth-1].setColor(Color.GRAY);

        }
        for (int i=1; i<arrayCell.length-1; i++) {
            for (int j=1; j<arrayCell[0].length-1; j++) {
                arrayCell[i][j].setColor(Color.BLACK);
            }
        }



    }
}
