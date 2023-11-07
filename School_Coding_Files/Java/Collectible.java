package com.group20;

/**
 * A type of entity that the player collects to gain points and open the level exit.
 */
public abstract class Collectible extends Entity{
    
    final int coinVal = 5;
    final int seaweedVal = -10;
    final int treasureVal = 20;

    
    /** Returns the score value of the given collectible.
     * */
    @Override
    public abstract int getValue();


}
