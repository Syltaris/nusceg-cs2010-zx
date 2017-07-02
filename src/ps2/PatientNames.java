package ps2;

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

	// --------------------------------------------

	public PatientNames() {
		// Write necessary code during construction;
		//
		// write your answer here

		// --------------------------------------------

		// --------------------------------------------
	}

	void AddPatient(String patientName, int gender) {
		// You have to insert the information (patientName, gender)
		// into your chosen data structure
		//
		// write your answer here

		// --------------------------------------------

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
}

class Name_BSTVertex implements Comparable<Name_BSTVertex> {
	private Name_BSTVertex parent, left, right;
	private String key_name;
	private int height;
	private int size;

	public Name_BSTVertex(String key_name) {
		this.key_name = key_name;
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
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int compareTo(Name_BSTVertex o) {
		return this.key_name.compareTo(o.key_name);
	}
}

class Name_BST {
	private Name_BSTVertex root;

	public Name_BST() {
		this.root = null;
	}

	public void insert(Name_BSTVertex node) {
		if(this.root == null) {
			root = node;
		} else {
			//traverse left, else right
			Name_BSTVertex vertex = null, vertex_parent = null;
			
			if(this.root.compareTo(node) < 0) {
				vertex = this.root.getLeft();
			} else if(this.root.compareTo(node) > 0) {
				vertex = this.root.getRight();
			} else {
				System.out.println("EQUALS?"); //ERR
			}
			
			//find insertion point
			while(vertex != null) {
				//if node value is larger than curr vertex, continue going right, else go left
				if(node.compareTo(vertex) < 0) {
					vertex_parent = vertex;
					vertex = vertex.getRight();
				} else if(node.compareTo(vertex) > 0) {
					vertex_parent = vertex;
					vertex = vertex.getLeft();
				}
			}
			
			vertex = node;
			
			//insert node
			if(vertex_parent.compareTo(node) < 0) {
				vertex_parent.setRight(node);
			} else {
				vertex_parent.setLeft(node);
			}
			
			//init new vertex's links
			vertex.setParent(vertex_parent);
			vertex.setLeft(null);
			vertex.setRight(null);
		}
	}
	
	@Override
	public String toString() {
		String output = new String();
		
		//do inorder traversal
		
		return output;
	}
}
