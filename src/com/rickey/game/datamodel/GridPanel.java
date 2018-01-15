package com.rickey.game.datamodel;

import com.rickey.game.common.GameSystemException;

import java.io.PrintStream;

/**
 * {@code GridPanel} represents a kind of panel with grid on it.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public abstract class GridPanel<T extends IDisc> {

    protected Cell<T>[][] grid;
    protected int maxX;
    protected int maxY;
    protected Cell latestPut = null;

    /**
     * Constructs a grid panel with dimension mX * mY. An 2 * 3 grid panel looks like:
     * 2  |*|*|
     * 1  |*|*|
     * 0  |*|*|
     *     0 1
     *
     * @param mX
     *         X dimension
     * @param mY
     *         Y dimension
     */
    public GridPanel(int mX, int mY){
        maxX = mX;
        maxY = mY;
        grid = new Cell[maxX][maxY];
        initializePanel();
    }

    /**
     * Set the values of all cells in this grid panel to null.
     * This method can be used when starting a new game.
     */
    public void cleanUpPanel(){
        for(int x = 0; x < maxX; x++){
            for(int y = 0; y < maxY; y++){
                grid[x][y].setData(null);
            }
        }
    }

    private void initializePanel(){
        for(int x = 0; x < maxX; x++){
            for(int y = 0; y < maxY; y++){
                grid[x][y] = new Cell<T>(x, y, null);
            }
        }
    }

    /**
     * Print out the current state of the grid panel to a particular place.
     *
     * @param out
     *         where to print this grid panel
     */
    public void displayGridPanel(PrintStream out){
        for(int y = maxY - 1; y >= 0; y--){
            StringBuilder stringBuilder = new StringBuilder();
            T data;
            for(int x = 0; x < maxX; x++){
                data = grid[x][y].getData();
                stringBuilder.append("|" + (null == data ? " " : data.getShortDisplay()));
            }
            stringBuilder.append("|");
            out.println(stringBuilder.toString());
        }
    }

    /**
     * Check whether no any cell on the grid panelwhich has a null value.
     * @return {@code true} if all cells in the grid has non-null values. {@code false} otherwise
     */
    public boolean isPanelFull(){
        for(int x = 0; x < maxX; x++){
            for(int y = 0; y < maxY; y++){
                if(grid[x][y].getData() == null){
                    return false;
                }
            }
        }
        return true;
    }

    public int getMaxX(){
        return maxX;
    }

    public int getMaxY(){
        return maxY;
    }

    /**
     * Find out the latest cell where there was an action.
     *
     * @return The latest cell which was performed on.
     */
    public Cell<T> getLatestPut(){
        return latestPut;
    }

    /**
     * Validation method. Make sure the inputted cell is a valid one.
     * If not, there has been an internal error, and a GameSystemException will be thrown.
     *
     * @param cell
     */
    private void checkCell(Cell<T> cell){
        if(null == cell){
            throw new GameSystemException("Internal error: null cell.");
        }
        if(cell.getPositionY() >= maxY || cell.getPositionY() < 0){
            throw new GameSystemException(String.format("Internal error: invalid cell Y position [%d]", cell.getPositionY()));
        }
        if(cell.getPositionX() >= maxX || cell.getPositionX() < 0){
            throw new GameSystemException(String.format("Internal error: invalid cell X position [%d]", cell.getPositionY()));
        }
    }

    /**
     * Find some cell which position is x = cell.x + xDiff, y = cell.y + yDiff, and whose value is the same as cell
     *
     * @param cell
     *          the cell
     * @param xDiff
     *          the difference in x
     * @param yDiff
     *          the difference in y
     * @return if the target is found, return it; return null otherwise
     */
    public Cell<T> getSameCell(Cell<T> cell, int xDiff, int yDiff){
        checkCell(cell);
        int x = cell.getPositionX() + xDiff;
        int y = cell.getPositionY() + yDiff;
        if(x >= maxX || x < 0 || y >= maxY || y < 0){
            return null;
        }
        Cell targetCell = grid[x][y];
        if(cell.getData().equals(targetCell.getData())){
            return (Cell<T>)targetCell.clone();  //for safety, return a copy of the cell in grid
        }
        return null;
    }
}
