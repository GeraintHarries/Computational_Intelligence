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
public class IterativeDeepening extends MazeSearch {

    Node initialNode;

    public IterativeDeepening(Maze maze) {
        super(maze);
        initialNode = new Node(new MazeState(maze, maze.getStartX(), maze.getStartY()), null, null);
    }

    public Node findPathToGoal() {
        for (int depthLimit = 1;; depthLimit++) {
            restart();
            frontier = new Stack();
            addToFrontier(initialNode);
            closed = new HashSet();

            while (!frontier.isEmpty()) {
                Node n = ((Stack<Node>) frontier).pop();
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
                            if (!closed.contains(child) && n.getDepth() <= depthLimit) {
                                addToFrontier(new Node(child, n, action));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        IterativeDeepening id = new IterativeDeepening(MazeGenerator.getMaze(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
        id.findPathToGoal();
    }
}
