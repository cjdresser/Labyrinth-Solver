/* Christian Dresser */
/* 7/11/2021 */
/**
 * The Class Edge.
 */
public class Edge {
	
	/** The first and second end-points of the edge. */
	private Node firstEndpoint, secondEndpoint; 
	
	/** The type of edge. */
	private int type;// 1 = corridor, 2 = brick wall, 3 = rock wall, 4 = metal wall
	
	/**
	 * Instantiates a new edge.
	 *
	 * @param u the start point
	 * @param v the the end point
	 * @param edgeType the edge type
	 */
	public Edge(Node u, Node v, int edgeType) {
		this.firstEndpoint = u;
		this.secondEndpoint = v;
		this.type = edgeType;
	}
	
	/**
	 * First endpoint.
	 *
	 * @return the node
	 */
	public Node firstEndpoint() {
		return this.firstEndpoint;
	}
	
	/**
	 * Second endpoint.
	 *
	 * @return the node
	 */
	public Node secondEndpoint() {
		return this.secondEndpoint;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param newType the new type
	 */
	public void setType(int newType) {
		this.type = newType;
	}
	
	/**
	 * Checks if an edge is equal to another edge.
	 *
	 * @param otherEdge the other edge
	 * @return true, if they are equal
	 */
	public boolean equals(Edge otherEdge) {
		if((this.firstEndpoint.equals(otherEdge.firstEndpoint())) && (this.secondEndpoint.equals(otherEdge.secondEndpoint()))){
			return true;
		}
		
		else if((this.firstEndpoint.equals(otherEdge.secondEndpoint())) && (this.secondEndpoint.equals(otherEdge.firstEndpoint()))) {
			return true;
		}
		else {
			return false;
		}
		
	}

}