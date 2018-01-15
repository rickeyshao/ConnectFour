package com.rickey.game.datamodel;

import java.util.List;

/**
 * {@code Step} represents one step for a player.
 * One step could contain changes in multiple cells. For connect game, one step only has one cell change.
 *
 * @author Rickey Shao
 * @since 1.1
 */
public class Step<T extends IDisc> {
    private List<CellChange<T>> cellChangeList;

    public Step(List<CellChange<T>> cellChanges){
        cellChangeList = cellChanges;
    }

    public void setCellChangeList(List<CellChange<T>> cellChanges){
        cellChangeList = cellChanges;
    }

    public List<CellChange<T>> getCellChangeList(){
        return cellChangeList;
    }
}