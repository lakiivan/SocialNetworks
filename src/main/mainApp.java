package main;

import graph.CapGraph;
import util.GraphLoader;

public class mainApp {

	public static void main(String[] args) {
		System.out.println("Hello World");
		CapGraph cg = new CapGraph();
		System.out.println(cg.exportGraph());
		GraphLoader gl = new GraphLoader();
		
		//String filePath = "data/small_test_graph.txt";
		String filePath = "data/facebook_1000.txt";
		gl.loadGraph(cg, filePath);
		System.out.println(cg.exportGraph());
	}
}
