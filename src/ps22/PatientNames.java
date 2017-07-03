package ps22;

import java.util.*;
import java.io.*;

//write your matric number here: A0138915X
//write your name here: Chong Ze Xuan
//write list of collaborators here:
//year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class PatientNames {
	// --------------------------------------------
	Name_BST malePatientsList, femalePatientsList;
	HashMap<String, Integer> patientsGenderMap;
	// --------------------------------------------

	public PatientNames() {
		// --------------------------------------------
		this.malePatientsList = new Name_BST();
		this.femalePatientsList = new Name_BST();
		this.patientsGenderMap = new HashMap<String, Integer>(20000); //20k patients
		// --------------------------------------------
	}

	void AddPatient(String patientName, int gender) {
		// --------------------------------------------
		if(gender == 1) {
			malePatientsList.insert(patientName);
		} else if(gender == 2) {
			femalePatientsList.insert(patientName);
		}
		patientsGenderMap.put(patientName, gender);
		// --------------------------------------------
	}

	void RemovePatient(String patientName) {
		// --------------------------------------------
		if(patientsGenderMap.get(patientName) == 1) {
			malePatientsList.delete(patientName);
		} else {
			femalePatientsList.delete(patientName);
		}
		// --------------------------------------------
	}

	int Query(String START, String END, int gender) {
		// --------------------------------------------
		int ans = 0;
		if(gender == 0) {
			ans = 2 + (malePatientsList.getRankBySubstring(END) - malePatientsList.getRankBySubstring(START) 
					 + femalePatientsList.getRankBySubstring(END) - femalePatientsList.getRankBySubstring(START));
		} else if (gender == 1) {
			ans = 1 + malePatientsList.getRankBySubstring(END) - malePatientsList.getRankBySubstring(START);
		} else {
			ans = 1 + femalePatientsList.getRankBySubstring(END) - femalePatientsList.getRankBySubstring(START);
		}
		return ans;
		// --------------------------------------------
	}

	void run() throws Exception {
		// do not alter this method to avoid unnecessary errors with the automated judging
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddPatient
				AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemovePatient
				RemovePatient(st.nextToken());
			else // if (command == 3) // Query
				pr.println(Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method to avoid unnecessary errors with the automated judging
		PatientNames ps2 = new PatientNames();
		ps2.run();
	}
	
	public Name_BST getMaleBST() {
		return this.malePatientsList;
	}
	
	public Name_BST getFemaleBST() {
		return this.femalePatientsList;
	}
}

class Name_BSTVertex implements Comparable<Name_BSTVertex> {
	private Name_BSTVertex parent, left, right;
	private String key_name;
	private int height;
	private int balance_factor;

	public Name_BSTVertex(String key_name, Name_BSTVertex parent) {
		this.key_name = key_name;
		this.parent = parent;
		this.left = null;
		this.right = null;
		
		this.height = 0; //assumption that vertex will always be leaf?
	}

	// getter/setter methods
	public Name_BSTVertex getParent() {
		return this.parent;
	}

	public Name_BSTVertex getLeft() {
		return this.left;
	}

	public Name_BSTVertex getRight() {
		return this.right;
	}
	
	public String getName() {
		return this.key_name;
	}

	public int getHeight() {
		return this.height;
	}
	public int getBalance_factor() {
		return balance_factor;
	}

	public void setParent(Name_BSTVertex parent) {
		this.parent = parent;
	}

	public void setLeft(Name_BSTVertex left) {
		this.left = left;
	}

	public void setRight(Name_BSTVertex right) {
		this.right = right;
	}
	
	public void setName(String key_name) {
		this.key_name = key_name;
	}

	public void setHeight(int height) {
		this.height = height;
		this.balance_factor = calculateBalanceFactor();
	}
	public void setBalance_factor(int balance_factor) {
		this.balance_factor = balance_factor;
	}
	
