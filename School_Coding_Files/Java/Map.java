package com.group20;

/**
 * 
 * Stores all game entities. 
 * A 2D array is used for drawing the game board in UI.
 * Manages movement of entities after initialization.
 */
public class Map {
    
    private Entity[][] entities;
    Diver diver;
    Shark shark1;
    Exit exit;
    int coinsRemaining, playerScore;
    Boolean gameOver, gameWin, gameLose;
    
    /**
     * Default constructor for Map objects
     * initializes all key attributes
     */
    Map(){
        entities = new Entity[20][20];
        playerScore = 0;
        gameWin = false;
        gameLose = false;
        gameOver = false;
    }

    
    /** Returns true if the game is over, false otherwise.
     * @return Boolean
     */
    public Boolean isGameOver(){
        return this.gameOver;
    }

    
    /** Returns true if the player has lost the game, false otherwise.
     * @return Boolean
     */
    public Boolean isGameLose(){
        return this.gameLose;
    }

    
    /** Returns true if the player has won the game, false otherwise.
     * @return Boolean
     */
    public Boolean isGameWin(){
        return this.gameWin;
    }


    
    /** Sets the player's score.
     * @param num the number that the player's score will be set to.
     */
    public void setPlayerScore(int num){
        this.playerScore = num;
    }

    
    /** Returns the player's current score.
     * @return The player's current score.
     */
    public int getPlayerScore(){
        return this.playerScore;
    }

    
    /** Sets the number of coins remaining on the game board.
     * @param num The number of coins remaining.
     */
    public void setCoinsRemaining(int num){
        this.coinsRemaining = num;
    }

    
    /** Gets the number of coins remaining on the game board.
     * @return The number of coins remaining.
     */
    public int getCoinsRemaining(){
        return this.coinsRemaining;
    }

    
    /** Returns true if all of the coins in the current level have been collected, returns false otherwise.
     * @return Boolean
     */
    public Boolean allCoinsCollected(){
        if(coinsRemaining==0){
            return true;
        }
        else{
            return false;
        }
    }

    
    /** Places a coin into the given position on the game board.
     * @param pos Desired placement of the coin on the game board.
     */
    public void placeCoin(Position pos){
        Coin coin = new Coin(pos);
        entities[coin.getPosition().getX()][coin.getPosition().getY()] = coin;
    }

    
    /** Places a treasure chest into the given position on the game board.
     * @param pos Desired placement of the treasure chest on the game board.
     */
    public void placeTreasureChest(Position pos){
        TreasureChest treasure = new TreasureChest(pos);
        entities[treasure.getPosition().getX()][treasure.getPosition().getY()] = treasure;
    }

    
    /** Places a block of seaweed into the given position on the game board.
     * @param pos Desired placement of the seaweed on the game board.
     */
    public void placeSeaweed(Position pos){
        Seaweed seaweed = new Seaweed(pos);
        entities[seaweed.getPosition().getX()][seaweed.getPosition().getY()] = seaweed;
    }

    
    /** Places a wall into the given position on the game board.
     * @param position Desired placement of the wall on the game board.
     */
    public void placeWall(Position position){
        Wall newWall = new Wall(position);
        entities[newWall.getPosition().getX()][newWall.getPosition().getY()] = newWall;
    }

    
    /** Places a shark onto the game board.
     * @param shark1 The new shark created.
     */
    public void setSharks(Shark shark1){
        this.shark1 = shark1;
        this.setEntityAt(shark1, shark1.getPosition());
    }
    
    /** Gets the shark on the gameboard.
     * @return The given shark.
     */
    public Shark getShark1(){
        return shark1;
    }

    
    /** Sets the position of the shark on the game board.
     * @param position1 The position of the shark.
     * @param position2
     */
    public void setSharkPosition(Position position1, Position position2){
        shark1.setPosition(position1);
    }

    
    /** Gets the position of shark1 on the game board.
     * @return The position of shark1.
     */
    public Position getShark1Position(){
        return shark1.getPosition();
    }

    
    
    /** Places the diver onto the game board.
     * @param player The diver.
     */
    public void setDiver(Diver player){
        this.diver = player;
        this.setEntityAt(player, player.getPosition());

    }

    
    /** Gets the diver.
     * @return The diver.
     */
    public Diver getDiver(){
        return diver;
    }

    
    /** Sets the position of the diver on the game board.
     * @param position The desired position for the diver.
     */
    public void setDiverPosition(Position position){
        diver.setPosition(position);
    }

    
    /** Gets the current position of the diver on the game board.
     * @return The divers current position.
     */
    public Position getDiverPosition(){
        return diver.getPosition();
    }

    
    /** Sets the level exit.
     * @param gameExit The level exit.
     */
    public void setExit(Exit gameExit){
        this.exit = gameExit;
    }

