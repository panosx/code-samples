package com.group20;
import java.util.concurrent.TimeUnit;
/**
 * Controls the shark once every game tick.
 */
public class tickShark extends Thread{
    
  tickShark(eventManager event){
      this.event=event;
  }
  // Moves the player based on the algorithm
  /** Moves the shark based on the BFS algorithm.
   * 
   */
  @Override
  public void run(){
    direction[] sharkDi;
    int index=0;
    BFS shark=new BFS(event.map);
    sharkDi=shark.Search();
    while(!event.map.isGameOver()){
      if (index==sharkDi.length){
        shark=new BFS(event.map);
        index=0;
        sharkDi=shark.Search();
      }

      System.out.print("Ther return direction is:"+sharkDi[index].name());

      switch(sharkDi[index]){
        case up:
          event.map.moveSharkUp();
          event.board.updateBoard();
          
        break;
        case down:
          event.map.moveSharkDown();
          event.board.updateBoard(); 
          
        break;
        case right:
          event.map.moveSharkRight();
          event.board.updateBoard();
          
        break;
        case left:
          event.map.moveSharkLeft();
          event.board.updateBoard();
          
        break;
        case pause:

        break;
      }
      shark=null;
      index++;
      // Checks if the game has ended and if the player has lost
      if(event.map.isGameLose()){
        event.frame.dispose();
        new EndMenu(event.map.getPlayerScore(), false);
      }
      try {
        TimeUnit.MILLISECONDS.sleep(600);
      } 
      catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
    } 
  }
  eventManager event;
}
