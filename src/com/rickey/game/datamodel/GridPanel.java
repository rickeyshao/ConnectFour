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

    public Cell<T> getUpSameCell(Cell<T> cell){
        checkCell(cell);
        //no up cell
        if(cell.getPositionY() == maxY - 1){
            return null;
        }

        Cell upCell = grid[cell.getPositionX()][cell.getPositionY() + 1];
        if(cell.getData().equals(upCell.getData())){
            return upCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getDownSameCell(Cell<T> cell){
        checkCell(cell);
        //no down cell
        if(cell.getPositionY() == 0){
            return null;
        }

        Cell downCell = grid[cell.getPositionX()][cell.getPositionY() - 1];
        if(cell.getData().equals(downCell.getData())){
            return downCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getLeftSameCell(Cell<T> cell){
        checkCell(cell);
        //no left cell
        if(cell.getPositionX() == 0){
            return null;
        }

        Cell leftCell = grid[cell.getPositionX() - 1][cell.getPositionY()];
        if(cell.getData().equals(leftCell.getData())){
            return leftCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getRightSameCell(Cell<T> cell){
        checkCell(cell);
        //no right cell
        if(cell.getPositionX() == maxX - 1){
            return null;
        }

        Cell rightCell = grid[cell.getPositionX() + 1][cell.getPositionY()];
        if(cell.getData().equals(rightCell.getData())){
            return rightCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getRightUpSameCell(Cell<T> cell){
        checkCell(cell);
        //no right-up cell
        if(cell.getPositionX() == maxX - 1 || cell.getPositionY() == maxY - 1){
            return null;
        }

        Cell rightUpCell = grid[cell.getPositionX() + 1][cell.getPositionY() + 1];
        if(cell.getData().equals(rightUpCell.getData())){
            return rightUpCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getRightDownSameCell(Cell<T> cell){
        checkCell(cell);
        //no right-down cell
        if(cell.getPositionX() == maxX - 1 || cell.getPositionY() == 0){
            return null;
        }

        Cell rightDownCell = grid[cell.getPositionX() + 1][cell.getPositionY() - 1];
        if(cell.getData().equals(rightDownCell.getData())){
            return rightDownCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getLeftUpSameCell(Cell<T> cell){
        checkCell(cell);
        //no left-up cell
        if(cell.getPositionX() == 0 || cell.getPositionY() == maxY - 1){
            return null;
        }

        Cell leftUpCell = grid[cell.getPositionX() - 1][cell.getPositionY() + 1];
        if(cell.getData().equals(leftUpCell.getData())){
            return leftUpCell;//TODO: return a clone
        }
        return null;
    }

    public Cell<T> getLeftDownSameCell(Cell<T> cell){
        checkCell(cell);
        //no left-down cell
        if(cell.getPositionX() == 0 || cell.getPositionY() == 0){
            return null;
        }

        Cell leftDownCell = grid[cell.getPositionX() - 1][cell.getPositionY() - 1];
        if(cell.getData().equals(leftDownCell.getData())){
            return leftDownCell;//TODO: return a clone
        }
        return null;
    }
}
