package com.rickey.game.strategy;

import com.rickey.game.datamodel.Cell;
import com.rickey.game.datamodel.GridPanel;

/**
 * An interface of the strategy on how to identify the winner in the game.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public interface IWinStrategy {
    /**
     * After a cell is filled, check whether the current player is a winner
     * according some strategy.
     *
     * @param panel
     *         The grid panel where the strategy works on
     * @param cell
     *         The last cell where some action was performed
     * @return {@code true} if the current player is win, {@code false} otherwise
     */
    public boolean isWin(GridPanel panel, Cell cell);

    /**
     * After a cell is filled, check whether the current state is a draw.
     * This check should be performed after win check {@code isWin()}.
     *
     * @param panel
     *         The grid panel where the strategy works on
     * @return {@code true} if the current player is win, {@code false} otherwise
     * @see  #isWin(GridPanel panel, Cell cell)
     */
    public boolean isGameADraw(GridPanel panel);
}
