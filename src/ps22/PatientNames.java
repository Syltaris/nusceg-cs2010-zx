package ps22;

//Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;

//write your matric number here: A0138915X
//write your name here: Chong Ze Xuan
//write list of collaborators here:
//year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class PatientNames {
	// if needed, declare a private data structure here that
	// is accessible to all methods in this class

	// --------------------------------------------
	Name_BST malePatientsList, femalePatientsList;
	// --------------------------------------------

	public PatientNames() {
		// Write necessary code during construction;
		//
		// write your answer here

		// --------------------------------------------
		this.malePatientsList = new Name_BST();
		this.femalePatientsList = new Name_BST();
		// --------------------------------------------
	}

	void AddPatient(String patientName, int gender) {
		// You have to insert the information (patientName, gender)
		// into your chosen data structure
		//
		// write your answer here

		// --------------------------------------------
		if(gender == 1) {
			malePatientsList.insert(patientName);
		} else if(gender == 2) {
			femalePatientsList.insert(patientName);
		}
		
		
		// --------------------------------------------
	}

	void RemovePatient(String patientName) {
		// You have to remove the patientName from your chosen data structure
		//
		// write your answer here

		// --------------------------------------------
		
		// --------------------------------------------
	}

	int Query(String START, String END, int gender) {
		int ans = 0;

		// You have to answer how many patient name starts
		// with prefix that is inside query interval [START..END)
		//
		// write your answer here

		// --------------------------------------------
		
		// --------------------------------------------

		return ans;
	}

	void run() throws Exception {
		// do not alter this method to avoid unnecessary errors with the
		// automated
		// judging
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
		// do not alter this method to avoid unnecessary errors with the
		// automated
		// judging
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
	private int size;

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

	public int getHeight() {
		return this.height;
	}
	public int getBalance_factor() {
		return balance_factor;
	}
	public int getSize() {
		return this.size;
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

	public void setHeight(int height) {
		this.height = height;
		this.balance_factor = calculateBalanceFactor();
	}
	public void setBalance_factor(int balance_factor) {
		this.balance_factor = balance_factor;
	}
	public void setSize(int size) {
		this.size = size;
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
		
		return Math.abs(bf);
	}
	

	@Override
	public int compareTo(Name_BSTVertex o) {
		return this.key_name.compareTo(o.key_name);
	}

	@Override
	public String toString() {
		String output = new String();
		
		output = output.concat("NAME: " + key_name).concat(",HEIGHT: " + height).concat(",BF: " + balance_factor);
		return output;
	}
}

class Name_BST {
	private Name_BSTVertex root;

	public Name_BST() {
		this.root = null;
	}

	public void insert(String name) {
		Name_BSTVertex nodeToInsert = new Name_BSTVertex(name, null);
		
		if(this.root == null) {
			root = nodeToInsert;
		} else {
			//traverse left, else right
			Name_BSTVertex vertex = null, vertex_parent = null;
			
			if(this.root.compareTo(nodeToInsert) < 0) {
				vertex = this.root.getRight();
			} else if(this.root.compareTo(nodeToInsert) > 0) {
				vertex = this.root.getLeft();
			} else {
				System.out.println("EQUALS?"); //ERR
			}
			
			//find insertion point
			while(vertex != null) {
				//if node value is larger than curr vertex, continue going right, else go left
				if(vertex.compareTo(nodeToInsert) < 0) {
					vertex_parent = vertex;
					vertex = vertex.getRight();
				} else if(vertex.compareTo(nodeToInsert) > 0) {
					vertex_parent = vertex;
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
			while(vertex_parent != null) {
				int currHeight = 1; //assume starting from leaf
				
				vertex_parent.setHeight(currHeight++);
				vertex_parent = vertex_parent.getParent();
			}
			//check and rotate
		}
		
	}
		
	public void delete(String name) {
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
		//removing leaves
		if(vertex.getLeft() == null && vertex.getRight() == null) {
			if(vertex_parent.getLeft().equals(vertex)) {
				vertex_parent.setLeft(null);
			} else if(vertex_parent.getRight().equals(vertex)) {
				vertex_parent.setRight(null);
			}
		} else if(vertex.getLeft() != null) {
			vertex_parent.setLeft(vertex.getLeft()); //inherit left children
		} else if(vertex.getRight() != null) {
			vertex_parent.setRight(vertex.getRight()); //inherit right children
		} else {
			//replace vertex with successor
		}
	}
	
	@Override
	public String toString() {
		String output = new String();
		
		//do inorder traversal
		
		return output;
	}
}
