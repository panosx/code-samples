package com.group20;
/**
 * Used to make the game map and board.
 */
public abstract class GameFactory {
    //Method for making a map instance for factory
    public abstract Map makeMap();
    //Method for making a board instance for factory using map
    public abstract Board makeBoard(Map map);
}
