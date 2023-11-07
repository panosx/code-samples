package com.group20;

/**
 * Contains methods for creating and updating game levels.
 */
public class DefaultGameFactory extends GameFactory {

    /**
     * Creates an in-game map.
     * 
     * @return Map: Newly created game level.
     */
    public Map makeMap() {

        // Create and place the moving entities on the map
        Map map = new Map();
        Diver diver = new Diver(new Position(1, 9));
        map.setDiver(diver);
        Shark s1 = new Shark(new Position(3, 3));
        map.setSharks(s1);

        placeWalls(map);

        placeCoins(map);
        
        placeSeaweeds(map);
       
        placeTreasureChests(map);

        // Placing the exit
        Exit gameExit = new Exit(new Position(19, 14));
        map.setExit(gameExit);

        return map;
    }

    private void placeTreasureChests(Map map){
        // Placing the collectables
        map.placeTreasureChest(new Position(15, 3));
        map.placeTreasureChest(new Position(5, 13));
    }

    private void placeSeaweeds(Map map){
        // Placing the collectables
        map.placeSeaweed(new Position(7, 4));
        map.placeSeaweed(new Position(17, 7));
        map.placeSeaweed(new Position(16, 13));
        map.placeSeaweed(new Position(4, 16));
        map.placeSeaweed(new Position(8, 15));
        map.placeSeaweed(new Position(7, 9));
        map.placeSeaweed(new Position(13, 9));
        map.placeSeaweed(new Position(10, 13));
        map.placeSeaweed(new Position(15, 18));
    }
    private void placeCoins(Map map){
         // Placing the collectables
         map.placeCoin(new Position(9, 9));
         map.placeCoin(new Position(18, 1));
         map.placeCoin(new Position(7, 16));
         map.placeCoin(new Position(3, 4));
         map.placeCoin(new Position(18, 18));
         map.setCoinsRemaining(5);
    }

    private void placeWalls(Map map) {
        // Placing all walls in default map
        map.setBorderWalls();
        for (int i = 5; i < 17; i++) {
            Position pos = new Position(i, 2);
            map.placeWall(pos);
        }
        map.placeWall(new Position(16, 3));
        map.placeWall(new Position(16, 4));
        for (int i = 2; i < 13; i++) {
            Position pos = new Position(i, 5);
            map.placeWall(pos);
        }
        map.placeWall(new Position(2, 2));
        map.placeWall(new Position(2, 3));
        map.placeWall(new Position(2, 4));
        for (int i = 4; i < 11; i++) {
            Position pos = new Position(i, 7);
            map.placeWall(pos);
        }
        for (int i = 7; i < 15; i++) {
            Position pos = new Position(4, i);
            map.placeWall(pos);
        }
        for (int i = 5; i < 11; i++) {
            Position pos = new Position(i, 14);
            map.placeWall(pos);
        }
        for (int i = 4; i < 17; i++) {
            Position pos = new Position(14, i);
            map.placeWall(pos);
        }
        for (int i = 6; i < 15; i++) {
            Position pos = new Position(i, 10);
            map.placeWall(pos);
        }
        for (int i = 3; i < 12; i++) {
            Position pos = new Position(i, 17);
            map.placeWall(pos);
        }
        map.placeWall(new Position(18, 8));
        map.placeWall(new Position(17, 8));
        map.placeWall(new Position(15, 14));
        map.placeWall(new Position(16, 14));
    }

    /**
     * Create and update the game board.
     * 
     * @param map: A game level.
     * @return Board: The map array on the JPanel.
     */
    // Create and update the board
    public Board makeBoard(Map map) {
        Board board = new Board(map);
        board.updateBoard();
        return board;
    }
}
