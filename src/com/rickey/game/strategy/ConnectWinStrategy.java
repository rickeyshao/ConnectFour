package com.rickey.game.strategy;

import com.rickey.game.datamodel.Cell;
import com.rickey.game.datamodel.CellChange;
import com.rickey.game.datamodel.GridPanel;
import com.rickey.game.datamodel.Step;

/**
 * An implement class of interface {@code IWinStrategy}.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class ConnectWinStrategy implements IWinStrategy {
    //If a player inserts a disc and connects more than three discs of his color, he will be the winner.
    public static final ConnectWinStrategy CONNECTION_FOUR_STRATEGY = new ConnectWinStrategy(4);
    //According to the game definition, there are four directions to win
    private static final int[][] WIN_DIRECTIONS = new int[][]{{0, 1},    //vertical
            {1, 0},    //horizontal
            {1, 1},    //diagonal
            {1, -1}};  //back diagonal

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
     * @param step
     *         The last step which was performed by player
     * @return {@code true} if the current player is win, {@code false} otherwise
     */
    @Override
    public boolean isWin(GridPanel panel, Step step) {
        //For 'Connect *' games, there is only one cell change in a step
        Cell cell = ((CellChange)step.getCellChangeList().get(0)).getCell();
        for(int n = 0; n < WIN_DIRECTIONS.length; n++){
            int[] direction = WIN_DIRECTIONS[n];
            int count = 1;
            Cell tempCell = cell;
            //check positive direction
            while ((tempCell = panel.getSameCell(tempCell, direction[0], direction[1])) != null){
                count++;
            }
            //check negative direction
            tempCell = cell;
            while ((tempCell = panel.getSameCell(tempCell, -direction[0], -direction[1])) != null){
                count++;
            }
            if(count >= winCount){
                return true;
            }
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
     * @see  #isWin(GridPanel panel, Step step)
     */
    @Override
    public boolean isGameADraw(GridPanel panel) {
        return panel.isPanelFull();
    }
}