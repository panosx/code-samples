package com.group20;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Window that is opened when the game runs.
 */
public class gameWindow extends JFrame{
    
    // Creates window
    gameWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,800);
        setLayout(new BorderLayout());
        setVisible(true);
        getContentPane().setBackground(Color.black);

        // Creates background image
        ImageIcon backGround = new ImageIcon();
        try {
            backGround = new ImageIcon(ImageIO.read(this.getClass().getResource("resources/images/backGround.jpg")));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        setContentPane(new JLabel(backGround));

        Timer timer=new Timer();
        JPanel timerPane =new JPanel(new BorderLayout());
        timerPane.add(timer);
        timerPane.setBackground(Color.black);
        add(timerPane, BorderLayout.EAST);   
    }   
}