	private int calculateBalanceFactor() {
		int bf = 0;
		if(this.getLeft() == null && this.getRight() == null) {
			bf = -2;
		} else if(this.getLeft() == null) {
			bf = this.getRight().getHeight() + 1; // right - (-1)
		} else if(this.getRight() == null) {
			bf = this.getLeft().getHeight() + 1; //left - (-1)
		} else {
			bf = this.getLeft().getHeight() - this.getRight().getHeight();
		}
		
		return bf < 0 ? -bf : bf;
	}
	

	@Override
	public int compareTo(Name_BSTVertex o) {
		return this.key_name.compareTo(o.key_name);
	}

	@Override
	public String toString() {
		String output = new String();
		
		output = output.concat("NAME: " + key_name +",HEIGHT: " + height+",BF: " + balance_factor);
		
		return output;
	}
}

class Name_BST {
	private Name_BSTVertex root;
	private HashMap<String, Integer> nameRankMapCache;

	public Name_BST() {
		this.root = null;
		this.nameRankMapCache = new HashMap<String, Integer>();
	}

	public void insert(String name) {
		this.nameRankMapCache.clear(); //invalidate cache
		
		Name_BSTVertex nodeToInsert = new Name_BSTVertex(name, null);
		
		if(this.root == null) {
			root = nodeToInsert;
		} else {
			//traverse left, else right
			Name_BSTVertex vertex = null, vertex_parent = root;
			
			if(this.root.compareTo(nodeToInsert) < 0) {
				vertex = this.root.getRight();
			} else if(this.root.compareTo(nodeToInsert) > 0) {
				vertex = this.root.getLeft();
			}
			
			//find insertion point
			while(vertex != null) {
				vertex_parent = vertex;
				//if node value is larger than curr vertex, continue going right, else go left
				if(vertex.compareTo(nodeToInsert) < 0) {
					vertex = vertex.getRight();
				} else if(vertex.compareTo(nodeToInsert) > 0) {
					vertex = vertex.getLeft();
				}
			}

			//insert node
			if(vertex_parent.compareTo(nodeToInsert) < 0) {
				vertex_parent.setRight(nodeToInsert);
			} else {
				vertex_parent.setLeft(nodeToInsert);
			}
			nodeToInsert.setParent(vertex_parent);
			
			//update balance factor and height going up the path from leaf to root
			int currHeight = 1; //assume starting from leaf
			while(vertex_parent != null) {
				vertex_parent.setHeight(currHeight++);
				vertex_parent = vertex_parent.getParent();
			}
			//check and rotate
		}
		
	}
		
	public void delete(String name) {
		this.nameRankMapCache.clear(); //invalidate cache
		
		Name_BSTVertex nodeToDelete = new Name_BSTVertex(name, null), vertex = this.root;
		
		//navigate to the node to be deleted
		while(vertex != null && vertex.compareTo(nodeToDelete) != 0) {
			if(vertex.compareTo(nodeToDelete) < 0) {
				vertex = vertex.getRight();
			} else if (vertex.compareTo(nodeToDelete) > 0) {
				vertex = vertex.getLeft();
			}
		}
		
		Name_BSTVertex vertex_parent = vertex.getParent();
				
		if(vertex.getLeft() == null && vertex.getRight() == null) { //removing leaves
			if(vertex_parent != null && vertex_parent.getLeft() != null && vertex_parent.getLeft().equals(vertex)) {
				vertex_parent.setLeft(null);
				vertex.setParent(null);
			} else if(vertex_parent != null && vertex_parent.getRight() != null && vertex_parent.getRight().equals(vertex)) {
				vertex_parent.setRight(null);
				vertex.setParent(null);
			}
			
			if(vertex == this.root) {
				this.root = null;
			}
		} else if (vertex.getLeft() == null && vertex.getRight() != null) { //node to be deleted has 1 child
			if(vertex_parent != null && vertex_parent.getLeft() != null && vertex_parent.getLeft().equals(vertex)) { //possible?
				vertex_parent.setLeft(vertex.getRight());
			} else if (vertex_parent != null) {
				vertex_parent.setRight(vertex.getRight());
			}
			vertex.getRight().setParent(vertex_parent);
			
			if(vertex == this.root) {
				this.root = vertex.getRight();
			}
		} else if (vertex.getLeft() != null && vertex.getRight() == null) { 
			if(vertex_parent != null && vertex_parent.getLeft() != null && vertex_parent.getLeft().equals(vertex)) { //possible?
				vertex_parent.setLeft(vertex.getLeft());
			} else if (vertex_parent != null) {
				vertex_parent.setRight(vertex.getLeft());
			}
			vertex.getLeft().setParent(vertex_parent);
			
			if(vertex == this.root) {
				this.root = vertex.getLeft();
			}
		} else { //node to be deleted has 2 children
			//replace vertex with successor
			replaceVertexWithSuccessor(vertex);
		}
	}
	
