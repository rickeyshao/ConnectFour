package com.rickey.game.datamodel;

import com.rickey.game.common.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code FallDownGridPanel} implements a logic that when a disc is
 * dropped at the top of the columns, the disc falls to the bottom of the column
 * if the column is empty, and future discs introduced to the same column will stack over previous ones.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class FallDownGridPanel<T extends IDisc> extends GridPanel<T> {
    public static final FallDownGridPanel SEVEN_SIX_FALLDOWN_GRID_PANEL = new FallDownGridPanel(7, 6);

    private int[] columnStates;

    /**
     * Constructs an instance of FallDownGridPanel.
     * The {@code columnStates} records the current height for all columns.
     *
     * @param mX
     * @param mY
     */
    public FallDownGridPanel(int mX, int mY){
        super(mX, mY);
        columnStates = new int[mX];
        for (int idx = 0; idx < columnStates.length; idx++){
            columnStates[idx] = -1;
        }
    }

    /**
     * Introduce a disc to the column.
     *
     * @param column
     *         the target column to drop the disc
     * @param data
     *         the data to fill in the target cell
     * @throws GameUserException
     *          if game player inputs an invalid column
     */
    public void put(int column, T data) throws GameUserException {
        column -= 1;
        if(data == null) {
            throw new GameSystemException("Putting a null data to grid column.");
        }
        if(column < 0 || column >= maxX){
            throw new GameUserException(String.format("Column [%d] is out of scope.", column + 1));
        }
        if(columnStates[column] == maxY - 1){
            throw  new GameUserException(String.format("The column [%d] has been full.", column + 1));
        }

        columnStates[column]++;
        T previousData = grid[column][columnStates[column]].getData();
        Cell<T> cell = new Cell<T>(column, columnStates[column], data);
        grid[column][columnStates[column]].setData(data);
        ArrayList<CellChange<T>> cellChangeList = new ArrayList<>();
        cellChangeList.add(new CellChange<T>(cell, previousData, data));
        Step<T> step = new Step<>(cellChangeList);
        stepIn(step);
    }

    /**
     * There is a smarter way to check panel full in FallDownGridPanel.
     *
     * @return
     */
    @Override
    public boolean isPanelFull(){
        for (int idx = 0; idx < columnStates.length; idx++){
            if(columnStates[idx] < maxY - 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void afterStepBack(List<CellChange<T>> changeList){
        //for fall down grid panel, there is only one CellChange in the change list
        CellChange<T> cellChange = changeList.get(0);
        int column = cellChange.getCell().getPositionX();
        columnStates[column]--;
    }
}