package com.rickey.game.datamodel;

/**
 * {@code CellChange} represents the data change in a grid cell.
 * It contains the previous value of the cell, and the current value of the cell.
 * But currently, the current value is not used.
 *
 * @author Rickey Shao
 * @since 1.1
 */
public class CellChange<T extends IDisc> {
    private Cell<T> cell;
    private T previousValue;
    private T currentValue;

    public CellChange(Cell<T> cell, T pValue, T cValue){
        this.cell = cell;
        previousValue = pValue;
        currentValue = cValue;
    }

    public Cell<T> getCell(){
        return cell;
    }

    public void setCell(Cell<T> cell){
        this.cell = cell;
    }

    public T getPreviousValue(){
        return previousValue;
    }

    public void setPreviousValue(T value){
        previousValue = value;
    }

    public T getCurrentValue(){
        return currentValue;
    }

    public void setCurrentValue(T value){
        currentValue = value;
    }
}