package com.rickey.game.common;

/**
 * The class {@code GameSystemException} is a kind of {@link java.lang.RuntimeException}.
 * When it occurs, the game can be regarded as CRASHed, and cannot continue. There
 * must be a bug in this application.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class GameSystemException  extends RuntimeException {
    public GameSystemException(){}

    public GameSystemException(String error){
        super(error);
    }

    @Override
    public String getMessage(){
        return "GameSystemException: " + getMessage();
    }
}
