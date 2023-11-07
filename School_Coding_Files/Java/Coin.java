package com.group20;

/**
 * A type of collectible the player collects to gain points.
 */
public class Coin extends Collectible{

    Coin(Position pos){
        this.setPosition(pos);
    }

    public int getValue(){
        return coinVal;
    }
}
