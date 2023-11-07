package com.group20;

/**
 * 
 * Class to be extended by all subsequent game entities.
 * All entities share a position attribute.
 */

public class Entity {
    
    private Position pos;

    
    /** Sets the board position of the game entity.
     * @param position: Desired position of the given entity.
     */
    public void setPosition(Position position){
        this.pos = position;
    }

    
    /** Gets the current position of the game entity on the game board.
     * @return Position: Current position of the entity.
     */
    public Position getPosition(){
        return this.pos;
    }

    
    /** Determines if a game entity is a Collectible.
     * @return Boolean: Returns true if the entity is a collectible and false otherwise.
     */
    public Boolean isCollectible(){
        if(this.getClass().getSuperclass().getSimpleName().equals("Collectible")){
            return true;
        }
        else{
            return false;
        }
    }

    
    /** 
     * @return int
     */
    public int getValue(){
        return 0;
    }

}
