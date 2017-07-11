
import java.util.*;
import java.io.*;

//write your matric number here: A0138915X
//write your name here: Chong Ze Xuan
//write list of collaborators here:
//year 2017 hash code: DZAjKugdE9QiOQKGFbut (do NOT delete this line)

class PatientNames {
	Name_BST males, females;
	HashMap<String, Integer> genders;

	public PatientNames() {
		this.males = new Name_BST();
		this.females = new Name_BST();
		this.genders = new HashMap<String, Integer>(20000);
	}

	void AddPatient(String name, int sex) {
		if(sex == 1) {
			males.insert(name);
		} else if(sex == 2) {
			females.insert(name);
		}
		genders.put(name, sex);
	}

	void RemovePatient(String name) {
		if(genders.get(name) == 1) {
			males.delete(name);
		} else {
			females.delete(name);
		}
	}

	int Query(String START, String END, int sex) {
		int ans = 0;

		String lastM;
		String lastF;
		
		if(sex == 0) {
			lastF = females.findMax();
			lastM = males.findMax();
			if(lastM == null || START.compareTo(lastM) > 0) {
				ans = 0;
			} else {
				ans = 1 + males.getRankBySubstring(END, false) - males.getRankBySubstring(START, true);
			}
			if(lastF == null || START.compareTo(lastF) > 0) {
				ans += 0;
			} else {
				ans += 1 + females.getRankBySubstring(END, false) - females.getRankBySubstring(START, true);
			}
		} else if (sex == 1) {
			lastM = males.findMax();
			if(lastM == null || START.compareTo(lastM) > 0) {
				ans = 0;
			} else {
				lastM = males.findMax();
				ans = 1 + males.getRankBySubstring(END, false) - males.getRankBySubstring(START, true);
			}
		} else {
			lastF = females.findMax();
			if(lastF == null || START.compareTo(lastF) > 0) {
				ans = 0;
			} else {
				ans = 1 + females.getRankBySubstring(END, false) - females.getRankBySubstring(START, true);
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
}

class Name_BSTVertex {
	private Name_BSTVertex p, l, r;
	private String key;
	private int h;
	private int bf;
	private int s;

	public Name_BSTVertex(String key) {
		this.key = key;
		this.l = null;
		this.r = null;
		this.h = 0;
		this.s = 1;
	}

	// getter/setter methods
	public Name_BSTVertex getParent() {return this.p;}
	public Name_BSTVertex getLeft() {return this.l;}
	public Name_BSTVertex getRight() {return this.r;}
	public String getName() {return this.key;}
	public int getHeight() {return this.h;}
	public int getBalance_factor() {return bf;}
	public int getSize() {return s;}
	
	public void setParent(Name_BSTVertex p) {this.p = p;}
	public void setLeft(Name_BSTVertex l) {this.l = l;}
	public void setRight(Name_BSTVertex r) {this.r = r;}
	public void setName(String key) {this.key = key;}
	public void setHeight(int h) {
		this.h = h;
		this.bf = calculateBalanceFactor();
	}
	public void setBalance_factor(int bf) {this.bf = bf;}
	public void setSize(int size) {this.s = size;}
	
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
	public int compareTo(Name_BSTVertex o) {return this.key.compareTo(o.key);}
}

class Name_BST {
	private Name_BSTVertex root;

	public Name_BST() {this.root = null;}

	public void insert(String name) {
		Name_BSTVertex node = new Name_BSTVertex(name);

		if (this.root == null) {
			root = node;
		} else {
			Name_BSTVertex v = root, v_p = null;

			while (v != null) {
				v_p = v;
				if (v.compareTo(node) < 0) {
					v = v.getRight();
				} else if (v.compareTo(node) > 0) {
					v = v.getLeft();
				}
			}
			if (v_p.compareTo(node) < 0) {
				v_p.setRight(node);
			} else {
				v_p.setLeft(node);
			}
			node.setParent(v_p);
			updateHeightFromLeaf(node);
			updateSizeFromLeaf(node);
			rebalance(node);
		}
	} 

	public void delete(String v) { 
		  Name_BSTVertex node = search(v).getParent();  
		  root = delete(root, v); 
		  rebalance(node);
	  }

	  protected Name_BSTVertex delete(Name_BSTVertex T, String v) {
	    if (T == null)  return T;

	    if (T.getName().compareTo(v) < 0)   
	      T.setRight(delete(T.getRight(), v));
	    else if (T.getName().compareTo(v) > 0)      
	      T.setLeft(delete(T.getLeft(), v));
	    else {                                        
	      if (T.getLeft() == null && T.getRight() == null)           
	        T = null;                                      
	      else if (T.getLeft() == null && T.getRight() != null) {         
	        T.getRight().setParent(T.getParent());
	        T = T.getRight();                                             
	      }
	      else if (T.getLeft() != null && T.getRight() == null) {      
	        T.getLeft().setParent(T.getParent());
	        T = T.getLeft();      
	      }
	      else { 
	        String successorV = successor(v);
	        T.setName(successorV);
	        T.setRight(delete(T.getRight(), successorV));
	      }
	    }
	    updateHeight(T);
	    updateSize(T);
	    return T;
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
		  int s = 1;
		  if(node == null) { return;}
		  if(node.getLeft() != null) {s += node.getLeft().getSize();}
		  if(node.getRight() != null) {s += node.getRight().getSize();}
		  node.setSize(s);
	  }
	  
	  private void updateSizeFromLeaf (Name_BSTVertex node) {
		  while(node != null) {
			  int s = 1;
			  if(node.getLeft() != null) {s += node.getLeft().getSize();}
			  if(node.getRight() != null) {s += node.getRight().getSize();}
			  node.setSize(s);
			  node = node.getParent();
		  }
	  }
	  
	  private void updateHeight(Name_BSTVertex node) {
		  int h = 1;
		  if(node == null) {return;}
		  if(node.getLeft() != null && node.getRight() != null) {
			  h += (max(node.getLeft().getHeight(),  node.getRight().getHeight()));
		  } else if (node.getRight() != null) {
			  h += node.getRight().getHeight();
		  } else if (node.getLeft() != null){
			  h += node.getLeft().getHeight();
		  } else {
			  h = 0;
		  }
		  node.setHeight(h);
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
	  
	public int getRankBySubstring(String keyword, boolean start) {
		int rank = 1;
		Name_BSTVertex vertex = this.root;

		while(vertex != null) {
			if(vertex.getLeft() != null && vertex.getName().compareTo(keyword) > 0) {
				vertex = vertex.getLeft();
			} else if(vertex.getRight() != null && (vertex.getName().compareTo(keyword) < 0)) {
				rank += 1 + (vertex.getLeft() != null ? vertex.getLeft().getSize() : 0);
				vertex = vertex.getRight();
			} else {
				if(!start && vertex.getName().compareTo(keyword) >= 0) {
					rank--;
				} else if (start && vertex.getName().compareTo(keyword) < 0) {
					rank++;
				}
				rank += (vertex.getLeft() != null ? vertex.getLeft().getSize() : 0);
				break;
			}
		}
		return rank;
	}

	  public String findMin() { return findMin(root); }
	  protected String findMin(Name_BSTVertex T) {
	         if (T == null)      return null;
	    else if (T.getLeft() == null) return T.getName();                    // this is the min
	    else                     return findMin(T.getLeft());           // go to the left
	  }

	  public String findMax() { return findMax(root); }
	  protected String findMax(Name_BSTVertex T) {
	         if (T == null)       return null;
	    else if (T.getRight() == null) return T.getName();                   // this is the max
	    else                      return findMax(T.getRight());        // go to the right
	  }
	
	  public Name_BSTVertex search(String v) {
	    Name_BSTVertex res = search(root, v);
	    return res == null ? null : res;
	  }
	  protected Name_BSTVertex search(Name_BSTVertex T, String v) {
	         if (T == null)  return null;                     // not found
	    else if (T.getName().compareTo(v) == 0) return T;                        // found
	    else if (T.getName().compareTo(v) < 0)  return search(T.getRight(), v);       // search to the right
	    else                 return search(T.getLeft(), v);        // search to the left
	  }
	

	  public String successor(String v) { 
	    Name_BSTVertex vPos = search(root, v);
	    return vPos == null ? "NULL" : successor(vPos);
	  }
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
