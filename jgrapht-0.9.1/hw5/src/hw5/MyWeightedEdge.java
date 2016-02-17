package hw5;

import org.jgrapht.graph.DefaultWeightedEdge;

public class MyWeightedEdge extends DefaultWeightedEdge{
	public String toString() {
	    return Double.toString(getWeight());
	  }
}
