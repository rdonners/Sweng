

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

//Using JUNIT FrameWork
public class DagTest {
	
	Dag emptyGraph = new Dag(0);
	Dag singleElementGraph = new Dag(1);
	Dag stickGraph = new Dag(6);
	Dag wideShortGraph = new Dag(6);
	Dag binaryGraph = new Dag(12);
	Dag edgelessGraph = new Dag(5);
	Dag nonNuclearGraph = new Dag(9);	//A graph with multiple occurences of multiple parents
	Dag wikiGraph = new Dag (6); 		//Based on wikipedia visual example of a DAG
	//Dag acyclyicalGraph = graph;
	
	
	@Test
	public void testEmpty()
	{
	//	assertEquals("Testing LCA on Empty Graph", -1, emptyGraph.findLCA());
	}
	@Test
	public void testSingleElementGraph()
	{
		//assertEquals("Testing LCA on Single Element Graph", -1, singleElementGraph.findLCA(0,1));
	}
	@Test
	public void testGraph()
	{
		Dag graph = new Dag (12);

		// Graph directon starting at the top and directed down ( 3 -> 6 is exception)
				//
				//	  	0
				//	   / \
				//	  2	  1
				//   /| X |
				//  3-6   4
				//    |\
				//    7 5
				//	  |
				//	  8
				
				graph.addEdge(0, 1);
				graph.addEdge(0, 2);
				graph.addEdge(1, 4);
				graph.addEdge(1, 6);
				graph.addEdge(2, 3);
				graph.addEdge(2, 4);
				graph.addEdge(2, 6);
				graph.addEdge(6, 5);
				graph.addEdge(6, 7);
				graph.addEdge(7, 8);
		assertEquals("Testing LCA on Random Graph", 1, graph.findLCA(7,4));
		assertEquals("Testing LCA on Random Graph", 1, graph.findLCA(4,7));
		assertEquals("Testing where LCA of two vetices is one of the vertices",6,graph.findLCA(5,6));
		assertEquals("Vertices both exist but are disconnected",-1,graph.findLCA(10, 2));
	}
	@Test
	public void testStickGraph()
	{
		//	  0
		//	  |
		//	  1
		//	  |
		//	  2
		//	  |
		//	  3
		//	  |
		//	  4
		stickGraph.addEdge(0, 1);
		stickGraph.addEdge(1, 2);
		stickGraph.addEdge(2, 3);
		stickGraph.addEdge(3, 4);
		
		assertEquals("Testing Stick Graph",1,stickGraph.findLCA(1,4));
		
		//Adding Isolated Vertex
		//	  0
		//	  |\
		//	  1	5
		//	  |
		//	  2
		//	  |
		//	  3
		//	  |
		//	  4

		stickGraph.addEdge(0, 5);
		assertEquals("Testing Stick Graph",0,stickGraph.findLCA(2,5));
	}
	@Test
	public void testWideShortGraph()
	{
		// 				0
		//		 /	 /	|  \   \	
		//		/	/	|	\	\
		//		1	2	3	4	5
		wideShortGraph.addEdge(0, 1);
		wideShortGraph.addEdge(0, 2);
		wideShortGraph.addEdge(0, 3);
		wideShortGraph.addEdge(0, 4);
		wideShortGraph.addEdge(0, 5);
		assertEquals("Testing Wide Short Graph", 0, wideShortGraph.findLCA(1, 5));
		assertEquals("Testing Wide Short Graph", 0, wideShortGraph.findLCA(2, 3));
	}
	
