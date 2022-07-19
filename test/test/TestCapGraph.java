package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import graph.CapGraph;

/**
 * @author Ivan Local
 *
 */

public class TestCapGraph {

	/**
	 * 
	 * testing method add vertex
	 * HashMap nodes size should be zero at beginning
	 * after adding one size should be one
	 * after adding already existing node size should remain the same
	 */
	@Test
	public void testAddVertex() {
		CapGraph cg = new CapGraph();
		int expected = 0;
		int actual = cg.exportGraph().size();
		//int actual = 0;
		assertEquals(expected, actual);
		//fail("Not yet implemented");
		
		cg.addVertex(0);
		expected = 1;
		actual = cg.exportGraph().size();
		assertEquals(expected, actual);
		
		cg.addVertex(1);
		expected = 2;
		actual = cg.exportGraph().size();
		assertEquals(expected, actual);

		cg.addVertex(2);
		expected = 3;
		actual = cg.exportGraph().size();
		assertEquals(expected, actual);

		cg.addVertex(1);
		expected = 3;
		actual = cg.exportGraph().size();
		assertEquals(expected, actual);
	}
	
	/**
	 * test method add edges
	 * Check the size edges after creating node
	 * Check the size of edges after adding one edge
	 * Check the size of edges after adding one more node
	 * Check the size of the edges after adding node already in HashSet
	 */
	@Test
	public void testAddEdge() {
		CapGraph cg = new CapGraph();
		cg.addVertex(0);
		int expected = 0;
		int actual = cg.exportGraph().get(0).size();
		assertEquals(expected, actual);
		
		//adding edge when from node doesn't exist in nodes 
		cg.addEdge(1, 0);
		expected = 0;
		actual = cg.exportGraph().get(0).size();
		assertEquals(expected, actual);

		//adding edge when to node doesn't exist in nodes 
		cg.addEdge(0, 1);
		expected = 0;
		actual = cg.exportGraph().get(0).size();
		assertEquals(expected, actual);
	
		// add new node and after that one edge for first two nodes
		cg.addVertex(1);
		cg.addEdge(0, 1);
		expected = 1;
		actual = cg.exportGraph().get(0).size();
		assertEquals(expected, actual);
		
		// add second edge for first two nodes
		cg.addEdge(1, 0);
		expected = 2;
		actual = cg.exportGraph().get(0).size() + cg.exportGraph().get(1).size();
		assertEquals(expected, actual);	
		
	}
	
	/**
	 * Test adding vertices and edges by comparing two HashMaps after adding all vertices and edges
	 */
	@Test
	public void testAddVertexAndAddEdgeExportMap() {
		CapGraph cg = new CapGraph();
		for (int i = 0; i < 5; i++) {
			cg.addVertex(i);
		}
		cg.addEdge(0, 1);
		cg.addEdge(1, 0);
		cg.addEdge(0, 2);
		cg.addEdge(2, 4);
		cg.addEdge(4, 3);
		cg.addEdge(3, 1);
		
		
		HashMap<Integer, HashSet<Integer>> expected = new HashMap<Integer, HashSet<Integer>>();
		HashMap<Integer, HashSet<Integer>> actual = cg.exportGraph();
		
		HashSet<Integer> currSet = new HashSet<Integer>();
		currSet.add(1);
		currSet.add(2);
		expected.put(0, currSet);
		
		HashSet<Integer> currSet2 = new HashSet<Integer>();
		currSet2.add(4);
		expected.put(2, currSet2);
		
		HashSet<Integer> currSet3 = new HashSet<Integer>();
		currSet3.add(3);
		expected.put(4, currSet3);

		HashSet<Integer> currSet4 = new HashSet<Integer>();
		currSet4.add(1);
		expected.put(3, currSet4);

		HashSet<Integer> currSet5 = new HashSet<Integer>();
		currSet5.add(0);
		expected.put(1, currSet5);
		
		assertEquals(expected, actual);	
		
	}
	
	
	/**
	 * Test getEgonet by comparing two graphs
	 */
	@Test
	public void testGetEgonet() {
		//System.out.println("testGetEgonet");
		CapGraph cg = new CapGraph();
		for (int i = 0; i < 5; i++) {
			cg.addVertex(i);
		}
		cg.addEdge(0, 1);
		cg.addEdge(0, 2);
		cg.addEdge(1, 0);
		cg.addEdge(1, 2);
		cg.addEdge(2, 0);
		cg.addEdge(2, 1);
		cg.addEdge(2, 4);
		cg.addEdge(4, 3);
		cg.addEdge(3, 1);
		
		HashMap<Integer, HashSet<Integer>> actual = cg.getEgonet(0).exportGraph();
		//System.out.println("actual = ");
		//System.out.println(actual);
		
		CapGraph cge = new CapGraph();
		cge.addVertex(0);
		cge.addVertex(1);
		cge.addVertex(2);
		cge.addEdge(0, 1);
		cge.addEdge(0, 2);
		cge.addEdge(1, 0);
		cge.addEdge(1, 2);
		cge.addEdge(2, 0);
		cge.addEdge(2, 1);
		
		HashMap<Integer, HashSet<Integer>> expected = cge.exportGraph();
		//System.out.println("expected = ");
		//System.out.println(expected);
		assertEquals(expected, actual);	
		
	}
	
