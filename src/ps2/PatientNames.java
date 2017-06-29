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
		// do not alter this method to avoid unnecessary errors with the automated
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
		// do not alter this method to avoid unnecessary errors with the automated
		// judging
		PatientNames ps2 = new PatientNames();
		ps2.run();
	}
}



class Name_BSTVertex {
	private Name_BSTVertex parent, left, right;
	private String key_name;
	private int height;
	private int size;
	
	public Name_BSTVertex(Name_BSTVertex parent, Name_BSTVertex left, Name_BSTVertex right) {
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	//getter/setter methods
	private Name_BSTVertex getParent() {
		return this.parent;
	}
	private Name_BSTVertex getLeft() {
		return this.left;
	}	
	private Name_BSTVertex getRight() {
		return this.right;
	}
	private int getHeight() {
		return this.height;
	}
	private int getSize() {
		return this.size;
	}
	
	private void setParent(Name_BSTVertex parent) {
		this.parent = parent;
	}
	private void setLeft(Name_BSTVertex left) {
		this.left = left;
	}
	private void setRight(Name_BSTVertex right) {
		this.right = right;
	}
	private void setHeight(int height) {
		this.height = height;
	}
	private void setSize(int size) {
		this.size = size;
	}
}

class Name_BST {
	private Name_BSTVertex root;
	
	
}