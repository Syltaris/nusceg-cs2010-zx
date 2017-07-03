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
		
//		System.out.println(malePatientsList.getRankBySubstring(START, true));
//		System.out.println(malePatientsList.getRankBySubstring(END, false));
//		System.out.println(femalePatientsList.getRankBySubstring(START, true));
//		System.out.println(femalePatientsList.getRankBySubstring(END, false));

		String maleLargestName = malePatientsList.findMax();
		String femaleLargestName = femalePatientsList.findMax();
		
		if(gender == 0) {
			if(START.compareTo(maleLargestName) > 0) {
				ans = 0;
			} else {
				ans = 1 + malePatientsList.getRankBySubstring(END, false) - malePatientsList.getRankBySubstring(START, true);
			}
			if(START.compareTo(femaleLargestName) > 0) {
				ans += 0;
			} else {
				ans += 1 + femalePatientsList.getRankBySubstring(END, false) - femalePatientsList.getRankBySubstring(START, true);
			}
		} else if (gender == 1) {
			if(START.compareTo(maleLargestName) > 0) {
				ans = 0;
			} else {
				ans = 1 + malePatientsList.getRankBySubstring(END, false) - malePatientsList.getRankBySubstring(START, true);
			}
		} else {
			if(START.compareTo(femaleLargestName) > 0) {
				ans = 0;
			} else {
				ans = 1 + femalePatientsList.getRankBySubstring(END, false) - femalePatientsList.getRankBySubstring(START, true);
			}
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

class Name_BSTVertex {
	private Name_BSTVertex parent, left, right;
	private String key_name;
	private int height;
	private int balance_factor;

	public Name_BSTVertex(String key_name) {
		this.key_name = key_name;
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

	  // method called to insert a new key with value v into BST
	  public void insert(String v) { root = insert(root, v); }

	  // overloaded recursive method to perform insertion of new vertex into BST
	  protected Name_BSTVertex insert(Name_BSTVertex T, String v) {
	    if (T == null) return new Name_BSTVertex(v);          // insertion point is found

	    if (T.getName().compareTo(v) < 0) {                                      // search to the right
	      T.setRight(insert(T.getRight(), v));
	      T.getRight().setParent(T);
	    }
	    else {                                                 // search to the left
	      T.setLeft(insert(T.getLeft(), v));
	      T.getLeft().setParent(T);
	    }

	    return T;                                          // return the updated BST
	  }  

	  // public method to delete a vertex containing key with value v from BST
	  public void delete(String v) { root = delete(root, v); }

	  // overloaded recursive method to perform deletion 
	  protected Name_BSTVertex delete(Name_BSTVertex T, String v) {
	    if (T == null)  return T;              // cannot find the item to be deleted

	    if (T.getName().compareTo(v) < 0)                // search to the right
	      T.setRight(delete(T.getRight(), v));
	    else if (T.getName().compareTo(v) > 0)           // search to the left
	      T.setLeft(delete(T.getLeft(), v));
	    else {                                            // this is the node to be deleted
	      if (T.getLeft() == null && T.getRight() == null)                   // this is a leaf
	        T = null;                                      // simply erase this node
	      else if (T.getLeft() == null && T.getRight() != null) {   // only one child at right        
	        T.getRight().setParent(T.getParent());
	        T = T.getRight();                                                 // bypass T        
	      }
	      else if (T.getLeft() != null && T.getRight() == null) {    // only one child at left        
	        T.getLeft().setParent(T.getParent());
	        T = T.getLeft();                                                  // bypass T        
	      }
	      else {                                 // has two children, find successor
	        String successorV = successor(v);
	        T.setName(successorV);         // replace this key with the successor's key
	        T.setRight(delete(T.getRight(), successorV));      // delete the old successorV
	      }
	    }
	    
	    return T;                                          // return the updated BST
	  }

	public int getRankBySubstring(String keyword, boolean inclusive) {
		int rank = 1;
		Name_BSTVertex vertex = this.root;
		
		//while node is still lexicographically larger than keyword
		while(vertex != null) {
			if(vertex.getLeft() != null && vertex.getName().compareTo(keyword) > 0) {
				vertex = vertex.getLeft();
			} else if(vertex.getRight() != null && (vertex.getName().compareTo(keyword) < 0)) {
				rank += 1 + getSize(vertex.getLeft());
				vertex = vertex.getRight();
			} else {
				if(!inclusive && vertex.getName().compareTo(keyword) > 0) {
					rank--;
				} else if (inclusive && vertex.getName().compareTo(keyword) <= 0) {
					rank++;
				}
				rank += getSize(vertex.getLeft());
				break;
			}
		}
		
		return rank;
	}
	
	//recursively returns size of tree/sub-tree from node (as root)
	private int getSize(Name_BSTVertex node) {
		if(node == null) {
			return 0;
		}
		return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
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
	
	  // public method called to find Minimum key value in BST
	  public String findMin() { return findMin(root); }

	  // overloadded recursive method to perform findMin
	  protected String findMin(Name_BSTVertex T) {
	         if (T == null)      throw new NoSuchElementException("BST is empty, no minimum");
	    else if (T.getLeft() == null) return T.getName();                    // this is the min
	    else                     return findMin(T.getLeft());           // go to the left
	  }

	  // public method called to find Maximum key value in BST
	  public String findMax() { return findMax(root); }

	  // overloadded recursive method to perform findMax
	  protected String findMax(Name_BSTVertex T) {
	         if (T == null)       throw new NoSuchElementException("BST is empty, no maximum");
	    else if (T.getRight() == null) return T.getName();                   // this is the max
	    else                      return findMax(T.getRight());        // go to the right
	  }
	
	  // method called to search for a value v 
	  public String search(String v) {
	    Name_BSTVertex res = search(root, v);
	    return res == null ? "NULL" : res.getName();
	  }

	  // overloaded recursive method to perform search
	  protected Name_BSTVertex search(Name_BSTVertex T, String v) {
	         if (T == null)  return null;                     // not found
	    else if (T.getName().compareTo(v) == 0) return T;                        // found
	    else if (T.getName().compareTo(v) < 0)  return search(T.getRight(), v);       // search to the right
	    else                 return search(T.getLeft(), v);        // search to the left
	  }
	
	  // public method to find successor to given value v in BST
	  public String successor(String v) { 
	    Name_BSTVertex vPos = search(root, v);
	    return vPos == null ? "NULL" : successor(vPos);
	  }

	  // overloaded recursive method to find successor to for a given vertex T in BST
	  protected String successor(Name_BSTVertex T) {
	    if (T.getRight() != null)                       // this subtree has right subtree
	      return findMin(T.getRight());  // the successor is the minimum of right subtree
	    else {
	      Name_BSTVertex par = T.getParent();
	      Name_BSTVertex cur = T;
	      // if par(ent) is not root and cur(rent) is its right children
	      while ((par != null) && (cur == par.getRight())) {
	        cur = par;                                         // continue moving up
	        par = cur.getParent();
	      }
	      return par == null ? "NULL" : par.getName();           // this is the successor of T
	    }
	  }
}