	/**
	 * Test getEgonet by comparing two graphs
	 */
	@Test
	public void testGetEgonet2() {
		
		CapGraph cg = new CapGraph();
		for (int i = 0; i < 5; i++) {
			cg.addVertex(i);
		}
		cg.addEdge(0, 1);
		cg.addEdge(0, 2);
		cg.addEdge(1, 0);
		cg.addEdge(1, 2);
		cg.addEdge(2, 0);
		cg.addEdge(2, 1);
		cg.addEdge(2, 4);
		cg.addEdge(4, 3);
		cg.addEdge(3, 1);
		cg.addEdge(3, 4);
		

		HashMap<Integer, HashSet<Integer>> actual = cg.getEgonet(3).exportGraph();
		//System.out.println("actual = ");
		//System.out.println(actual);
		
		CapGraph cge = new CapGraph();
		cge.addVertex(1);
		cge.addVertex(3);
		cge.addVertex(4);
		cge.addEdge(3, 1);
		cge.addEdge(4, 3);
		cge.addEdge(3, 4);
		HashMap<Integer, HashSet<Integer>> expected = cge.exportGraph();
		//System.out.println("expected = ");
		//System.out.println(expected);

		assertEquals(expected, actual);	
	}
	
	/**
	 * Test getEgonet if vertex is not present in the graph
	 */
	@Test
	public void testGetEgonetMissingVertex() {
		CapGraph cg = new CapGraph();
		for (int i = 0; i < 5; i++) {
			cg.addVertex(i);
		}
		cg.addEdge(0, 1);
		cg.addEdge(0, 2);
		cg.addEdge(1, 0);
		cg.addEdge(1, 2);
		cg.addEdge(2, 0);
		cg.addEdge(2, 1);
		cg.addEdge(2, 4);
		cg.addEdge(4, 3);
		cg.addEdge(3, 1);
		cg.addEdge(3, 4);

		CapGraph actual = (CapGraph) cg.getEgonet(-5);
		boolean isNull = false;
		if (actual == null) {
			isNull = true;
		}
		
		assertEquals(isNull, true);	
		
	}

	/**
	 * Testing Stack transpose method
	 */
	@Test
	public void testTransposeStack() {
		CapGraph cg = new CapGraph();
		Stack<Integer> original = new Stack<Integer>();
		original.push(1);
		original.push(2);
		original.push(3);
		original.push(4);
		original.push(5);
		//System.out.println("original: " + original);
		
		Stack<Integer> expected = new Stack<Integer>();
		for (int i = 5; i > 0; i--) {
			expected.push(i);
		}
		//System.out.println("expected: " + expected);
		
		Stack<Integer> actual = cg.transposeStack(original);
		//System.out.println("acutal: " + actual);
		
		assertEquals(expected, actual);
		
	}
	
	/**
	 * Test method trasnposeStack on empty stack
	 */
	public void testTrasnposeStackEmpty() {
		CapGraph cg = new CapGraph();
		Stack<Integer> expected = new Stack<Integer>();
		Stack<Integer> actual = cg.transposeStack(expected);
		
		assertEquals(expected, actual);
		
	}
}
