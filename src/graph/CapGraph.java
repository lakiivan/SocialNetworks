/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return this.nodes;
	}

}
