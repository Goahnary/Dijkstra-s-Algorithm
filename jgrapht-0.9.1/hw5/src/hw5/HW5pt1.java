 package hw5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.*;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.*;

public class HW5pt1 extends JApplet {
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 720, 720 );
    
    private JGraphModelAdapter m_jgAdapter;
	
	public void init(  ) {
        // create JGraphs:
        ListenableUndirectedWeightedGraph<String, MyWeightedEdge> test1 = new ListenableUndirectedWeightedGraph<String, MyWeightedEdge>( MyWeightedEdge.class );
        ListenableUndirectedWeightedGraph<String, MyWeightedEdge> test2 = new ListenableUndirectedWeightedGraph<String, MyWeightedEdge>( MyWeightedEdge.class );

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( test1 ); //@INST: CHANGE TO GRAPH YOU WANT TO TEST!!!!

        JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );

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

        // position vertices within JGraph component
        positionVertexAt( "a", 40, 40 );
        positionVertexAt( "b", 240, 40 );
        positionVertexAt( "c", 440, 190 );
        positionVertexAt( "d", 40, 340 );
//        positionVertexAt( "e", 240, 340 ); //@INST: UNCOMMENT FOR test2!!!!!
    }
	
	private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }
	
	private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D        b    = GraphConstants.getBounds( attr );
        
//        System.out.println(b.getBounds2D());

        GraphConstants.setBounds( attr, new Rectangle( x, y, 100, 100 ) );

        Map cellAttr = new HashMap();
        cellAttr.put( cell, attr );
//        m_jgAdapter.edit( cellAttr, null, null, null, null );
    }
}
