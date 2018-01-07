package com.rickey.game.controller;

import com.rickey.game.common.GameSystemException;
import com.rickey.game.datamodel.Cell;
import com.rickey.game.datamodel.GamePlayer;
import com.rickey.game.datamodel.GridPanel;
import com.rickey.game.strategy.IWinStrategy;

import java.io.PrintStream;

/**
 * The class {@code GameController} implements a logic for the process of the game.
 * There could be multiple game players. Players take alternate turns. After the
 * action of each player, we check whether there is a winner, or it's a draw. If no,
 * the game will continue until a winner or a draw is identified.
 *
 * There is an index to record the current player.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public abstract class GameController {
    protected GridPanel gridPanel = null;

    //where there information is printed out
    protected PrintStream out = null;
    protected GamePlayer[] gamePlayers;
    protected IWinStrategy winStrategy;
    private int currentPlayerIndex = 0;

    /**
     * Constructs a GameController instance which coordinate a grid panel and players
     * to perform a kind of game.
     *
     * @param panel
     * @param players
     */
    public GameController(GridPanel panel, GamePlayer[] players){
        gridPanel = panel;
        out = System.out;
        gamePlayers = players;
    }

    /**
     * Constructs a GameController instance which coordinate a grid panel and players
     * to perform a kind of game.
     *
     * @param panel
     * @param printStream
     *         where there information is printed out
     * @param players
     */
    public GameController(GridPanel panel, PrintStream printStream, GamePlayer[] players){
        gridPanel = panel;
        out = printStream;
        gamePlayers = players;
    }

    public GamePlayer getCurrentPlayer(){
        return gamePlayers[currentPlayerIndex];
    }

    private GamePlayer getLastPlayer(){
        return gamePlayers[(currentPlayerIndex + gamePlayers.length - 1) % gamePlayers.length];
    }

    public void setWinStrategy(IWinStrategy strategy){
        winStrategy = strategy;
    }

    private void doNextTurn()throws GameSystemException {
        //Find the next player
        currentPlayerPerform();
        currentPlayerIndex = (currentPlayerIndex + 1) % gamePlayers.length;
    }

    protected abstract void currentPlayerPerform() throws GameSystemException;

    /**
     * When there is a draw or a winner, the game finishes.
     *
     * @throws GameSystemException
     */
    public void start() throws GameSystemException {
        while (!displayPanelAndCheckWin()){
            doNextTurn();
        }
    }

    private boolean displayPanelAndCheckWin(){
        //print the grid panel state
        gridPanel.displayGridPanel(out);
        out.println();

        //check winner firstly
        Cell latestCell = gridPanel.getLatestPut();
        if(null == latestCell){
            //game has not been started
            return false;
        }

        if(winStrategy.isWin(gridPanel, latestCell)){
            out.println(String.format("%s wins!", getLastPlayer()));
            return true;
        }

        //then check whether is a draw
        if(winStrategy.isGameADraw(gridPanel)){
            out.println("It is draw game!");
            return true;
        }
        return false;
    }
}
