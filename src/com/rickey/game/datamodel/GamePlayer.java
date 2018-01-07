package com.rickey.game.datamodel;

/**
 * {@code GamePlayer} has its own name and disc.
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class GamePlayer {
    private String name;
    private IDisc disc;

    public GamePlayer(String name, IDisc disc){
        this.name = name;
        this.disc = disc;
    }

    public String getName(){
        return name;
    }

    public IDisc getDisc(){
        return disc;
    }

    /**
     * Return a description of a game player.
     *
     * @return A string generated with its name and disc full name
     */
    @Override
    public String toString(){
        return String.format("%s [%s]", name, disc.getFullDisplay());
    }
}
