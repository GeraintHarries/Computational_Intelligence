/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.*;

/**
 *
 * @author steven
 */
public class BidirectionalNode extends Node implements Comparable<BidirectionalNode>{

    public BidirectionalNode(State st, Node previousNode, Action lastAction) {        
        super(st,previousNode,lastAction);
    }
    
    public int compareTo(BidirectionalNode n){
        int score = cost + st.getEstimatedDistanceToStart();
        int scoreN = n.cost + n.st.getEstimatedDistanceToStart();
        return score - scoreN;
    }
    
}