    /** Open the exit of the level.
     * 
     */
    public void openExit(){
        this.removeEntityAt(exit.getPosition());
        this.setEntityAt(exit, exit.getPosition());
    }

    
    /**  Manages behaviour upon collision detection for an entity and the entity it is interacting with.
     * @param newPosition The position of the entity being interacted with.
     */
    // collision detection for the entity
    public void manageCollisionAt(Position newPosition){
        String name = getEntityNameAt(newPosition);
        Position currentPosition = this.getDiverPosition();
        int value;
        switch(name){
            // Collects the coin, increases player score and checks if all coins are collected
            case "Coin":
                value = this.getEntityAt(newPosition).getValue();
                this.removeEntityAt(newPosition);
                this.setPlayerScore(this.getPlayerScore()+value);
                this.removeEntityAt(currentPosition);
                this.setEntityAt(diver, newPosition);
                this.setDiverPosition(newPosition);
                this.setCoinsRemaining(this.getCoinsRemaining()-1);
                if(this.allCoinsCollected()){
                    this.openExit();
                }
                break;
            case "TreasureChest":
                value = this.getEntityAt(newPosition).getValue();
                this.removeEntityAt(newPosition);
                this.setPlayerScore(this.getPlayerScore()+value);
                this.removeEntityAt(currentPosition);
                this.setEntityAt(diver, newPosition);
                this.setDiverPosition(newPosition);
                break;
            // Collects the seaweed and removes points from the player
            case "Seaweed":
                value = this.getEntityAt(newPosition).getValue();
                this.removeEntityAt(newPosition);
                this.setPlayerScore(this.getPlayerScore()+value);
                this.removeEntityAt(currentPosition);
                this.setEntityAt(diver, newPosition);
                this.setDiverPosition(newPosition);
                if(playerScore<0){
                    gameLose = true;
                    gameOver = true;
                }
                break;
            // The player wins the game
            case "Exit":
                this.removeEntityAt(newPosition);
                this.removeEntityAt(currentPosition);
                this.setEntityAt(diver, newPosition);
                this.setDiverPosition(newPosition);
                gameWin = true;
                gameOver = true;
                break; 
            // The player loses 
            case "Shark":
                gameLose = true;
                gameOver = true;
                break;
            case "Diver":
                gameLose = true;
                gameOver = true;
                break;
            default:           
        }
    }
    
    
    /** Checks if a given position is valid.
     * @param pos Position on the game board.
     * @return True if the position is valid, false otherwise.
     */
    public Boolean positionisValid(Position pos){
        if(pos.getX()>-1&&pos.getX()<20&&pos.getY()>-1&&pos.getY()<20){
            return true;
        }
        else{
            return false;
        }
    }

