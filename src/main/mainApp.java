package main;

import java.util.Stack;

import graph.CapGraph;
import util.GraphLoader;

public class mainApp {

	public static void main(String[] args) {
		System.out.println("Hello World");
		CapGraph cg = new CapGraph();
		System.out.println(cg.exportGraph());
		GraphLoader gl = new GraphLoader();
		
		String filePath = "data/small_test_graph.txt";
		//String filePath = "data/facebook_1000.txt";
		gl.loadGraph(cg, filePath);
		System.out.println(cg.exportGraph());
		
		Stack<Integer> original = new Stack<Integer>();
		original.push(1);
		original.push(2);
		original.push(3);
		original.push(4);
		original.push(5);
		System.out.println("original: " + original);
		Stack<Integer> transposed = cg.transposeStack(original);
		System.out.println("trasnposed: " + transposed);
	}
}
