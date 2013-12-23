/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.*;
import maze.Maze;
import maze.MazeGenerator;
import maze.MazeState;

/**
 *
 * @author steven
 */
public class Astar extends MazeSearch {

    public Astar(Maze maze) {
        super(maze);
        frontier = new PriorityQueue();
        closed = new HashSet();
        AstarNode initialNode = new AstarNode(new MazeState(maze, maze.getStartX(), maze.getStartY()), null, null);
        frontier.add(initialNode);
    }

    public Node findPathToGoal() {
	    
	while (!frontier.isEmpty()) {
            Node n = ((PriorityQueue<Node>) frontier).poll();
            if (!closed.contains(n.getState())) {
                addToClosed(n.getState());
                if (n.getState().isGoal()) {
                    /*
                     * We have found the goal. From the representation of the node
                     * we can easily retrieve the path that has led us from the
                     * initial state to this solution
                     */

						highlightPath(n);
                    	return n;
                } else {
                    List<Action> actions = n.getState().getLegalActions();
                    for (Action action : actions) {
                        State child = n.getState().doAction(action);
                        /* 
                         * Cycle checking: to avoid going in circles, we check if we 
                         * have already encountered this state on the path from the initial
                         * state to the current state
                         */
                        if (!closed.contains(child)){
                            addToFrontier(new AstarNode(child, n, action));
                        }
                    }
                }
            }
        }

        /*
         * We have explored the entire tree, but found no goal state. This 
         * means that the problem does not have a solution.
         */
        System.out.println("No solution found.");
        return null;
    }
    
     public static void main(String[] args) {
     	
		Astar as = new Astar(MazeGenerator.getMaze(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
     		
		as.findPathToGoal();
	}
}
