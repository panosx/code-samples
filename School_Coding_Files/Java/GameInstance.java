package com.group20;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Creates an instance of the game. Sets up the window for the game and starts the player at the main menu.
 */
public class GameInstance {
    JFrame frame;


    GameInstance(GameFactory factory){
        // Setup the Window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(815,635);
        readBackgournd();

        setElements(factory);
       
        
        frame.setVisible(true);
    }

    private void readBackgournd(){
        // Creates and fits a background for the window
        ImageIcon backGround = new ImageIcon();
        try {
            backGround = new ImageIcon(ImageIO.read(this.getClass().getResource("/zbackGround.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = backGround.getImage();
        Image scaled = image.getScaledInstance(815, 635, java.awt.Image.SCALE_SMOOTH);
        backGround = new ImageIcon(scaled);

        frame.setContentPane(new JLabel(backGround));
        frame.setLayout(new FlowLayout());

        frame.setLayout(new BorderLayout());
    }

    private void setElements(GameFactory factory){
         // Add elementes to the Frame
         Map map = factory.makeMap();
         Board board = factory.makeBoard(map);
         Timer timer = new Timer();
         eventManager keylistener = new eventManager(board, this.frame,timer);
         JPanel scoreboard = new JPanel();
         scoreboard.setLayout(new BorderLayout());
         JLabel scoreText = board.getPlayerScoreLabel();
         
         timer.setHorizontalAlignment(JLabel.CENTER);
         timer.setVerticalAlignment(JLabel.CENTER);
         scoreboard.add(scoreText, BorderLayout.NORTH);
         scoreboard.add(timer, BorderLayout.SOUTH);
         scoreboard.setOpaque(false);
         
         board.setBounds(0,0,600,600); 
         frame.addKeyListener(keylistener); 
         board.setPreferredSize(new Dimension(600,600));
         timer.setPreferredSize(new Dimension(200, 300));
 
         frame.add(board, BorderLayout.CENTER);
         frame.add(scoreboard, BorderLayout.EAST);
    }
}
