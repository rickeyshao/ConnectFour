package com.rickey.game.controller;

import com.rickey.game.common.*;
import com.rickey.game.datamodel.FallDownGridPanel;
import com.rickey.game.datamodel.GamePlayer;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * The class {@code ConnectGameController} is an implement of {@link GameController}.
 * The special logic in this class is how to execute the action of current player.
 *
 * There is an index to record the current player.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class ConnectGameController extends GameController{
    private Scanner scanner;

    public ConnectGameController(FallDownGridPanel panel, GamePlayer[] players, Scanner scanner){
        super(panel, players);
        this.scanner = scanner;
    }

    public ConnectGameController(FallDownGridPanel panel, PrintStream printStream, GamePlayer[] players, Scanner scanner){
        super(panel, printStream, players);
        this.scanner = scanner;
    }

    /**
     * Execute a round of action for current player. Including ask player
     * for an input, and process the input.
     */
    @Override
    protected void currentPlayerPerform() {
        //print something like: Player 1 [RED] - choose column (1-7): 6
        int column = -1;
        GamePlayer currentPlayer = getCurrentPlayer();
        while (true){
            out.print(String.format("%s - choose column (1-%d): ", currentPlayer, gridPanel.getMaxX()));
            String input = null;
            try{
                input = scanner.next();
                column = Integer.parseInt(input);
            }catch (Exception ex){
                out.println(String.format("Invalid column: [%s], please select a valid column.", input) );
                continue;
            }

            try {
                ((FallDownGridPanel)gridPanel).put(column, currentPlayer.getDisc());
            } catch (GameUserException e) {
                out.println(String.format("%s, please select a valid column.", e.getMessage()));
                continue;
            }
            break;
        }
    }
}
