package hw5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

public class HW5pt2 {
	// create JGraphs:
    private static ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> test1 = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>( DefaultWeightedEdge.class );
    private static ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> test2 = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>( DefaultWeightedEdge.class );
    
    static int resultNum = 0;
    
	public static void main(String[] args) {    
        // Adding Vertices
        // test1
        test1.addVertex( "a" );
        test1.addVertex( "b" );
        test1.addVertex( "c" );
        test1.addVertex( "d" );
        // test2
        test2.addVertex( "a" );
        test2.addVertex( "b" );
        test2.addVertex( "c" );
        test2.addVertex( "d" );
        test2.addVertex( "e" );

        // Adding Edges:
        // test1
        test1.addEdge( "a", "b" );
        test1.addEdge( "b", "c" );
        test1.addEdge( "c", "d" );
        test1.addEdge( "d", "a" );
        test1.addEdge( "d", "b" );
        // test2
        test2.addEdge( "a", "b" );
        test2.addEdge( "a", "d" );
        test2.addEdge( "b", "c" );
        test2.addEdge( "b", "e" );
        test2.addEdge( "c", "e" );
        test2.addEdge( "d", "e" );
        
        // Setting Edge Weights:
        // test1
        test1.setEdgeWeight(test1.getEdge( "a", "b" ), 1.0);
        test1.setEdgeWeight(test1.getEdge( "b", "c" ), 1.0);
        test1.setEdgeWeight(test1.getEdge( "c", "d" ), 2.0);
        test1.setEdgeWeight(test1.getEdge( "d", "a" ), 3.0);
        test1.setEdgeWeight(test1.getEdge( "d", "b" ), 2.0);
        // test2
        test2.setEdgeWeight(test2.getEdge( "a", "b" ), 9.0);
        test2.setEdgeWeight(test2.getEdge( "a", "d" ), 1.0);
        test2.setEdgeWeight(test2.getEdge( "b", "c" ), 1.0);
        test2.setEdgeWeight(test2.getEdge( "b", "e" ), 2.0);
        test2.setEdgeWeight(test2.getEdge( "c", "e" ), 6.0);
        test2.setEdgeWeight(test2.getEdge( "d", "e" ), 2.0);
        
        dijkstra(test1, "a", "c");
        dijkstra(test2, "a", "c");
    }
	
	public static void dijkstra(ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> g, String initNode, String endNode){
		
		//Variable creation/assignment
		resultNum++;
		int loopCnt = 0; // Keep count of loops
		String currNode = initNode; // always starts as initial node
		
		Set<String> allVerts = new LinkedHashSet<String>();
		allVerts = g.vertexSet(); //All vertices in the graph
		
		Set<DefaultWeightedEdge> allEdges = new LinkedHashSet<DefaultWeightedEdge>();
		allEdges = g.edgeSet(); //All edges in the graph
		
		Set<String> visited = new LinkedHashSet<String>(); //List of visited vertices
		Set<String> unvisited = new LinkedHashSet<String>(); //List of unvisited vertices
		for(String v : allVerts)
			//adding the rest of the nodes to unvisited set
			{
				if(v != initNode){
					unvisited.add(v);
				}
			}
		
		Map<String, Double> vertVals = new HashMap<String, Double>();
		for(String v : allVerts)
		//setting a value of INFINITY to all vertices.
		{
			vertVals.put(v, Double.POSITIVE_INFINITY);
		}
		
		vertVals.put(initNode, 0.0); // Setting the initial node value to 0
		visited.add(initNode); //adding initial node to visited set
		List<String> path = new ArrayList<String>();
		path.add(initNode); //adding initial node to path
		//END Variable creation/assignment
		
		
		System.out.println("Result " + resultNum + ":");
		
		while(!visited.contains(endNode) && loopCnt < 1000000){
			
			loopCnt++;
			
			for(DefaultWeightedEdge e : allEdges)
			//Set weight values for neighbors of current node
			{	
				if(g.getEdgeSource(e) == currNode && g.getEdgeWeight(e) < (vertVals.get(g.getEdgeTarget(e)) + vertVals.get(currNode)) && !visited.contains(g.getEdgeTarget(e)) )
				// if the edge comes from the currentNode AND the value of that edges target is greater than the distance to the current node AND the target hasn't been visited
				{
					vertVals.put(g.getEdgeTarget(e), g.getEdgeWeight(e) + vertVals.get(currNode)); // Update value of that vertex
				} else if(g.getEdgeTarget(e) == currNode && g.getEdgeWeight(e) < (vertVals.get(g.getEdgeSource(e)) + vertVals.get(currNode)) && !visited.contains(g.getEdgeSource(e)))
				// if the edge comes to the currentNode AND the value of that edges source is greater than the distance from the starting node to the current node AND the source hasn't been visited
				{
					vertVals.put(g.getEdgeSource(e), g.getEdgeWeight(e) + vertVals.get(currNode)); // Update value of that vertex
				}
				
			}
			
			Double lowest = Double.POSITIVE_INFINITY; // used to find lowest of the current neighbor vertices
			String n = ""; //temporary string for storing vertex names 
						
			for(DefaultWeightedEdge e : allEdges)
			//finding the lowest of the current neighbor vertices to set to currentNode
			{
				if(g.getEdgeSource(e) == currNode && g.getEdgeWeight(e) < lowest && !visited.contains(g.getEdgeTarget(e)))
				//if the edge comes from the currentNode AND it's the smallest vertex value that has not been visited and .
				{
					lowest = g.getEdgeWeight(e);
					n = g.getEdgeTarget(e);
				} else if(g.getEdgeTarget(e) == currNode && g.getEdgeWeight(e) < lowest && !visited.contains(g.getEdgeSource(e))){
					lowest = g.getEdgeWeight(e);
					n = g.getEdgeSource(e);
				}
			}
			if(n != ""){
				currNode = n;
				visited.add(currNode);
				path.add(currNode);
			} else {
				currNode = path.get(path.size()-2);
				visited.add(currNode);
			}
		}
		
		System.out.println("Shortest Path: " + path);
		System.out.println("Cost: " + vertVals.get(endNode));
		System.out.println();
	}
}
