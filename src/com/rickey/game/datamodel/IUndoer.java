package com.rickey.game.datamodel;

import com.rickey.game.common.GameUserException;

/**
 * {@code IUndoer} is an interface which support undo in the game.
 *
 * @author Rickey Shao
 * @since 1.1
 */
public interface IUndoer<T extends IDisc> {
    public void stepIn(Step<T> step);
    public void stepBack() throws GameUserException;
}