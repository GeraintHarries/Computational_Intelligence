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
public abstract class MazeAction implements Action{
    
    public int getCost() {
        return 1;
    }
    
    public abstract int updateX(int x);
    public abstract int updateY(int y);
    
}
