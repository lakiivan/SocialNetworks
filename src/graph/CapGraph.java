/**
 * 
 */
package graph;

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
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

}
