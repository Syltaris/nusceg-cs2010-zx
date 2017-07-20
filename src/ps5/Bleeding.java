package ps5;

import java.util.*;
import java.io.*;

// write your matric number here: A0138915X
// write your name here: Chong Ze Xuan
// write list of collaborators here: 
// year 2017 hash code: x8DYWsALaAzykZ8dYPZP (do NOT delete this line)

class Bleeding {
  private int V; // number of vertices in the graph (number of junctions in Singapore map)
  private int Q; // number of queries
  private ArrayList < ArrayList < IntegerPair > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge
  
  public static final int INF = Integer.MAX_VALUE/2;
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------
  ArrayList<Integer> dist;
  TreeSet<IntegerPair> pq;
  private ArrayList < ArrayList < Integer > > Answers; // //V*V matrix of answers, stores all the valid SPs, INF if unreachable
  // --------------------------------------------

  public Bleeding() {}

  void PreProcess() {	  
	  Answers = new ArrayList<ArrayList<Integer>>(V);

	  for(int i=0; i<Math.min(AdjList.size(), 10); i++) {
		  dist = new ArrayList<Integer>(V);
		  pq = new TreeSet<IntegerPair>();
		  
		  //constructing dist of INF values representing vertices, answers matrix
	      for(int j = 0; j<V; j++) {
	          dist.add(INF);
	      }
	      dist.set(i, 0);
		  
		  dijkstra(i, 20); //go through all the sources
	  }
  }

  int Query(int s, int t, int k) {
    int ans = -1;
    if(Answers.get(s).get(t) != INF) {
    	ans = Answers.get(s).get(t);
    }
    return ans;
  }

  public void dijkstra(int source, int limit) {

	  //djikstra optimized, lazy DS
      pq.add(new IntegerPair(dist.get(source), source));
      while(!pq.isEmpty()) {
          IntegerPair next = pq.first(); //dequeue min item
          pq.remove(next); //finish the dequeue
          if(next.first() == dist.get(next.second())) {//if d == D[u]
               Iterator<IntegerPair> neighbours = AdjList.get(next.second()).iterator();
               while(neighbours.hasNext()) {
                   IntegerPair ee = neighbours.next();
                   if(dist.get(ee.second()) > dist.get(next.second()) + ee.first()) {//if can relax, relax, then add back to PQ
                       dist.set(ee.second(), dist.get(next.second()) + ee.first()); //if D[v] > D[u] + w(u,v)...                      
                       pq.add(new IntegerPair(dist.get(ee.second()), ee.second()));
                   }
               }
          }
      }
      
      //after done, add the dist array to the Answers
      Answers.add(dist);
  }

  // --------------------------------------------

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