package com.rickey.game.datamodel;

/**
 * The class {@code Cell} is a generic class where represent a cell in
 * the grid panel. The data it contains can be any type.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class Cell<T extends IDisc> implements Cloneable {
    //column index
    private int positionX;
    //row index
    private int positionY;
    //the data contains by the cell
    private T data;

    /**
     * Constructs a new {@code Cell} by column index and row index.
     * The default value of its data is null.
     *
     * @param x
     *         column index
     * @param y
     *         row index
     */
    public Cell(int x, int y){
        positionX = x;
        positionY = y;
        data = null;
    }

    /**
     * Constructs a new {@code Cell} by column index, row index,
     * and its data value.
     *
     * @param x
     *         column index
     * @param y
     *         row index
     * @param val
     *         cell data value
     */
    public Cell(int x, int y, T val){
        positionX = x;
        positionY = y;
        data = val;
    }

    public void setData(T val){
        data = val;
    }

    public T getData(){
        return data;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    /**
     * Check whether this cell has the same value as the target cell.
     * If the data in both cells are null, they are different values.
     *
     * @param cell
     *         target cell
     * @return {@code true} if this cell has the same value as target
     * cell, {@code false} otherwise
     */
    public boolean hasSameValue(Cell<T> cell){
        if(null != cell && cell.data.equals(this.data)){
            return true;
        }
        return false;
    }

    @Override
    public Object clone(){
        Cell cell = null;
        try {
            cell = (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            //would not happen
        }
        cell.data = (T) data.clone();
        return cell;
    }
}