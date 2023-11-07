package com.group20;

/**
 * 
 * Used as an attribute for entities to track their
 * position on the game map.
 */

public class Position{
    
    private int xPos, yPos;

    /**
     * Constructor for Position objects
     * @param x x coordinate
     * @param y y coordinate
     */
    Position(int x, int y){
        xPos = x;
        yPos = y;
    }

    
    /** Sets X coordinate for an entity on the game map.
     * @param x The x coordinate. 
     */
    public void setX(int x){
        xPos = x;
    }

    
    /** Sets Y coordinate for an entity on the game map.
     * @param y The y coordinate.
     */
    public void setY(int y){
        yPos = y;
    }

    
    /** Gets X coordinate for an entity on the game map.
     * @return The x coordinate.
     */
    public int getX(){
        return this.xPos;
    }

    
    /** Gets Y coordinate for an entity on the game map.
     * @return The y coordinate.
     */
    public int getY(){
        return this.yPos;
    }

    /**
     * Overrides equals comparator
     * Returns true if the given position matches the calling position exactly
     * Returns false if given object is not a position or does not exactly match calling position 
     */
    @Override
    public boolean equals(Object object){
        //checks if given object is a Position intance
        if(object==this){
            return true;
        }
        if(!(object instanceof Position)){
            return false;
        }
        
        //cast parameter object to Position type
        Position compare = (Position) object;

        //compare both x and y coordinates
        if(this.getX()!=compare.getX()||this.getY()!=compare.getY()){
            return false;
        }
        else{
            return true;
        }
    }

}
