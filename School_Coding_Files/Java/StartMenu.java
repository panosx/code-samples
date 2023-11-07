package com.group20;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The start menu. The player can choose to start a game or exit.
 */
public class StartMenu extends JFrame{

	
	JLabel label;
	private JButton button;
	
	StartMenu(){	
        // Setup the Window	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,800);
		this.setLayout(null);

        // Creates and fits a background for the window
		ImageIcon backGround = new ImageIcon();
        try{
            backGround = new ImageIcon(ImageIO.read(this.getClass().getResource("/StartBac.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Image image = backGround.getImage();
        Image scaled = image.getScaledInstance(1000,800 , java.awt.Image.SCALE_SMOOTH);
        backGround = new ImageIcon(scaled);
        this.setContentPane((new JLabel(backGround)));
    
        
		JPanel buttonsC = new JPanel();
        // Stacks the Buttons vertically
        buttonsC.setLayout(new BoxLayout(buttonsC,BoxLayout.Y_AXIS));

        // Create elements to be added to the JFrame
        ClickButton startButton = new ClickButton("Start Game");
        ClickButton exitButton = new ClickButton("Exit Game");

		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Opens the appropriate windows when buttons are clicked
		startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				DefaultGameFactory factory1 = new DefaultGameFactory();
                GameInstance test = new GameInstance(factory1);
                dispose();
				//System.exit(0);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				
                dispose();
				System.exit(0);
            }
        });
        
         // Adds the buttons the the JFrame
		buttonsC.add((startButton));
        buttonsC.add((exitButton));

        // Places the Frame in a specific position 
        Dimension size = buttonsC.getPreferredSize();
        buttonsC.setBounds(350,500,size.width, size.height);

        setUndecorated(true);
		getContentPane().add((buttonsC));
        this.getContentPane().setLayout(null);

		setVisible(true);
	}
}