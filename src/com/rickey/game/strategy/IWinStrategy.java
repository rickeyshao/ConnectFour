package com.rickey.game.strategy;

import com.rickey.game.datamodel.GridPanel;
import com.rickey.game.datamodel.Step;

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
     * @param step
     *         The last step which was performed by player
     * @return {@code true} if the current player is win, {@code false} otherwise
     */
    public boolean isWin(GridPanel panel, Step step);

    /**
     * After a cell is filled, check whether the current state is a draw.
     * This check should be performed after win check {@code isWin()}.
     *
     * @param panel
     *         The grid panel where the strategy works on
     * @return {@code true} if the current player is win, {@code false} otherwise
     * @see  #isWin(GridPanel panel, Step step)
     */
    public boolean isGameADraw(GridPanel panel);
}
