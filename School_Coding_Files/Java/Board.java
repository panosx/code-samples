package com.group20;

import java.awt.*;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * Draws the map array on the JPanel.
 */
public class Board extends JPanel {

    private Map map;
    BufferedImage[] allImages;
    JLabel[] labels;
    JLabel[][] board;
    ImageIcon[] icons;
    JLabel playerScore;

    /**
     * Constructor for setting up the game map visually
     * 
     */
    Board(Map gameMap) {

        map = gameMap;

        this.setLayout(null);
        this.setOpaque(false);

        readResouce();

        scaleImage();

        createGameBoard();

        createScoreBoard();

    }

    
    private void createScoreBoard() {
        playerScore = new JLabel("YOUR SCORE:\n" + map.getPlayerScore());
        playerScore.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        playerScore.setForeground(Color.white);
        playerScore.setHorizontalAlignment(JLabel.CENTER);
        playerScore.setVerticalAlignment(JLabel.CENTER);
        playerScore.setPreferredSize(new Dimension(200, 300));
    }

    
    private void createGameBoard() {
        board = new JLabel[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = new JLabel();
                board[i][j].setBounds(i * 30, j * 30, 30, 30);
                // board[i][j].setIcon(icons[6]);
                this.add(board[i][j]);
            }
        }
    }

    
    private void scaleImage() {
        labels = new JLabel[7];
        icons = new ImageIcon[7];
        for (int i = 0; i < 7; i++) {
            labels[i] = new JLabel();
            ImageIcon icon = new ImageIcon(allImages[i]);
            Image image = icon.getImage();
            Image scaled = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaled);
            labels[i].setIcon(icon);
            labels[i].setSize(30, 30);
            icons[i] = icon;
        }
    }

    
    private void readResouce() {
        allImages = new BufferedImage[7];
        try {
            allImages[0] = ImageIO.read(this.getClass().getResource("/coin.png"));
            allImages[1] = ImageIO.read(this.getClass().getResource("/scuba diver.png"));
            allImages[2] = ImageIO.read(this.getClass().getResource("/seaweed.png"));
            allImages[3] = ImageIO.read(this.getClass().getResource("/shark.png"));
            allImages[4] = ImageIO.read(this.getClass().getResource("/treasure chest.png"));
            allImages[5] = ImageIO.read(this.getClass().getResource("/wall.jpg"));
            allImages[6] = ImageIO.read(this.getClass().getResource("/StartBac.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the game map.
     * 
     * @return Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Returns the player's current score.
     * 
     * @return JLabel
     */
    public JLabel getPlayerScoreLabel() {
        return playerScore;
    }

    // redrawn the map, base on the map array
    /**
     * Re-draw the map and the position of the game entities based on the map array.
     */
    public void updateBoard() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Position currPosition = new Position(i, j);
                switch (map.getEntityNameAt(currPosition)) {
                    case "Diver":
                        board[i][j].setIcon(icons[1]);
                        break;
                    case "Shark":
                        board[i][j].setIcon(icons[3]);
                        break;
                    case "Wall":
                        board[i][j].setIcon(icons[5]);
                        break;
                    case "Coin":
                        board[i][j].setIcon(icons[0]);
                        break;
                    case "TreasureChest":
                        board[i][j].setIcon(icons[4]);
                        break;
                    case "Seaweed":
                        board[i][j].setIcon(icons[2]);
                        break;
                    case "Exit":
                        board[i][j].setIcon(null);
                        board[i][j].setText("EXIT");
                    default:
                        board[i][j].setIcon(null);

                }
            }
        }
        playerScore.setText("YOUR SCORE:\n" + map.getPlayerScore());
    }
}
