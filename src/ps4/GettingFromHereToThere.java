
import java.util.*;
import java.io.*;

// write your matric number here: A0138915X
// write your name here: Chong Ze Xuan
// write list of collaborators here: Clarence Chee, Tan Hong Yu, Chee Yeo, Kelvin, Zachary Foo
// year 2017 hash code: x4gxK7xzMSlNvFsMEUVn (do NOT delete this line)

class GettingFromHereToThere {
  private int V; // number of vertices in the graph (number of rooms in the building)
  private ArrayList < ArrayList < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too
  
  private MST mst;
  
  public GettingFromHereToThere() { }

  void PreProcess() { this.mst = new MST(this.AdjList);}

  int Query(int source, int destination) {
    return mst.getMAXiMINMatrix(source, destination);
  }

  void run() throws Exception {
    // do not alter this method
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // clear the graph and read in a new graph as Adjacency List
      AdjList = new ArrayList < ArrayList < IntegerPair > >();
      for (int i = 0; i < V; i++) {
        AdjList.add(new ArrayList < IntegerPair >());

        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt(), w = sc.nextInt();
          AdjList.get(i).add(new IntegerPair(w, j)); //  weight (effort rating) edge (corridor) is stored here
        }
      }
      
      PreProcess(); // you may want to use this function or leave it empty if you do not need it
            
      int Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt()));
      pr.println(); // separate the answer between two different graphs
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    GettingFromHereToThere ps4 = new GettingFromHereToThere();
    ps4.run();
  }
}

class MST {
	private PriorityQueue<IntegerTriple> queue; //(weight, vertex, edgeTo)
	private ArrayList<Boolean> taken;
	private ArrayList<Integer> parentOf;
	ArrayList <ArrayList<IntegerPair>> adjList;
	private int[][] maximin;
	
	
	public MST(ArrayList <ArrayList<IntegerPair>> adjList) {
		this.adjList = adjList;
		this.queue = new PriorityQueue<IntegerTriple>();
		this.maximin = new int[Math.min(adjList.size(), 10)][adjList.size()];

		preprocess();
	}
	
	private void preprocess() {
		
		for(int i = 0; i<Math.min(10, adjList.size()); i++) { //source
			this.taken = new ArrayList<Boolean>(adjList.size());
			this.parentOf = new ArrayList<Integer>(adjList.size());
			for(int ii = 0; ii < adjList.size(); ii++) {
				taken.add(false);
				parentOf.add(-1);
			}
			
			process(i); //start off the queue
			while(!queue.isEmpty()) {
				IntegerTriple node = queue.poll();
				
				if(!taken.get(node.third())) {
					parentOf.set(node.third(), node.second());
					
					maximin[i][node.third()] = Math.max(node.first(), maximin[i][node.second()]); // (edge's weight, currMax of prev node)
					process(node.third());
				}
			}
		}
	}
	
	private void process(int vertex) {
		Iterator<IntegerPair> neighbours = adjList.get(vertex).iterator();
		
		taken.set(vertex, true);
		while(neighbours.hasNext()) {
			IntegerPair next = neighbours.next();
			
			//if node is not taken add it to queue, else add vertex to list of endpoints
			if(!taken.get(next.second())) {
				queue.add(new IntegerTriple(next.first(), vertex, next.second())); // weight, vertex, edge
			}
		}
	}
	
	public int getMAXiMINMatrix(int source, int destination) {return this.maximin[source][destination];}
}

class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }
  
  public int nextInt() {
    int result = 0;
    try {
      int cur = bis.read();
      if (cur == -1)
        return -1;

      while ((cur < 48 || cur > 57) && cur != 45) {
        cur = bis.read();
      }

      boolean negate = false;
      if (cur == 45) {
        negate = true;
        cur = bis.read();
      }

      while (cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }

      if (negate) {
        return -result;
      }
      return result;
    }
    catch (IOException ioe) {
      return -1;
    }
  }
}



class IntegerPair implements Comparable < IntegerPair > {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}



class IntegerTriple implements Comparable < IntegerTriple > {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.third() - o.third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
}