	@Test
	public void testBinaryGraph()
	{
		//			  0
		//		  /		   \
		//		 1	 	   2
		//		/ \  	  / \
		//	   3   4  	 5   6
		binaryGraph.addEdge(0,1);
		binaryGraph.addEdge(0,2);
		binaryGraph.addEdge(1,3);
		binaryGraph.addEdge(1,4);
		binaryGraph.addEdge(2,5);
		binaryGraph.addEdge(2,6);
		
		assertEquals("Testing Balanced Binary Graphs", 0, binaryGraph.findLCA(3, 6));

		//			  0
		//		  /		   \
		//		 1	 	   2
		//		/ \  	  / \
		//	   3   4  	 5   6
//		//    / |  | \
		//	 7  8  9  10
		
		binaryGraph.addEdge(3,7);
		binaryGraph.addEdge(3,8);
		binaryGraph.addEdge(4,9);
		binaryGraph.addEdge(4,10);
		assertEquals("Testing UnBalanced Binary Graphs", 1, binaryGraph.findLCA(7, 10));		
	}
	@Test
	public void testEdgelessGraph()
	{
		assertEquals("Testing Edgeless Graph",-1, edgelessGraph.findLCA(2, 3));
	}
	@Test
	public void testnonNuclearGraph()
	{
		
		//	  	0
		//	   / \
		//	  2	  1
		//   /| X |
		//  4 6   3
		//    	\/\ \
		//   	 7 5-8
		nonNuclearGraph.addEdge(0, 1);
		nonNuclearGraph.addEdge(0, 2);
		nonNuclearGraph.addEdge(2, 4);
		nonNuclearGraph.addEdge(2, 6);
		nonNuclearGraph.addEdge(2, 3);
		nonNuclearGraph.addEdge(1, 6);
		nonNuclearGraph.addEdge(1, 3);
		nonNuclearGraph.addEdge(6, 7);
		nonNuclearGraph.addEdge(3, 7);
		nonNuclearGraph.addEdge(3, 5);
		nonNuclearGraph.addEdge(3, 8);
		nonNuclearGraph.addEdge(5, 8);

		
		
		assertEquals("Testing NonNuclear Graph",1,nonNuclearGraph.findLCA(6, 3));
		assertEquals("Testing NonNuclear Graph Side By Side",5,nonNuclearGraph.findLCA(5, 8));
		assertEquals("Testing NonNuclear Graph Cross",1,nonNuclearGraph.findLCA(6, 3));
		assertEquals("Testing NonNuclear Graph Layer",2,nonNuclearGraph.findLCA(5, 4));
	}
	@Test
	public void testWiki()
	{
	//			0
	//		 / / \  \
	//      1  |  2  \
	//		 \ | / 	 |
	//         3 	 |
	//				 4
	// Based on wikepedia example of a directed acyclical graph https://en.wikipedia.org/wiki/Directed_acyclic_graph
		wikiGraph.addEdge(0,1);
		wikiGraph.addEdge(0, 2);
		wikiGraph.addEdge(0, 3);
		wikiGraph.addEdge(0, 4);
		wikiGraph.addEdge(1, 3);
		wikiGraph.addEdge(2, 3);
		
		assertEquals("Testing Wiki e.g", 0, wikiGraph.findLCA(3, 4));
		assertEquals("Testing Wiki e.g same elements", 3, wikiGraph.findLCA(3,3));


	}
	
	
	/*
	@Test
	public void testLCA()
	{
		
		
		
		
		
		
		
		
		
		
		
		Dag test = new Dag(12);
		
		// Testing before edges are added
		//assertEquals(-1,test.findLCA(4,7));
		
		// Graph directon starting at the top and directed down ( 3 -> 6 is exception)
		//
		//	  	0
		//	   / \
		//	  2	  1
		//   /| X |
		//  3-6   4
		//    |\
		//    7 5
		//	  |
		//	  8
		
		test.addEdge(0, 1);
		test.addEdge(0, 2);
		test.addEdge(1, 4);
		test.addEdge(1, 6);
		test.addEdge(2, 3);
		test.addEdge(2, 4);
		test.addEdge(2, 6);
		test.addEdge(6, 5);
		test.addEdge(6, 7);
		test.addEdge(7, 8);
		
		// Testing basic functionality
		assertEquals(1,test.findLCA(4,7));
		assertEquals(1,test.findLCA(7,4));
		
		 //Testing where one value is ancestor of another
		assertEquals(7,test.findLCA(7,8));
		
		// Testing where node is created but not connected
		assertEquals(-1,test.findLCA(10, 7));
		
		// Joining nodes 6 -> 4 and retesting basic functionality
		test.addEdge(6, 4);	
		assertEquals(6, test.findLCA(7,4));
		
		// Testing on directed CYCLICAL graph
		test.addEdge(8, 0);
		assertEquals(-1,test.findLCA(4, 7));
		
	}
	*/
	
}