/* Christian Dresser */
/* 7/11/2021 */
import java.util.ArrayList;


/**
 * The Class Graph.
 */
public class Graph implements GraphADT{
	
	/** The number of nodes. */
	private int numNodes;
	
	/** The graph, stored via adjacency list. */
	private ArrayList<Edge>[] graph;
	
	/** The node list. */
	private Node[] nodeList;
	
	
	/**
	 * Instantiates a new graph.
	 *
	 * @param n the number of nodes
	 */
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		this.numNodes = n;
	    graph = new ArrayList[n];//create new array of array lists with a size equal to n
	    nodeList = new Node[n];//create node list
	    
	    for(int i = 0; i < n;i++) {//initialize each edge list
	    	graph[i] = new ArrayList<Edge>();
	    }
	    
		Node node;//create the nodes
	    for(int i = 0;i < numNodes;i++) {
	    	node = new Node(i);
	    	nodeList[i] = node;
	    }
	    
	}
	
	/**
	 * Insert edge.
	 *
	 * @param nodeu the node u
	 * @param nodev the node v
	 * @param type the type of edge
	 * @throws GraphException the graph exception
	 */
	@Override
	public void insertEdge(Node nodeu, Node nodev, int type) throws GraphException {
		if(type != 0) { //if type = 0 its an unbreakable wall
			try {
				nodeu = nodeList[nodeu.getName()];//swap whatever nodes the were given with the same nodes from node list
				nodev = nodeList[nodev.getName()];
			}catch(ArrayIndexOutOfBoundsException e) {
				throw new GraphException("Message");
			}
			Edge newEdge1 = new Edge(nodeu, nodev, type);//create the edge from u to v
			Edge newEdge2 = new Edge(nodev, nodeu, type);//create the edge from v to u
			
			try {
				if(noCopies(newEdge1)) {//make sure this edge is not already in the graph
					graph[nodeu.getName()].add(newEdge1);//add the edge to index corresponding to u
				}
				if(noCopies(newEdge2)) {//make sure no copies 
					graph[nodev.getName()].add(newEdge2);//add edge to index corresponding to v
				}
			}catch(Exception e) {
				e.printStackTrace();
				throw new GraphException("Error");
			}
		}
	}

	/**
	 * Gets a node.
	 *
	 * @param u the name of the node
	 * @return the node
	 * @throws GraphException the graph exception
	 */
	@Override
	public Node getNode(int u) throws GraphException {
		try {
			return nodeList[u];//get the node if it exists
		}catch(Exception e) {
			e.printStackTrace();
			throw new GraphException("Error");
		}
	}

	/**
	 * Finds all edges incident on Node u and returns them in an ArrayList.
	 *
	 * @param u the node
	 * @return the array list of incident edges
	 * @throws GraphException the graph exception
	 */
	@Override
	public ArrayList<Edge> incidentEdges(Node u) throws GraphException {
		try {
			if(graph[u.getName()].size() == 0) {//if its empty, theres no incident edges
				return null;
			}
			else {
				return graph[u.getName()];//otherwise just get the list of edges for Node u
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new GraphException("Error");
		}
		
	}

	/**
	 * Gets the edge connecting nodes u and v.
	 *
	 * @param u the starting node
	 * @param v the ending node
	 * @return the edge
	 * @throws GraphException the graph exception
	 */
	@Override
	public Edge getEdge(Node u, Node v) throws GraphException {
		ArrayList<Edge> edges;
		try {
			edges = graph[u.getName()];
			
			for(Edge edge : edges) {//iterate through list of edges corresponding to node u
				if(edge.secondEndpoint().equals(v)) {//if found edge (u, v), return it
					return edge;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new GraphException("Error");
		}
		return null;//otherwise, return null
	}

	/**
	 * Checks if two nodes are adjacent.
	 *
	 * @param u the first node
	 * @param v the second node
	 * @return true, if u and v are adjacent
	 * @throws GraphException the graph exception
	 */
	@Override
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		ArrayList<Edge> edges;
		try {
			edges = graph[u.getName()];
			
			
			for(Edge edge : edges) {
				if(edge.secondEndpoint().equals(v)) {//if there is an edge connecting u and v, they are adjacent
					return true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new GraphException("Error");
		}
		return false;//otherwise, they are not
	}
	
	/**
	 * Checks if a given edge already exists within the graph.
	 *
	 * @param newEdge the new edge
	 * @return true, if there are no copies of the given edge in the graph
	 */
	private boolean noCopies(Edge newEdge) {
		for(Edge edge : graph[newEdge.firstEndpoint().getName()]) {
			if(edge.equals(newEdge)) {
				return false;
			}
		}
		return true;
		
	}
	


}