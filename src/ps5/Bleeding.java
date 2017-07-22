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
  
  public static final int INF = Integer.MAX_VALUE - Integer.MAX_VALUE/8;
  ArrayList<Integer> dist;
  PriorityQueue<IntegerTriple> pq;
  
  public Bleeding() {}

  void PreProcess() {}

  int Query(int s, int t, int k) {
    int ans = INF;
    ArrayList<ArrayList<IntegerPair>> answers = dijkstra(s, k);
    for(IntegerPair answ : answers.get(t)){
    	ans = Math.min(answ.second(), ans);
    }
    return ans == INF ? -1 : ans;
  }

  private void dijkstraPrep(int source) {
	  dist = new ArrayList<Integer>(V);
	  pq = new PriorityQueue<IntegerTriple>();
	  //constructing dist of INF values representing vertices, answers matrix
	  //init all other vertices except source into PQ
	  for(int j = 0; j<V; j++) {
        dist.add(INF);
	  }
	  dist.set(source, 0);
  }
  
	//modified dijkstra
	public ArrayList<ArrayList<IntegerPair>> dijkstra(int source, int limit) {
		dijkstraPrep(source);
		
		ArrayList<ArrayList<IntegerPair>> answers = new ArrayList<ArrayList<IntegerPair>>(V);
		for(int i=0; i<V; i++)
			answers.add(new ArrayList<IntegerPair>()); //V*K
		
		int currK = 1;
		pq.add(new IntegerTriple(0, source, currK)); // add source in with dist 0
		while (!pq.isEmpty()) {
			IntegerTriple next = pq.poll();

			currK = next.third();
			if (currK >= limit) {continue;} //just ignore, will break when queue is empty 

			int k = currK + 1;
			if(next.first() == dist.get(next.second())) {
				Iterator<IntegerPair> neighbours = AdjList.get(next.second()).iterator();
				while (neighbours.hasNext()) {
					IntegerPair ee = neighbours.next();
					if (dist.get(ee.second()) > dist.get(next.second()) + ee.first()) {
						dist.set(ee.second(), dist.get(next.second()) + ee.first());
						
						//if can relax, add it as possible answer to Answers
						answers.get(ee.second()).add(new IntegerPair(k, dist.get(ee.second()))); //k, cost
						
						pq.add(new IntegerTriple( dist.get(ee.second()), ee.second(), k));
					}
				}
			}
		}
		
		return answers;
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