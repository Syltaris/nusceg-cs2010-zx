
import java.util.*;
import java.io.*;

//write your matric number here: A0138915X
//write your name here: Chong Ze Xuan
//write list of collaborators here:
//year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class PatientNames {
	Name_BST malePatientsList, femalePatientsList;
	HashMap<String, Integer> patientsGenderMap;

	public PatientNames() {
		this.malePatientsList = new Name_BST();
		this.femalePatientsList = new Name_BST();
		this.patientsGenderMap = new HashMap<String, Integer>(20000); //20k patients
	}

	void AddPatient(String patientName, int gender) {
		if(gender == 1) {
			malePatientsList.insert(patientName);
		} else if(gender == 2) {
			femalePatientsList.insert(patientName);
		}
		patientsGenderMap.put(patientName, gender);
	}

	void RemovePatient(String patientName) {
		if(patientsGenderMap.get(patientName) == 1) {
			malePatientsList.delete(patientName);
		} else {
			femalePatientsList.delete(patientName);
		}
	}

	int Query(String START, String END, int gender) {
		int ans = 0;

		String maleLargestName;
		String femaleLargestName;
		
		if(gender == 0) {
			femaleLargestName = femalePatientsList.findMax();
			maleLargestName = malePatientsList.findMax();
			if(maleLargestName == null || START.compareTo(maleLargestName) > 0) {
				ans = 0;
			} else {
				ans = 1 + malePatientsList.getRankBySubstring(END, false) - malePatientsList.getRankBySubstring(START, true);
			}
			if(femaleLargestName == null || START.compareTo(femaleLargestName) > 0) {
				ans += 0;
			} else {
				ans += 1 + femalePatientsList.getRankBySubstring(END, false) - femalePatientsList.getRankBySubstring(START, true);
			}
		} else if (gender == 1) {
			maleLargestName = malePatientsList.findMax();
			if(maleLargestName == null || START.compareTo(maleLargestName) > 0) {
				ans = 0;
			} else {
				maleLargestName = malePatientsList.findMax();
				ans = 1 + malePatientsList.getRankBySubstring(END, false) - malePatientsList.getRankBySubstring(START, true);
			}
		} else {
			femaleLargestName = femalePatientsList.findMax();
			if(femaleLargestName == null || START.compareTo(femaleLargestName) > 0) {
				ans = 0;
			} else {
				ans = 1 + femalePatientsList.getRankBySubstring(END, false) - femalePatientsList.getRankBySubstring(START, true);
			}
		}
		return ans;
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
	private int size;

	public Name_BSTVertex(String key_name) {
		this.key_name = key_name;
		this.left = null;
		this.right = null;
		this.height = 0; //assumption that vertex will always be leaf?
		this.size = 1;
	}

	// getter/setter methods
	public Name_BSTVertex getParent() {return this.parent;}
	public Name_BSTVertex getLeft() {return this.left;}
	public Name_BSTVertex getRight() {return this.right;}
	public String getName() {return this.key_name;}
	public int getHeight() {return this.height;}
	public int getBalance_factor() {return balance_factor;}
	public int getSize() {return size;}
	
	public void setParent(Name_BSTVertex parent) {this.parent = parent;}
	public void setLeft(Name_BSTVertex left) {this.left = left;}
	public void setRight(Name_BSTVertex right) {this.right = right;}
	public void setName(String key_name) {this.key_name = key_name;}
	public void setHeight(int height) {
		this.height = height;
		this.balance_factor = calculateBalanceFactor();
	}
	public void setBalance_factor(int balance_factor) {this.balance_factor = balance_factor;}
	public void setSize(int size) {this.size = size;}
	
	private int calculateBalanceFactor() {
		int bf = 0;
		if(this.getLeft() == null && this.getRight() == null) {
			bf = 0; // -1 + (-1)
		} else if(this.getLeft() == null) {
			bf = -1 - this.getRight().getHeight(); // right - (-1)
		} else if(this.getRight() == null) {
			bf = this.getLeft().getHeight() + 1; //left - (-1)
		} else {
			bf = this.getLeft().getHeight() - this.getRight().getHeight();
		}
		
		return bf;
	}
	public int compareTo(Name_BSTVertex o) {return this.key_name.compareTo(o.key_name);}

	@Override
	public String toString() {
		String output = new String();
		output = output.concat("NAME: " + key_name +",HEIGHT: " + height+",BF: " + balance_factor+"SIZE: "+size);
		return output;
	}
}

class Name_BST {
	private Name_BSTVertex root;
//	private HashMap<String, Integer> startQueryRankCache;
//	private HashMap<String, Integer> endQueryRankCache;

	public Name_BST() {
		this.root = null;
//		this.startQueryRankCache = new HashMap<String, Integer>(20000);
//		this.endQueryRankCache = new HashMap<String, Integer>(20000);
	}

