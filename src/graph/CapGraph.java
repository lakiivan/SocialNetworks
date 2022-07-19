/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	
	private HashMap<Integer, HashSet<Integer>> nodes;
	
	public CapGraph() {
		this.nodes = new HashMap<>();
	}
	
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (!this.nodes.containsKey(num)) {
			this.nodes.put(num, new HashSet<>());
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (this.nodes.containsKey(from) && this.nodes.containsKey(to)) {
			HashSet<Integer> fromSet = this.nodes.get(from);
			if (!fromSet.contains(to)) {
				fromSet.add(to);
			}
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// check whether center node exist in nodes 
		if (!this.exportGraph().containsKey(center)) {
			return null;
		} else {
			// init new Graph
			Graph egonet = new CapGraph();
			egonet.addVertex(center);
			// find all neighbors of center node as HashSet primary neighbors
			HashSet<Integer> primaryNeighbors = this.nodes.get(center);
			//add all neighbor vertices to graph
			for (int neighbor : primaryNeighbors) {
				egonet.addVertex(neighbor);
			}
			//System.out.println("Primary neighbors");
			//System.out.println(primaryNeighbors);
			//for every neighbor in primary neighbors add vertex to newly created graph 
			for (int neighbor : primaryNeighbors) {
				//System.out.println("Neighbor: " + neighbor);
				egonet.addEdge(center, neighbor);
				HashSet<Integer> currNeighbors = this.nodes.get(neighbor);
				//System.out.println("Curr neighbors: " + currNeighbors);
				// for every neighbors node if their neighbors are in neighbors list add edge to new graph
				for (int currNeighbor : currNeighbors) {
					//System.out.println("Curr neighbor: " + currNeighbor);
					if (primaryNeighbors.contains(currNeighbor) || currNeighbor == center) {
						egonet.addEdge(neighbor, currNeighbor);
					}
					//System.out.println("curr egonet graph: " + egonet.exportGraph());
				}
			}
			//System.out.println(egonet.exportGraph());
			return egonet;
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Stack<Integer> getStackFromSet() {
		Stack<Integer> nodes = new Stack<Integer>();
		for (int node : this.exportGraph().keySet()) {
			nodes.push(node);
		}
		return nodes;
	}
	
	public Stack<Integer> dfs(Stack<Integer> nodes) {
		Stack<Integer> visited = new Stack<Integer>();
		Stack<Integer> finished = new Stack<Integer>();
		while (!nodes.isEmpty()) {
			int node = nodes.pop();
			if (!visited.contains(node)) {
				dfsVisit(node, visited, finished);
			}
		}
		return finished;
	}
	
	public void dfsVisit(int node, Stack<Integer> visited, Stack<Integer> finished) {
		// place last visited node on top of finished stack and returns it
		// check whether node is in the graph g
		if (this.exportGraph().containsKey(node)) {
			// add node to visited stack
			visited.add(node);
			HashSet<Integer> neighbors = this.exportGraph().get(node);
			for (int neighbor : neighbors) {
				if (!visited.contains(neighbor)) {
					dfsVisit(neighbor, visited, finished);
				}
			}
		}
		finished.push(node);
	}
	
	public Stack<Integer> transposeStack(Stack<Integer> original) {
		Stack<Integer> transposed = new Stack<Integer>();
		Stack<Integer> cloned = (Stack<Integer>)original.clone();
		//System.out.println("cloned: " + cloned);
		while (cloned.size() > 0) {
			int currValue = cloned.pop();
			transposed.push(currValue);
		}
		return transposed;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return this.nodes;
	}

}
