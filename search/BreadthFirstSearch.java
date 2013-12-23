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
public class BreadthFirstSearch extends MazeSearch {

    public BreadthFirstSearch(Maze maze) {
        super(maze);
        frontier = new LinkedList();
        closed = new HashSet();
        Node initialNode = new Node(new MazeState(maze, maze.getStartX(), maze.getStartY()), null, null);
        addToFrontier(initialNode);
    }

    public Node findPathToGoal() {
        while (!frontier.isEmpty()) {
            Node n = ((LinkedList<Node>) frontier).removeFirst();
            /* 
             * Multiple path pruning: avoid that states are considered
             * more than once.
             */
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
                        if (!closed.contains(child)) {
                            addToFrontier(new Node(child, n, action));
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
        BreadthFirstSearch bfs = new BreadthFirstSearch(MazeGenerator.getMaze(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
        bfs.findPathToGoal();
    }
}
