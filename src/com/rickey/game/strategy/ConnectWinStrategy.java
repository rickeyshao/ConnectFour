package com.rickey.game.strategy;

import com.rickey.game.datamodel.Cell;
import com.rickey.game.datamodel.GridPanel;

/**
 * An implement class of interface {@code IWinStrategy}.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class ConnectWinStrategy implements IWinStrategy {
    //If a player inserts a disc and connects more than three discs of his color, he will be the winner.
    public static final ConnectWinStrategy CONNECTION_FOUR_STRATEGY = new ConnectWinStrategy(4);

    private int winCount;

    public ConnectWinStrategy(int count){
        winCount = count;
    }

    /**
     * After a cell is filled, check whether the current player is a winner.
     * If the player connects more than three discs of his color in a
     *     straight vertical,
     *     straight horizontal,
     *     diagonal line,
     *     back diagonal line,
     * then that player wins.
     *
     * @param panel
     *         The grid panel where the strategy works on
     * @param cell
     *         The last cell where some action was performed
     * @return {@code true} if the current player is win, {@code false} otherwise
     */
    @Override
    public boolean isWin(GridPanel panel, Cell cell) {
        int count = 1;
        //1. check vertical
        Cell tempCell = cell;
        //while ((tempCell = panel.getUpSameCell(tempCell)) != null){
        //    count++;
        //}
        //tempCell = cell;
        while ((tempCell = panel.getDownSameCell(tempCell)) != null){
            count++;
        }
        if(count >= winCount){
            return true;
        }

        //2. check horizontal
        count = 1;
        tempCell = cell;
        while ((tempCell = panel.getRightSameCell(tempCell)) != null){
            count++;
        }
        tempCell = cell;
        while ((tempCell = panel.getLeftSameCell(tempCell)) != null){
            count++;
        }
        if(count >= winCount){
            return true;
        }

        //3. check diagonal
        count = 1;
        tempCell = cell;
        while ((tempCell = panel.getLeftUpSameCell(tempCell)) != null){
            count++;
        }
        tempCell = cell;
        while ((tempCell = panel.getRightDownSameCell(tempCell)) != null){
            count++;
        }
        if(count >= winCount){
            return true;
        }

        //4. check back diagonal
        count = 1;
        tempCell = cell;
        while ((tempCell = panel.getRightUpSameCell(tempCell)) != null){
            count++;
        }
        tempCell = cell;
        while ((tempCell = panel.getLeftDownSameCell(tempCell)) != null){
            count++;
        }
        if(count >= winCount){
            return true;
        }

        return false;
    }

    /**
     * After a cell is filled, check whether the current state is a draw.
     * For connect games, when there is no place empty (also means the grid
     * panel is full), and no winners, it is a draw.
     *
     * This check should be performed after win check {@code isWin()}.
     *
     * @param panel
     *         The grid panel where the strategy works on
     * @return {@code true} if the current player is win, {@code false} otherwise
     * @see  #isWin(GridPanel panel, Cell cell)
     */
    @Override
    public boolean isGameADraw(GridPanel panel) {
        return panel.isPanelFull();
    }
}
