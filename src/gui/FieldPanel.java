package gui;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {

    private int countCellsWidth = 10;
    private int countCellsHeight = 20;
    private Cell[][] arrayCell = new Cell[countCellsHeight][countCellsWidth];

    public FieldPanel() {

       init();

    }

    public Cell[][] getArrayCell() {
        return arrayCell;
    }

    private void init() {

        setLayout(new GridLayout(countCellsHeight, countCellsWidth));

        for (int i=0; i<arrayCell.length; i++) {
            for (int j=0; j<arrayCell[0].length; j++) {
                arrayCell[i][j] = new Cell();
                add(arrayCell[i][j]);
            }
        }

        for (int i=0; i<arrayCell.length; i++) {
            for (int j=0; j<arrayCell[0].length; j++) {
                arrayCell[i][j].setColor(Color.BLACK);
            }
        }



    }
}
