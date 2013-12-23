package search;

import java.util.*;
import maze.Maze;
import maze.MazeGenerator;
import maze.MazeState;

/**
 *
 * @author steven_and_geraint
 */
public class BidirectionalSearch extends MazeSearch {

	/**
	 *
	 *	** BIDIRECTIONAL SEARCH **
	 *
	 *	INPUT: Maze
	 *
	 *	DESCRIPTION: Constructor
	 *
	 **/
        public BidirectionalSearch(Maze maze) {	
        	

		super(maze);

        	frontier 			= new PriorityQueue(); 									// THIS IS THE FRONTIER FROM THE EDGE
		frontier2 			= new PriorityQueue(); 									// THIS IS THE FRONTIER FROM THE CENTRE
        	closed 				= new HashSet(); 									// NODES READ BY FRONTIER1
        	closed2 			= new HashSet(); 									// NODES READ BY FRONTIER2
		AstarNode initialNode 		= new AstarNode(new MazeState(maze, maze.getStartX(), maze.getStartY()), null, null);
		BidirectionalNode goalNode 		= new BidirectionalNode(new MazeState(maze, maze.getGoalX(), maze.getGoalY()), null, null);
		Node n 				= new AstarNode(new MazeState(maze, maze.getStartX(), maze.getStartY()), null, null);

		frontier.add(initialNode);
		frontier2.add(goalNode);


    	}

	/**
	 *
	 *	** FINDPATHTOGOAL **
	 *
	 *	INPUT: None
	 *
	 *	DESCRIPTION: This is the bidirectional algorithm. 
	 *	
	 *	Rather than seeing if two frontiers meet, the frontiers keep going until they meet a closed list
	 *	They then conclude the node before (or PreviousNode) must be the others frontier and therefore say there is a match
	 *	Though this potentially adds 3 nodes to the nodes expanded, it saves computational time as you don't need to iterate 
	 *	through each closed list when you add another node.
	 *
	 **/
    	public Node findPathToGoal() {
		
		boolean OneSmallerThanTwo = false;
		Node Check;

        	while (!frontier.isEmpty() || !frontier2.isEmpty()) {
	    	
			if((frontier.size() > 0) && (frontier.size() < frontier2.size())){ 
				
	    			n = ((PriorityQueue<Node>) frontier).poll();
            			
	  	  		if (!closed.contains(n.getState())) {
                
					addToClosed(n.getState());
              					
					if(closed2.contains(n.getState())){
						
						highlightPath(n);
						
						/**
						 *
						 * This is the code which sees if the two frontiers have met
						 *
						 * "If the previousnode matches the node of the other frontier (or vice versa)"
						 * " highlight path"
						 *
						 *
						 **/

						Node n2 	= null; 
						int x 		= frontier2.size();
				
						for(int  i = 0; i < x; i++){
					
							n2 = ((PriorityQueue<Node>) frontier2).poll();
							
							if(n2.previousNode.getState().equals(n.getState()) && n.previousNode.getState().equals(n2.getState())) {
					
								highlightPath(n2);
							
							}
						
						}
						
						return n;

					}
					
					else {
                    				
						List<Action> actions = n.getState().getLegalActions();
                    				
						for (Action action : actions) {
                        				
							State child = n.getState().doAction(action);
                        				
							if (!closed.contains(child)){
                            					
								addToFrontier(new AstarNode(child, n, action));
                        				
							}
                    				
						}
                			
					}
            			
				}
        		
			}
			
			else{
            			
				n = ((PriorityQueue<Node>) frontier2).poll();
	    			
				if (!closed2.contains(n.getState())) {
                
					addToClosed2(n.getState());

					if(closed.contains(n.getState())){

						highlightPath(n);
						
						Node n2 	= null;
						int x 		= frontier.size();

						for(int  i = 0; i < x; i++){
							
							n2 = ((PriorityQueue<Node>) frontier).poll();
							
							if(n2.previousNode.getState().equals(n.getState()) && n.previousNode.getState().equals(n2.getState())) {
								
								highlightPath(n2);
	
							}
						
						}
			
						return n2;

                			} 
					
					else {
                    
		    				List<Action> actions = n.getState().getLegalActions();
                    
		    				for (Action action : actions) {
                        
							State child = n.getState().doAction(action);
                        
							if (!closed2.contains(child)){
                            					
								addToFrontier2(new BidirectionalNode(child, n, action));
                        				
							}
                    				
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
	/**
	 *
	 *	MAIN
	 *
	 *	INPUT: String
	 *
	 *	DESCRIPTION: Main
	 *
	 **/
    public static void main(String[] args) {
     
	
		BidirectionalSearch as = new BidirectionalSearch(MazeGenerator.getMaze(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		as.findPathToGoal();

				
	}	
}