	//assumes vertex has 2 children
	private void replaceVertexWithSuccessor(Name_BSTVertex vertex) {
		Name_BSTVertex vertex_successor = null;
		try {
			vertex_successor = findSuccessor(vertex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(vertex_successor.getParent().equals(vertex)) { //if successor is next element w/o left subtree
			vertex_successor.setParent(vertex.getParent());
			vertex_successor.setLeft(vertex.getLeft());
			if(vertex.getLeft() != null) {
				vertex.getLeft().setParent(vertex_successor);
				vertex.setLeft(null);
			}
			if(vertex.getParent() != null) {
				vertex.getParent().setRight(vertex_successor);
				vertex.setParent(null);
			}
			vertex.setRight(null);
			
			//if root
			if(vertex == this.root) {
				this.root = vertex_successor;
			}  
		} else { //successor is element in left subtree
			vertex.setName(vertex_successor.getName());
			vertex_successor.getParent().setLeft(null);
			vertex_successor.setParent(null);
		}
	}

	public int getRankBySubstring(String keyword) {
		int rank = 1;
		Name_BSTVertex vertex = this.root;
		
		//while node is still lexicographically larger than keyword
		while(true) {
			if(vertex.getLeft() != null && vertex.getName().compareTo(keyword) > 0) {
				vertex = vertex.getLeft();
			} else if(vertex.getRight() != null && vertex.getRight().getName().compareTo(keyword) < 0) {
				rank += 1 + getSize(vertex.getLeft());
				vertex = vertex.getRight();
			} else {
				return rank + getSize(vertex.getLeft());
			}
		}
	}
	
	@Override
	public String toString() {
		String output = new String();
		
		//do inorder traversal
		inorderTraversalPrint(this.root);
		
		return output;
	}
	
	public int inorderTraversalPrint(Name_BSTVertex node) {
		if(node == null) {
			return -1;
		}
		
		inorderTraversalPrint(node.getLeft()); //if came out from left child		
		System.out.println("NODE IS:" + node);
		return inorderTraversalPrint(node.getRight()); //pass on to the successor
	}
	
	private Name_BSTVertex findSuccessor(Name_BSTVertex node) throws Exception {
		Name_BSTVertex vertex = node, vertex_parent;
		
		//successor is in right subtree
		if(vertex.getRight() != null) {
			//if left is null, return this
			//else traverse left until vertex's left child is null, return this
			vertex = node.getRight();
			
			while(vertex.getLeft() != null) {
				vertex = vertex.getLeft();
			}
			
			return vertex;
		} else { //succesor is parent or in parents' right subtree
			vertex_parent = vertex.getParent();
			
			//traverse up and left of the tree from right(if so)
			//until traverse up and right once
			while(vertex_parent != null && vertex.equals(vertex_parent.getRight())) {
				vertex = vertex_parent;
				vertex_parent = vertex.getParent();
			}
			
			//if root, return ERR
			if(vertex_parent == null) {
				throw new Exception("root is reached unexpectedly");
			} else {
				return vertex_parent;
			}
		}
	}
	
	//recursively returns size of tree/sub-tree from node (as root)
	private int getSize(Name_BSTVertex node) {
		if(node == null) {
			return 0;
		}
		return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
	}
}
