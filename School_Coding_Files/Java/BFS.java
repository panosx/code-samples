package com.group20;
import java.util.Queue;
import java.util.LinkedList;


/**
 * The BFS algorithm that the sharks use to chase the player over the game board.
 */
public class BFS {
    Map map;
    Queue<direction[]> directionQueue= new LinkedList<>();
    private int [][]maze=new int[20][20];
    
    private Position start;
    //3 is destination
    //2 is block being searched
    //1 is wall
    BFS(Map map){
        this.map=map;
        this.start=map.getShark1Position();
        // convert the map to an 2d array containing number
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                Position currPosition = new Position(i, j);
                switch (map.getEntityNameAt(currPosition)){
                    case "Diver":
                        maze[i][j]=3;
                        
                        break;
                    case "Shark":
                        maze[i][j]=4;
                        break;
                    case "Wall":
                        maze[i][j]=1;
                        break; 
                    case "Coin":
                        maze[i][j]=1;
                        break;
                    case "TreasureChest":
                        maze[i][j]=1;
                        break;
                    case "Seaweed":
                        maze[i][j]=1;
                        break;
                    case "Exit":
                        maze[i][j]=1;      
                    default:
                    maze[i][j]=0;    

                }
            }
        }

      

    }


    /**
     * This method will find a path using BFS
     * @return return the first direction enum 
     */
    public direction[] Search(){
        Position currentPosition;
        //using BFS to search
        while(true){
            
            if(directionQueue.size()==0){
                if(maze[start.getX()][start.getY()+1]==3){
                    direction value[]={direction.down};
                    return value;
                 }
                 if(maze[start.getX()][start.getY()-1]==3){
                    direction value[]={direction.up};
                    return value;
                 }
                 if(maze[start.getX()+1][start.getY()]==3){
                    direction value[]={direction.right};
                    return value;
                 }
                 if(maze[start.getX()-1][start.getY()]==3){
                    direction value[]={direction.left};
                    return value;
                 }
                if(maze[start.getX()][start.getY()+1]==0){
                    direction[] inputPath={direction.down};
                    
                    directionQueue.add(inputPath);
                    maze[start.getX()][start.getY()+1]=2;
                }
                if(maze[start.getX()][start.getY()-1]==0){
                    direction[] inputPath={direction.up};
                    
                    directionQueue.add(inputPath);
                    maze[start.getX()][start.getY()-1]=2;
                }
                if(maze[start.getX()+1][start.getY()]==0){
                    direction[] inputPath={direction.right};
                   
                    directionQueue.add(inputPath);
                    maze[start.getX()+1][start.getY()]=2;
                }
                if(maze[start.getX()-1][start.getY()]==0){
                    direction[] inputPath={direction.left};
                    
                    directionQueue.add(inputPath);
                    maze[start.getX()][start.getY()+1]=2;
                }
               
                
            }
            else{

                currentPosition = new Position(start.getX(),start.getY());
                direction[] topath = directionQueue.remove();
                
                int dx = 0;
                int dy = 0;
                for (int i = 0;i<topath.length;i++){
                    switch(topath[i]){
                        case down:
                            dy++;
                        break;
                        case up:
                            dy--;
                        break;
                        case right:
                            dx++;
                        break;
                        case left:
                            dx--;
                        break;
                        case pause:
                        break;
                    }
                }
                currentPosition.setX(currentPosition.getX()+dx);
                currentPosition.setY(currentPosition.getY()+dy);
                
                if(maze[currentPosition.getX()][currentPosition.getY()+1]==0){

                    direction[] inputPath=addOnPath(topath,direction.down);
                    
                    directionQueue.add(inputPath);
                }
                if(maze[currentPosition.getX()][currentPosition.getY()-1]==0){
                    
                    direction[] inputPath=addOnPath(topath,direction.up);
                    
                    directionQueue.add(inputPath);
                }
                if(maze[currentPosition.getX()+1][currentPosition.getY()]==0){
                    
                    direction[] inputPath=addOnPath(topath,direction.right);
                   
                    directionQueue.add(inputPath);
                }
                if(maze[currentPosition.getX()-1][currentPosition.getY()]==0){
                    
                    direction[] inputPath=addOnPath(topath,direction.left);
                    
                    directionQueue.add(inputPath);
                }

                if(maze[currentPosition.getX()][currentPosition.getY()+1]==3){
                    direction[] inputPath=addOnPath(topath,direction.down);
                    return inputPath;
                 }
                 if(maze[currentPosition.getX()][currentPosition.getY()-1]==3){
                    direction[] inputPath=addOnPath(topath,direction.up);
                    return inputPath;
                 }
                 if(maze[currentPosition.getX()+1][currentPosition.getY()]==3){
                    direction[] inputPath=addOnPath(topath,direction.right);
                    return inputPath;
                 }
                 if(maze[currentPosition.getX()-1][currentPosition.getY()]==3){
                    direction[] inputPath=addOnPath(topath,direction.left);
                    return inputPath;
                 }

            }
        }

            
    }
     

    
    /** Adds on new elements to an old array.
     * @param previous
     * @param addOn
     * @return direction[]
     */
    //expand a old array
    private direction[] addOnPath(direction[] previous,direction addOn){
        direction[] current=new direction[previous.length+1];
        for(int i=0;i <previous.length;i++){
            current[i]=previous[i];
        }
        current[previous.length]=addOn;
        return current;
    }
}
