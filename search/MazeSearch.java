/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import maze.Maze;
import maze.MazeGenerator;
import maze.MazeState;
import maze.StdDraw;

/**
 *
 * @author steven_and_geraint
 */
public abstract class MazeSearch {

    Collection<Node> frontier;
    Collection<Node> frontier1;
    Collection<Node> frontier2;
    Set<State> closed;
    Set<State> closed2;
    Maze maze;
    Node n;
    
    public MazeSearch(Maze maze) {
        this.maze = maze;
        StdDraw.show(0);
        maze.draw();
    }

    public abstract Node findPathToGoal();

    protected void addToFrontier(Node n) {
        frontier.add(n);
        MazeState st = (MazeState) n.getState();
        maze.drawBlue(st.getX(), st.getY());
    }
    
    protected void addToFrontier2(Node n) {
        frontier2.add(n);
        MazeState st = (MazeState) n.getState();
        maze.drawBlue(st.getX(), st.getY());
    }
    protected void addToClosed(State st0) {
        closed.add(st0);
        MazeState st = (MazeState) st0;
        maze.drawGray(st.getX(), st.getY());
    }

    protected void addToClosed2(State st0){
        closed2.add(st0);
        MazeState st = (MazeState) st0;
        maze.drawGray2(st.getX(), st.getY());
    }

    protected void highlightPath(Node n) {
        List<State> path = n.getPath();
        for (State st : path) {
            MazeState ms = (MazeState) st;
            maze.highlight(ms.getX(), ms.getY());
        }
    }

    protected void restart() {
        maze.clearDots();
    }
}
