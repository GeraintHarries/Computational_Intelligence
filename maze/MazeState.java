/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.ArrayList;
import java.util.List;
import search.Action;
import search.State;

/**
 *
 * @author steven
 */
public class MazeState implements State {

    private Maze maze;
    private int x;
    private int y;
    public static final MoveEast moveEast = new MoveEast();
    public static final MoveWest moveWest = new MoveWest();
    public static final MoveNorth moveNorth = new MoveNorth();
    public static final MoveSouth moveSouth = new MoveSouth();

    public MazeState(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Maze getMaze(){
        return maze;
    }
    
    public List<Action> getLegalActions() {
        List<Action> result = new ArrayList();
        if (!maze.hasEastWall(x, y)) {
            result.add(moveEast);
        }
        if (!maze.hasWestWall(x, y)) {
            result.add(moveWest);
        }
        if (!maze.hasSouthWall(x, y)) {
            result.add(moveSouth);
        }
        if (!maze.hasNorthWall(x, y)) {
            result.add(moveNorth);
        }
        return result;
    }

    public boolean isGoal() {
        return x == maze.getGoalX() && y == maze.getGoalY();
    }

    public boolean isStart() {
	return x == maze.getStartX() && y == maze.getStartY();
    }

    public void printState() {
        System.out.println("(" + x + "," + y + ")");
    }

    public boolean isLegal(Action action) {
        if (maze.hasEastWall(x, y) && (action instanceof MoveEast)) {
            return false;
        } else if (maze.hasWestWall(x, y) && (action instanceof MoveWest)) {
            return false;
        } else if (maze.hasSouthWall(x, y) && (action instanceof MoveSouth)) {
            return false;
        } else if (maze.hasNorthWall(x, y) && (action instanceof MoveNorth)) {
            return false;
        } else {
            return true;
        }
    }

    public State doAction(Action action) {
        MazeAction ma = (MazeAction) action;
        return new MazeState(maze,ma.updateX(x),ma.updateY(y));
    }

    public int getEstimatedDistanceToGoal() {
        return Math.abs(x - maze.getGoalX()) + Math.abs(y - maze.getGoalY());
    }
   
    public int getEstimatedDistanceToStart() {
	return Math.abs(x - maze.getStartX()) + Math.abs(y - maze.getStartY());
    }
 
    public boolean equals (Object o){
        if(!(o instanceof MazeState))
            return false;
        MazeState ms = (MazeState) o;
        return x==ms.x && y==ms.y && maze==ms.maze;
    }
    
    public int hashCode(){
        return x+1024*y; 
    }
}
