/* Christian Dresser */
/* 7/11/2021 */
 /**
 * The Class Node.
 */
public class Node {
	/*The name of a node is an integer value between 0 and n − 1.*/
	private int name; 
	
	/*Indicates whether a node has been visited or not.*/
	private boolean marked; 
	
	/*Creates an unmarked node with the given name.*/
	public Node(int nodeName) {
		this.marked = false;
		this.name = nodeName;
	}
	
	
	//marks the node with the specified value.
	public void setMark(boolean mark) {
		this.marked = mark;
		
	}
	//returns the value with which the node has been marked.
	public boolean getMark() {
		return this.marked;
	}
	
	//returns the name of the node.
	public int getName() {
		return this.name;
	}
	
	//returns true of this node has the same name as otherNode; returns false otherwise
	public boolean equals(Node otherNode) {
		if(otherNode.getName() == this.name) {
			return true;
		}
		else {
			return false;
		}
	}
									

}