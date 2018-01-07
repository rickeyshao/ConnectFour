package com.rickey.game.common;

/**
 * The class {@code GameUserException} is for invalid operations from users.
 * It is a kind of regular {@link java.lang.Exception} and need to be caught
 * by exception handling logic.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class GameUserException extends Exception {
    public GameUserException(String error){
        super(error);
    }
}
