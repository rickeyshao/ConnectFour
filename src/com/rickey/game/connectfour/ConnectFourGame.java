package com.rickey.game.connectfour;

import com.rickey.game.controller.ConnectGameController;
import com.rickey.game.datamodel.ColorDisc;
import com.rickey.game.datamodel.FallDownGridPanel;
import com.rickey.game.datamodel.GamePlayer;
import com.rickey.game.strategy.ConnectWinStrategy;
import com.rickey.game.strategy.IWinStrategy;

import java.util.Scanner;

/**
 * The class {@code ConnectFourGame} is the main class of ConnectFour game.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class ConnectFourGame {
    public static final int WIN_CONNECTION_COUNT = 4;

    public static void main(String[] args){
        //the grid panel for ConnectFour game is 7 * 6
        FallDownGridPanel<ColorDisc> panel = new FallDownGridPanel<>(7, 6);

        //we have two players in ConnectFour game, player 1 with red discs, and player 2 with green discs
        GamePlayer[] players = new GamePlayer[]{new GamePlayer("Player 1", ColorDisc.RED_DISC),
                new GamePlayer("Player 2", ColorDisc.GREEN_DISC)};

        //define the win strategy of connect four
        IWinStrategy winStrategy = new ConnectWinStrategy(WIN_CONNECTION_COUNT);

        ConnectGameController gameController = new ConnectGameController(panel, System.out, players, new Scanner(System.in));
        gameController.setWinStrategy(winStrategy);

        //start the game
        gameController.start();
    }
}
