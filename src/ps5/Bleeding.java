package ps5;

import java.util.*;
import java.io.*;

// write your matric number here: A0138915X
// write your name here: Chong Ze Xuan
// write list of collaborators here: Clarence Chee, Tan Hong Yu, Zachary Ong
// year 2017 hash code: x8DYWsALaAzykZ8dYPZP (do NOT delete this line)

class Bleeding {
  private int V; // number of vertices in the graph (number of junctions in Singapore map)
  private int Q; // number of queries
  private ArrayList < ArrayList < IntegerPair > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge
  
  public static final int INF = Integer.MAX_VALUE/2;
  ArrayList<Integer> dist;
  PriorityQueue<IntegerTriple> pq;
  private ArrayList< ArrayList < ArrayList < Integer > > > Answers; //V*K*V matrix of answers, stores all the valid SPs, INF if unreachable
  
  public Bleeding() {}

  void PreProcess() {	  
	  Answers = new ArrayList<ArrayList<ArrayList<Integer>>>(V);
	  for(int i=0; i<V; i++) {
		  Answers.add(new ArrayList<ArrayList<Integer>>());
		  for(int j=0; j<20; j++)
			  Answers.get(i).add(new ArrayList<Integer>());
	  }
  }

  int Query(int s, int t, int k) {
    int ans = INF;
    
    //randomly check any k, if empty, generate the dijkstra sssp on it
    //else, answer should already be generated
    if(Answers.get(s).get(0).isEmpty()) {
//    	dijkstra(s);
    	bellmanford(s);
    }
    
    //search for the answer, skip searching for k=1 since will be -1
    for(int i=2; i<=k; i++) {
    	ArrayList<Integer> next = Answers.get(s).get(i);
    	//if pass the k boundary for the graph, or k restriction by query
    	if(next.isEmpty()) {
    		break;
    	}
//    																																System.out.println(i + ": " + next);
    	ans = Math.min(next.get(t), ans);
    }
    
    return ans == INF ? -1 : ans;
  }

  private void dijkstraPrep(int source) {
	  dist = new ArrayList<Integer>(V);
	  pq = new PriorityQueue<IntegerTriple>();
	  
	  //constructing dist of INF values representing vertices, answers matrix
    for(int j = 0; j<V; j++) {
        dist.add(INF);
    }
    dist.set(source, 0);
}

  
  @SuppressWarnings("unchecked")
public void bellmanford(int source) {
	  dist = new ArrayList<Integer>(V);
	  //constructing dist of INF values representing vertices, answers matrix
	    for(int j = 0; j<V; j++) {
	        dist.add(INF);
	    }
	    
	  ArrayList<IntegerTriple> edgeList = new ArrayList<IntegerTriple>(V);
	  
	  int j = 0;
	  for(ArrayList<IntegerPair> neighbours : AdjList) {
		  
		  for(IntegerPair pair : neighbours)
			  edgeList.add(new IntegerTriple(pair.first(), j, pair.second())); //w, u, v
		  
		  j++;
	  }
	  dist.set(source, 0); //this determines the source
	  
	  //run K iterations, at each iteration store dist into Answers
	  for(int i = 2; i<Math.min(21, AdjList.size()); i++) {
		  Iterator<IntegerTriple> edges = edgeList.iterator();
		  
			System.out.println(i + "before:: " + dist);

		  
		  while(edges.hasNext()) {
			  IntegerTriple next = edges.next();
			  
			  if(dist.get(next.third()) > dist.get(next.second()) + next.first() ) {
				  dist.set(next.third(), dist.get(next.second()) + next.first());
			  }
		  }
		  
			System.out.println(i + ":: " + dist);

		  Answers.get(source).set(i, (ArrayList<Integer>)dist.clone());
	  }
	 
  }
  
public void dijkstra(int source) {
	  dijkstraPrep(source); //inits the dist[] and pq
	  
	  int currK = 2;
	  
	  //djikstra optimized, lazy DS
      pq.add(new IntegerTriple(currK, dist.get(source), source)); //k, D[u], u
      while(!pq.isEmpty()) {
          IntegerTriple next = pq.poll(); //dequeue min item
          
          //at this point, should have processed all 'k' distances, add to Kth array for the answers, (check for 20th k limit too)
          if(currK < next.first()) {
        	  for(Integer e : dist)
        		  Answers.get(source).get(currK).add(e);
        	  
//        	  																													System.out.println(currK + ":: " + dist);
        	  currK = next.first();
          }
          if (next.first() >= 21) {
        	  break;
          }
          
          int k = next.first() + 1;     	  //now processing next 'hospitals' with higher k than this current node

          if(next.second() == dist.get(next.third())) {//if d == D[u]
               Iterator<IntegerPair> neighbours = AdjList.get(next.third()).iterator();
               while(neighbours.hasNext()) {
                   IntegerPair ee = neighbours.next();
                   if(dist.get(ee.second()) > dist.get(next.third()) + ee.first()) {//if can relax, relax, then add back to PQ
                       dist.set(ee.second(), dist.get(next.third()) + ee.first()); //if D[v] > D[u] + w(u,v)...                      
                       pq.add(new IntegerTriple(k, dist.get(ee.second()), ee.second()));
                   }
               }
          }
      }
  }


  void run() throws Exception {
    // you can alter this method if you need to do so
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
          AdjList.get(i).add(new IntegerPair(w, j)); // edge (road) weight (in minutes) is stored here
        }
      }

      PreProcess(); // optional

      Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

      if (TC > 0)
        pr.println();
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    Bleeding ps5 = new Bleeding();
    ps5.run();
  }
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
	    else if(!this.second().equals(o.second()))
	      return this.second() - o.second();
	    else
		  return this.third() - o.third();
	  }

	  Integer first() { return _first; }
	  Integer second() { return _second; }
	  Integer third() {return _third; }
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