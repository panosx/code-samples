package com.group20;
import java.util.concurrent.TimeUnit;

/**
 * Calls the eventManager function every tick period. 
 */
public class Tick implements Runnable{
    
    /**
     * Constructor for Tick
     * @param event handles key events
     */  
    Tick(eventManager event){

        this.event=event;
        Thread thread = new Thread(this);
            thread.start();
    }
    /** Moves the player based on the keyboard input every tick period.
     * 
     */
    public void run(){
        while(!event.map.isGameOver()){          
            direction di=event.Direction();
                       
            switch(di){
              case up:
                
              event.map.moveDiverUp();
                event.board.updateBoard();
               
              break;
              case down:
              event.map.moveDiverDown();
                event.board.updateBoard(); 
                
              break;
              case right:
              event.map.moveDiverRight();
                event.board.updateBoard();
                
              break;
              case left:
                event.map.moveDiverLeft();
                event.board.updateBoard();
                
              break;
              case pause:

                
              break;

              
            }
            

            // Checks if the game has ended and if the player has won or lost
            if(event.map.isGameLose()){
              event.frame.dispose();
              event.timer.exit();
              new EndMenu(event.map.getPlayerScore(), false);

            }
            else if (event.map.isGameWin()){
              event.timer.exit();
              event.frame.dispose();
              new EndMenu(event.map.getPlayerScore(), true);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
          }
    }


    eventManager event;
}
