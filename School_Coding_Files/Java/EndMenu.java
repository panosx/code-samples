package com.group20;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * Menu that appears upon losing the game.
 */
public class EndMenu extends JFrame{

	
	JLabel label;
	
	
	EndMenu(int plScore, boolean result){		
        // Setup the Window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,800); //Set window size to 1000x800 pixels
		this.setLayout(null);
		
        // Creates and fits a background for the window
		ImageIcon backGround = new ImageIcon();
        try{
            if(result == true){ //if the player wins
                backGround = new ImageIcon(ImageIO.read(this.getClass().getResource("/WinBac.png"))); //display the win screen
            }
            else{ //if the player loses
                backGround = new ImageIcon(ImageIO.read(this.getClass().getResource("/LoseBac.png"))); //display the lose screen
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Image image = backGround.getImage();
        Image scaledImage = image.getScaledInstance(1000,800 , java.awt.Image.SCALE_SMOOTH); //rescale the background image
        backGround = new ImageIcon(scaledImage);
        this.setContentPane((new JLabel(backGround)));
    
        
        // Stacks the Buttons vertically
		JPanel buttonsC = new JPanel();
        buttonsC.setLayout(new BoxLayout(buttonsC,BoxLayout.Y_AXIS));

        // Create elements to be added to the JFrame
        ClickButton startButton = new ClickButton("Main Menu");
        ClickButton restartButton = new ClickButton("Restart");
        ClickButton exitButton = new ClickButton("Exit Game");

        // Adds the player's score to the display
        JLabel score = new JLabel("SCORE: " + plScore);
        score.setFont((new Font("Serif", Font.PLAIN, 50)));
        score.setForeground(Color.black);
        
        // Adds the time elapsed to the display
        JLabel time = new JLabel("TIME: " + Timer.time);
        time.setFont((new Font("Serif", Font.PLAIN, 50)));
        time.setForeground(Color.black);

        // Centers the buttons, the time and the score
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        time.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Opens the appropriate windows when buttons are clicked
		startButton.addActionListener(new ActionListener() { //Go to the start menu
            @Override
            public void actionPerformed(ActionEvent e) {
				new StartMenu(); //go to the start menu
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() { //Exit game
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
				System.exit(0); //quit the game
            }
        });
        restartButton.addActionListener(new ActionListener() { //Restart the game
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultGameFactory factory1 = new DefaultGameFactory();
                new GameInstance(factory1); //remake the game level
                dispose();
            }
        });
        
        // Adds the buttons, score and time to the JFrame
        buttonsC.add(score);
        buttonsC.add(time);
		buttonsC.add(startButton);
        buttonsC.add(exitButton);
        buttonsC.add(restartButton);

        // Places the Frame in a specific position 
        Dimension size = buttonsC.getPreferredSize();
        buttonsC.setBounds(350,400,size.width, size.height);

        // Removes any decorations from the frame and adds the buttons
        setUndecorated(true);
		getContentPane().add((buttonsC));
        this.getContentPane().setLayout(null);

		setVisible(true); //make this window visible
	}

}