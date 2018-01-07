package com.rickey.game.datamodel;

/**
 * An implement class of the interface {@link IDisc}. {@code ColorDisc}
 * cannot be instantiated out of the class. Currently, there is only two
 * kind of ColorDisc.
 * @see #RED_DISC
 * @see #GREEN_DISC
 *
 * @author Rickey Shao
 * @since 1.0
 */
public class ColorDisc implements IDisc {
    public static final ColorDisc RED_DISC = new ColorDisc("RED");
    public static final ColorDisc GREEN_DISC = new ColorDisc("GREEN");

    protected String color = "";
    protected ColorDisc(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof ColorDisc && color != null){
            return this.color.equals(((ColorDisc) obj).color);
        }
        return false;
    }

    @Override
    public String getShortDisplay() {
        return color.substring(0, 1);
    }

    @Override
    public String getFullDisplay() {
        return color;
    }
}
