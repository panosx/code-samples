package com.group20;
/**
 * A type of entity. Walls prevent the player from being in a given position.
 */
public class Wall extends Entity{
    
    /**
     * Constructor for Wall objects
     * @param pos position of Wall instance
     */
    Wall(Position position){
        this.setPosition(position);
    }

}
