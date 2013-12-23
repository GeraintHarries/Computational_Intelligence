/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import search.Action;

/**
 *
 * @author steven
 */
public class MoveSouth extends MazeAction{

    public int updateX(int x) {
        return x;
    }

    public int updateY(int y) {
        return y-1;
    }        
}