    private Boolean moveResponse(Position currentPosition,Position newPosition,Entity entity){
        if(this.positionIsVacant(newPosition)&&positionisValid(newPosition)){
            entities[currentPosition.getX()][currentPosition.getY()] = null;
            entities[newPosition.getX()][newPosition.getY()] = entity;
            entity.setPosition(newPosition);
            return true;
        }
        else if(!this.positionIsVacant(newPosition)&&positionisValid(newPosition)){
            manageCollisionAt(newPosition);
            return true;
        }
        else{
            return false;
        }
    }
    /** Moves the shark up if the space is vacant or occupied by an entity the shark can interact with.
     * @return True if the shark can move upwards, false otherwise.
     */
    public boolean moveSharkUp(){
        Position currentPosition = shark1.getPosition();
        Position newPosition = new Position(shark1.getPosition().getX(),shark1.getPosition().getY()-1);
        return moveResponse( currentPosition,newPosition,shark1);
    }

    
    /** Moves the shark down if the space is vacant or occupied by an entity the shark can interact with.
     * @return True if the shark can move downwards, false otherwise.
     */
    public boolean moveSharkDown(){
        Position currentPosition = shark1.getPosition();
        Position newPosition = new Position(shark1.getPosition().getX(),shark1.getPosition().getY()+1);
        return moveResponse( currentPosition,newPosition,shark1);
    }

    
    /** Moves the shark left if the space is vacant or occupied by an entity the shark can interact with.
     * @return True if the shark can move left, false otherwise.
     */
    public boolean moveSharkLeft(){
        Position currentPosition = shark1.getPosition();
        Position newPosition = new Position(shark1.getPosition().getX()-1,shark1.getPosition().getY());
        return moveResponse( currentPosition,newPosition,shark1);
    }

    
    /** Moves the shark right if the space is vacant or occupied by an entity the shark can interact with.
     * @return True if the shark can move right, false otherwise.
     */
    public boolean moveSharkRight(){
        Position currentPosition = shark1.getPosition();
        Position newPosition = new Position(shark1.getPosition().getX()+1,shark1.getPosition().getY());
        return moveResponse( currentPosition,newPosition,shark1);
    }

    
    /** Moves the diver up if the space is vacant or occupied by an entity the diver can interact with.
     * @return True if the diver can move upwards, false otherwise.
     */
    public boolean moveDiverUp(){
        Position currentPosition = diver.getPosition();
        Position newPosition = new Position(diver.getPosition().getX(),diver.getPosition().getY()-1);
        return moveResponse( currentPosition,newPosition,diver);
    }

    
    /** Moves the diver down if the space is vacant or occupied by an entity the diver can interact with.
     * @return True if the diver can move downwards, false otherwise.
     */
    public boolean moveDiverDown(){
        Position currentPosition = diver.getPosition();
        Position newPosition = new Position(diver.getPosition().getX(),diver.getPosition().getY()+1);
        return moveResponse( currentPosition,newPosition,diver);
    }

    
    /** Moves the diver left if the space is vacant or occupied by an entity the diver can interact with.
     * @return True if the diver can move left, false otherwise.
     */
    public boolean moveDiverLeft(){
        Position currentPosition = diver.getPosition();
        Position newPosition = new Position(diver.getPosition().getX()-1,diver.getPosition().getY());
        return moveResponse( currentPosition,newPosition,diver);
    }

    
    /** Moves the diver right if the space is vacant or occupied by an entity the diver can interact with.
     * @return True if the diver can move right, false otherwise.
     */
    public boolean moveDiverRight(){
        Position currentPosition = diver.getPosition();
        Position newPosition = new Position(diver.getPosition().getX()+1,diver.getPosition().getY());
        return moveResponse( currentPosition,newPosition,diver);
    }

    
    /** Checks if the given position is vacant or not.
     * @param position The given position.
     * @return Returns true if the position is vacant, false otherwise.
     */
    //Checks if given position is occupied or not
    public boolean positionIsVacant(Position position){
        if(entities[position.getX()][position.getY()]==null){
            return true;
        }
        else{
            return false;
        }
    }

    
    /** Returns the entity at a given position. If the position is vacant, returns null.
     * @param position The given position.
     * @return Returns entity if not vacant.
     */
    //Returns the entity at given position, returns null if position vacant
    public Entity getEntityAt(Position position){
        if(!this.positionIsVacant(position)){
            return entities[position.getX()][position.getY()];
        }
        else{
            return null;
        }
    }

    
    /** Gets the name of the entity at a given position. If no entity is there, it returns "Water".
     * @param position The given position.
     * @return The name of the entity.
     */
    public String getEntityNameAt(Position position){
        if(!this.positionIsVacant(position)){
            return entities[position.getX()][position.getY()].getClass().getSimpleName();
        }
        else{
            return "Water";
        }
    }

    
    /** Inserts the given entity at the given position. The entity cannot already be present on the map.
     * @param entity The given entity.
     * @param position The given position.
     */
    //Inserts given entity at given position. Entity cannot already be present on map
    public void setEntityAt(Entity entity, Position position){
        if(this.positionIsVacant(position)){
            entities[position.getX()][position.getY()] = entity;
        }
        else{

        }
    }

    
    /** Removes the entity at the given position.
     * @param pos The position to remove the entity at.
     */
    public void removeEntityAt(Position pos){
        entities[pos.getX()][pos.getY()] = null;
    }

    /** Sets the border walls for the game level.
     *  Used in factory methods
     */
    public void setBorderWalls(){
        for(int i = 0; i<20; i++){
            Position pos = new Position(i, 0);
            this.placeWall(pos);
        }

        for(int i = 0; i<20; i++){
            Position pos = new Position(i, 19);
            this.placeWall(pos);
        }

        for(int i = 0; i<20; i++){
            Position pos = new Position(0, i);
            this.placeWall(pos);
        }

        for(int i = 0; i<20; i++){
            Position pos = new Position(19, i);
            this.placeWall(pos);
        }
    }

}
