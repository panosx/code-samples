package com.group20;
import javax.swing.*;
import java.awt.event.*;

/**
 * Listens to keyboard input and manages the tick of the game.
 */
public class eventManager implements KeyListener{

    int up,down,right,left;
    JTextArea ta;
    Board board;
    Map map;
    Diver diver;
    JFrame frame;
    tickShark ts=new tickShark(this);
    Timer timer;
    //private eventManager event=new eventManager();    
    public eventManager( Board playarea, JFrame frame,Timer timer){
        this.timer=timer;
        this.frame = frame;
        map = playarea.getMap();
        board = playarea;
        up=0;
        down=0;
        right=0;
        left=0;
        ts.start();
    }

    
    /** Invoked when a key has been typed.
     * @param e: Keyboard input.
     */
    @Override
    public void keyTyped(KeyEvent e){
    }


    
    /** Invoked when a key has been pressed.
     * @param e: Keyboard input.
     */
    @Override
    public void keyPressed(KeyEvent e){
       
		switch(e.getKeyCode()) {
            case 37: 
           
                left=1;
                
                break;
            case 38: 
            
                up=1;
                break;
            case 39: 
            
                right=1;
                break;
            case 40:
           
                down=1;
                break;
        }
    }

    
    
    /** Invoked when a key has been released.
     * @param e: Keyboard input.
     */
    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()) {
            case 37: 
           
                left=0;
                
                break;
            case 38: 
            
                up=0;
                break;
            case 39: 
            
                right=0;
                break;
            case 40:
           
                down=0;
                break;
        }
    }

    
    /** Called each tick by the event manager to get the direction of the player.
     * @return direction: The player's current direction.
     */
    //each tick, event manager will call this function to get the direction
     public direction Direction(){
         int add=(up+down+right+left);
         if(add>1 || add==0 ){
             
             return direction.pause;
         }
         else if(up==1){
             return direction.up;
         }
         else if(down==1){
             return direction.down;
         }
         else if(left==1){
             return direction.left;
         }
         else {
             return direction.right;
         }
         
     }
     

     Tick tick=new Tick(this);

}