	public void insert(String name) {
//		this.startQueryRankCache.clear(); // invalidate cache
//		this.endQueryRankCache.clear();

		Name_BSTVertex nodeToInsert = new Name_BSTVertex(name);

		if (this.root == null) {
			root = nodeToInsert;
		} else {
			// traverse left, else right
			Name_BSTVertex vertex = root, vertex_parent = null;

			// find insertion point
			while (vertex != null) {
				vertex_parent = vertex;
				// if node value is larger than curr vertex, continue going
				// right, else go left
				if (vertex.compareTo(nodeToInsert) < 0) {
					vertex = vertex.getRight();
				} else if (vertex.compareTo(nodeToInsert) > 0) {
					vertex = vertex.getLeft();
				}
			}

			// insert node
			if (vertex_parent.compareTo(nodeToInsert) < 0) {
				vertex_parent.setRight(nodeToInsert);
			} else {
				vertex_parent.setLeft(nodeToInsert);
			}
			nodeToInsert.setParent(vertex_parent);

			// update balance factor and height going up the path from leaf to root
			updateHeightFromLeaf(nodeToInsert);
			updateSizeFromLeaf(nodeToInsert);
			
			// check and rotate
			rebalance(nodeToInsert);
		}

	} 

	  // public method to delete a vertex containing key with value v from BST
	  public void delete(String v) { 
		  Name_BSTVertex nodeToRebalance = search(v).getParent();
		  
		  root = delete(root, v); 
//		  this.startQueryRankCache.clear(); //invalidate cache
//		  this.endQueryRankCache.clear();
		  
		  //rebalance
		  rebalance(nodeToRebalance);
	  }

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
	    updateHeight(T);
	    updateSize(T);
	    return T;                                          // return the updated BST
	  }
	  
	  private void updateSizes(Name_BSTVertex node) {
		  if(node == null) {
			  return;
		  } else if(node.getLeft() == null && node.getRight() == null) {
			  updateSizeFromLeaf(node);
			  return;
		  }
		  updateSizes(node.getLeft());
		  updateSizes(node.getRight());
	  }
	  
	  private void updateSize(Name_BSTVertex node) {
		  int size = 1;
		  if(node == null) {
			  return;
		  }
		  if(node.getLeft() != null) {
			  size += node.getLeft().getSize();
		  }
		  if(node.getRight() != null) {
			  size += node.getRight().getSize();
		  }
		  
		  node.setSize(size);
	  }
	  
	  private void updateSizeFromLeaf (Name_BSTVertex node) {
		  while(node != null) {
			  int size = 1;
			  if(node.getLeft() != null) {
				  size += node.getLeft().getSize();
			  }
			  if(node.getRight() != null) {
				  size += node.getRight().getSize();
			  }
			  
			  node.setSize(size);
			  node = node.getParent();
		  }
	  }
	  
	  private void updateHeight(Name_BSTVertex node) {
		  int height = 1;
		  if(node == null) {
			  return;
		  }
		  
		  if(node.getLeft() != null && node.getRight() != null) {
			  height += (max(node.getLeft().getHeight(),  node.getRight().getHeight()));
		  } else if (node.getRight() != null) {
			  height += node.getRight().getHeight();
		  } else if (node.getLeft() != null){
			  height += node.getLeft().getHeight();
		  } else {
			  height = 0;
		  }
		  node.setHeight(height);
	  }
	  
	  //traverse up from node to root and update all heights
	  private void updateHeightFromLeaf(Name_BSTVertex node) {
		  int height = 0;
		  
		  while(node != null) {
			  if(node.getLeft() != null && node.getRight() != null) {
				  height = 1 + (max(node.getLeft().getHeight(),  node.getRight().getHeight()));
			  }
			  node.setHeight(height++);
			  node = node.getParent();
		  }
	  }
	  
	  //recursively traverse down from root to leaves and update heights from there
	  private void updateHeights(Name_BSTVertex node) {
		  if(node == null) {
			  return;
		  } else if(node.getLeft() == null && node.getRight() == null) {
			  updateHeightFromLeaf(node);
			  return;
		  }
		  updateHeights(node.getLeft());
		  updateHeights(node.getRight());
	  }
	  
	  private void rebalance(Name_BSTVertex node) {
		  while(node != null) {
			  if(node.getBalance_factor() == 2 && node.getLeft() != null && node.getLeft().getBalance_factor() == 1) {
				  rotateRight(node);
			  } else if(node.getBalance_factor() == 2 && node.getLeft() != null && node.getLeft().getBalance_factor() == -1) {
				  rotateLeft(node.getLeft());
				  rotateRight(node);
			  } else if(node.getBalance_factor() == -2 && node.getRight() != null && node.getRight().getBalance_factor() == -1) {
				  rotateLeft(node);
			  } else if(node.getBalance_factor() == -2 && node.getRight() != null && node.getRight().getBalance_factor() == 1) {
				  rotateRight(node.getRight());
				  rotateLeft(node);
			  }
			  node = node.getParent();
		  }
	  }
	  
	  private Name_BSTVertex rotateLeft(Name_BSTVertex node) {
		  Name_BSTVertex right = node.getRight();
		  
		  node.setRight(right.getLeft());
		  if(right.getLeft() != null) {
			  right.getLeft().setParent(node);
		  }
		  
		  right.setParent(node.getParent());
		  if(node.getParent() != null && node.getParent().getLeft() == node) {
			  node.getParent().setLeft(right);
		  } else if(node.getParent() != null) {
			  node.getParent().setRight(right);
		  }
		  
		  right.setLeft(node);
		  node.setParent(right);
		  
		  node.setHeight(1 + max(node.getLeft() == null ? 0 : node.getLeft().getHeight(),
				  node.getRight() == null ? 0 : node.getRight().getHeight()));
		  right.setHeight(1 + max(right.getLeft() == null ? 0 : right.getLeft().getHeight(),
				  right.getRight() == null ? 0 : right.getRight().getHeight()));
		  
		  if(right.getParent() == null) {
			  this.root = right;
		  }
		  
		  updateHeights(right);
		  updateSizes(right);
		  return right;
	  }
	  
	  private Name_BSTVertex rotateRight(Name_BSTVertex node) {
		  Name_BSTVertex left = node.getLeft();
		  
		  node.setLeft(left.getRight());
		  if(left.getRight() != null) {
			  left.getRight().setParent(node);
		  }
		  
		  left.setParent(node.getParent());
		  if(node.getParent() != null && node.getParent().getLeft() == node) {
			  node.getParent().setLeft(left);
		  } else if(node.getParent() != null) {
			  node.getParent().setRight(left);
		  }
		  
		  left.setRight(node);
		  node.setParent(left);
		  
		  node.setHeight(1 + max(node.getLeft() == null ? 0 : node.getLeft().getHeight(),
				  node.getRight() == null ? 0 : node.getRight().getHeight()));
		  left.setHeight(1 + max(left.getLeft() == null ? 0 : left.getLeft().getHeight(),
				  left.getRight() == null ? 0 : left.getRight().getHeight()));
		  
		  if(left.getParent() == null) {
			  this.root = left;
		  }
		  
		  updateHeights(left);
		  updateSizes(left);
		  return left;
	  }

	  private int max(int a, int b) {return a > b ? a : b;}
	  
	public int getRankBySubstring(String keyword, boolean inclusive) {
		int rank = 1;
		Name_BSTVertex vertex = this.root;
		
//		//if query is cached, return query
//		if(inclusive && this.startQueryRankCache.containsKey(keyword)) {
//			return this.startQueryRankCache.get(keyword);
//		} else if (!inclusive && this.endQueryRankCache.containsKey(keyword)) {
//			return this.endQueryRankCache.get(keyword);
//		}
		
		//while node is still lexicographically larger than keyword
		while(vertex != null) {
			if(vertex.getLeft() != null && vertex.getName().compareTo(keyword) > 0) {
				vertex = vertex.getLeft();
			} else if(vertex.getRight() != null && (vertex.getName().compareTo(keyword) < 0)) {
				rank += 1 + (vertex.getLeft() != null ? vertex.getLeft().getSize() : 0);
				vertex = vertex.getRight();
			} else {
				if(!inclusive && vertex.getName().compareTo(keyword) >= 0) {
					rank--;
				} else if (inclusive && vertex.getName().compareTo(keyword) < 0) {
					rank++;
				}
				rank += (vertex.getLeft() != null ? vertex.getLeft().getSize() : 0);
				break;
			}
		}
		
//		if(inclusive) 
//			this.startQueryRankCache.put(keyword, rank);
//		else 
//			this.endQueryRankCache.put(keyword, rank);

		return rank;
	}
	
	//recursively returns size of tree/sub-tree from node (as root)
	private int getSize(Name_BSTVertex node) {
		if(node == null) {return 0;}
		return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
	}
	
	@Override
	public String toString() {
		String output = new String();
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
	         if (T == null)      return null;
	    else if (T.getLeft() == null) return T.getName();                    // this is the min
	    else                     return findMin(T.getLeft());           // go to the left
	  }

	  // public method called to find Maximum key value in BST
	  public String findMax() { return findMax(root); }

	  // overloadded recursive method to perform findMax
	  protected String findMax(Name_BSTVertex T) {
	         if (T == null)       return null;
	    else if (T.getRight() == null) return T.getName();                   // this is the max
	    else                      return findMax(T.getRight());        // go to the right
	  }
	
	  // method called to search for a node v 
	  public Name_BSTVertex search(String v) {
	    Name_BSTVertex res = search(root, v);
	    return res == null ? null : res;
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
