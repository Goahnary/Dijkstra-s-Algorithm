package hw5;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

public class HW5pt2 {
	// create JGraphs:
    private static ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> test1 = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>( DefaultWeightedEdge.class );
    private static ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> test2 = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>( DefaultWeightedEdge.class );
    
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
        
        dijkstra(test1);
        dijkstra(test2);
    }
	
	public static void dijkstra(ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge> g){
		Set<String> S = new HashSet<String>();
		Set<String> allVerts = g.vertexSet();
		String u = "a";
		Double[] L = new Double[allVerts.size()];
		
		for(int i = 0; i < allVerts.size(); i++){
			L[i] = Double.POSITIVE_INFINITY;
		}
		
		L[Character.getNumericValue('a')-10] = 0.0;
		S.removeAll(S);
		
		while(!(S.contains("c"))){
			Set<DefaultWeightedEdge> edges = g.edgesOf(u);
			System.out.println(S);
//			DefaultWeightedEdge cheapest = edges.iterator().next();
			String cheapest = g.getEdgeTarget(edges.iterator().next());
			for(DefaultWeightedEdge e : edges){
				//set labels
				L[Character.getNumericValue(g.getEdgeTarget(e).charAt(0))-10] = g.getEdgeWeight(e);
			}
			
			for(String v : allVerts){
				if(!S.contains(v) && L[Character.getNumericValue(v.charAt(0))-10] <= L[Character.getNumericValue(g.getEdgeTarget(cheapest).charAt(0))-10]){
					System.out.println("nc");
					cheapest = v;
				}
			}
			
			u = cheapest;
			//System.out.println(u);
			L[Character.getNumericValue(u.charAt(0))-10] = g.getEdgeWeight(cheapest);
			
			for(String v : allVerts){
				if(!(S.contains(v))){
					System.out.println("u: " + u);
					System.out.println("v: " + v);
					
					Double newPathCost = Double.POSITIVE_INFINITY;
					if(g.getEdge(u,v) != null) {
						newPathCost = L[Character.getNumericValue(u.charAt(0))-10] + g.getEdgeWeight(g.getEdge(u,v));
					}
					Double curPathCost = L[Character.getNumericValue(v.charAt(0))-10];
					if(newPathCost < curPathCost){
						curPathCost = newPathCost;
						S.add(u);
					}
				}
			}
		}
		
		Double lowestCost = L[Character.getNumericValue('c')-10];
		
		System.out.println(S + " " + lowestCost);
	}
}
