/* Christian Dresser */
/* 7/11/2021 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

/**
 * The Class Solver.
 */
public class Solver {
	
	/** The Labyrinth. */
	private Graph Labyrinth;
	
	/** The width and length of the labyrinth. */
	private int width, length;
	
	/** The number of blast and melt bombs. */
	private int bBombs, mBombs;
	
	/** The values of the entrance and exit nodes. */
	private int entrance, exit;
	
	/** The stack containing the path taken. */
	private Stack<Node> path = new Stack<Node>();
	
	/** The previous edges in the path. */
	private Stack<Edge> prevEdges = new Stack<Edge>();
	
	/**
	 * Instantiates a new solver.
	 *
	 * @param inputFile the input file
	 * @throws LabyrinthException the labyrinth exception
	 */
	public Solver(String inputFile) throws LabyrinthException {
		BufferedReader br;
		int count = 0;
		char[][] cArr;//will hold labyrinth
		try {
			br = new BufferedReader(new FileReader(inputFile));
			br.readLine();
			width = Integer.parseInt(br.readLine());//read first 4 lines
			length = Integer.parseInt(br.readLine());
			bBombs = Integer.parseInt(br.readLine());
			mBombs = Integer.parseInt(br.readLine());
			Labyrinth = new Graph(width*length);//create new graph based on number of nodes in labyrinth 
			cArr = new char[(2*length)-1][(2*width)-1];//create char array
			
			String s;
			int lCount = 0;
			while(br.ready()) {
				s = br.readLine();
				for(int i = 0;i < (2*width)-1;i++) {//read file into 2d char array
					cArr[lCount][i] = s.charAt(i);
				}
				lCount++;
			}
			
			
			Node[] nodeList = new Node[width*length];//put all the nodes into a list so i can use them to make edges
			for(int i = 0;i < cArr.length;i++) {
				for(int j = 0;j < cArr[0].length;j++) {				
					switch(cArr[i][j]) {
					case 'e':
						entrance = count;//get the value of the entrance node
						nodeList[count] = new Node(count);
						count++;
						break;
					case 'o': 
						nodeList[count] = new Node(count);
						count++;
						break;
					case 'x':
						exit = count;//get value of exit node
						nodeList[count] = new Node(count);
						count++;
						break;
					default:
						break;
					}
					
				}
			}


			//Basically go through char array. If finds a room check for edges up, down, left, and right of the room.
			//Because rooms are numbered from left to right, top to bottom, the end point for each edge is already known.
			//Create a new edge based on the edge character and store it in the graph.
			int upEdgeType = -1;
			int dEdgeType = -1;
			int rEdgeType = -1;
			int lEdgeType = -1;
			count = 0;
			for(int i = 0;i < cArr.length;i++) {
				for(int j = 0;j < cArr[0].length;j++) {
					switch(cArr[i][j]){
					case 'e':
					case 'x':
					case 'o':
						try {
							upEdgeType = getEdgeType(cArr[i-1][j]);
							if(upEdgeType != 0) {
								Labyrinth.insertEdge(nodeList[count], nodeList[count-width], upEdgeType);
							}
							
						}catch(Exception e) {
							
						}
						
						try {
							rEdgeType = getEdgeType(cArr[i][j+1]);
							if(rEdgeType != 0) {
								Labyrinth.insertEdge(nodeList[count], nodeList[count+1], rEdgeType);
							}
						}catch(Exception e) {
							
						}
						
						try {
							dEdgeType = getEdgeType(cArr[i+1][j]);
							if(dEdgeType != 0) {
								Labyrinth.insertEdge(nodeList[count], nodeList[count+width], dEdgeType);
							}
						}catch(Exception e) {
							
						}
						
						try {
							lEdgeType = getEdgeType(cArr[i][j-1]);
							if(lEdgeType != 0) {
								Labyrinth.insertEdge(nodeList[count], nodeList[count-1], rEdgeType);
							}
						}catch(Exception e) {
							
						}
						count++;
						break;
					default:
						break;
					}
					
				}

			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LabyrinthException("File Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Gets the edge type associated with a given character.
	 *
	 * @param c the character
	 * @return the edge type
	 */
	private int getEdgeType(char c) {
		switch(c) {
		case 'b':
		case 'B':
			return 2;//brick
		case 'r':
		case 'R':
			return 3;//rock
		case 'm':
		case 'M':
			return 4;//metal
		case '-':
		case '|':
			return 1;//corridor
		case '*':
			return 0;//unbreakble
		default:
			return -1;//something wrong
		}
	}

	
	/**
	 * Gets the graph.
	 *
	 * @return the graph
	 * @throws LabyrinthException the labyrinth exception
	 */
	public Graph getGraph() throws LabyrinthException {
		return this.Labyrinth;
	}
	
	/**
	 * Find a valid path from the entrance to the exit of the labyrinth.
	 *
	 * @return An iterator for a stack containing the path
	 * @throws LabyrinthException the labyrinth exception
	 */
	public Iterator<Node> solve() throws LabyrinthException {
		try {
			path(Labyrinth.getNode(entrance), Labyrinth.getNode(exit));//make path
			if(path.size() == 0) {
				return null;//if no solution, return null
			}
			else {
				return path.iterator();//return stack iterator
			}
		} catch (GraphException e) {

		}
		return null;//something goes wrong, return null
	}

	
	
	/**
	 * Path traversal of graph.
	 *
	 * @param entrance the entrance of the labyrinth
	 * @param exit the exit of the labyrinth
	 * @return true, if a path is found
	 * @throws GraphException the graph exception
	 */
	private boolean path(Node entrance, Node exit) throws GraphException {
		entrance.setMark(true);//mark current node as visited
		path.push(entrance);//push it onto stack
		
		if(entrance.equals(exit)) {//if it's the exit, we're done, path has been found
			return true;
		}
		
		for(Edge edge : Labyrinth.incidentEdges(entrance)) {//for each edge incident on the current node
			//check if it is valid to move to the second end point of the edge
			Node next = edge.secondEndpoint();
			
			if(next.getMark() == false) {//make sure its not marked
				
				if(edge.getType() == 1) {//if its a corridor, might as well take it
					prevEdges.push(edge);//push the edge onto stack
					if(path(next, exit)) {
						return true;
					}//end if path
				}//end if type == 1
				
				else if((edge.getType() == 2) && (bBombs >= 1)) {//otherwise, if its brick and we have bBomb, use it
					prevEdges.push(edge);//push the edge onto stack
					bBombs--;//used a bomb
					if(path(next, exit)) {//goto next node
						return true;
					}//end if path
				}//end else if edge type == 2
				
				else if((edge.getType() == 3) && (bBombs >= 2)) {//if its a rock wall and we have two bBombs, use them
					prevEdges.push(edge);//push edge onto stack
					bBombs = bBombs - 2;//used two bombs
					if(path(next, exit)) {//move to next node
						return true;
					}//end if path()
				}//end else if edge type == 3
				
				else if((edge.getType() == 4) && (mBombs >= 1)) {//if its a metal wall, use a mBomb, if we have any
					prevEdges.push(edge);//push edge onto stack
					mBombs--;//used a melt bomb
					if(path(next, exit)) {//move to next node
						return true;
					}//end if path()
				}//end else if edge type == 4
			}//end if mark == false
			
		}//end for
		//otherwise, if there are no valid moves at a given node, backtrack
		try {
			Edge prev = prevEdges.pop();//pop the last edge off the edge stack
			if(prev.getType() == 2) {//if we used a bomb for that edge, give us back the bombs we used
				bBombs++;
			}
			else if(prev.getType() == 3) {
				bBombs = bBombs + 2;
			}
			else if(prev.getType() == 4) {
				mBombs++;
			}
		}catch(EmptyStackException e) {
			
		}
		path.pop().setMark(false);//mark the node as false in case we need to go there some other way and pop the node off the path stack
		return false;//return false, i.e. try next edge of previous node
	}
	
	

}