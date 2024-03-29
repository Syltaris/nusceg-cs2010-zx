
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
  
  public static final int INF = Integer.MAX_VALUE;
  ArrayList<Integer> dist;
  PriorityQueue<IntegerTriple> pq;
  
  public Bleeding() {}
  void PreProcess() {}

  int Query(int s, int t, int k) {
    int ans = INF;
    int[][] ansarr = dijkstra(s, k);

    for(int i=k; i>1; i--) {
    	if(ansarr[i][t] == 0)
    		continue;
    	ans = Math.min(ans, ansarr[i][t]);
    }
    
    return ans == INF ? -1 : ans;
  }

  private void dijkstraPrep(int source) {
	  dist = new ArrayList<Integer>(V);
	  pq = new PriorityQueue<IntegerTriple>();
	  for(int j = 0; j<V; j++) {
        dist.add(INF);
	  }
	  dist.set(source, 0);
  }
  
	//modified dijkstra
	public int[][] dijkstra(int source, int limit) {
//		dijkstraPrep(source);
		pq = new PriorityQueue<IntegerTriple>();

		int[][] dist = new int[limit+1][V];
		for(int i=0; i<=limit; i++)
			for(int j=0; j<V; j++)
				dist[i][j] = INF;

		int currK = 1;
		dist[currK][source] = 0;
		pq.add(new IntegerTriple( 0, source, currK)); // add source in with dist 0
		while (!pq.isEmpty()) {
			IntegerTriple next = pq.poll();

			currK = next.third();
			if (currK >= limit) {continue;}

			int k = currK + 1;
			if(next.first() == dist[currK][next.second()]) {
				Iterator<IntegerPair> neighbours = AdjList.get(next.second()).iterator();
				while (neighbours.hasNext()) {
					IntegerPair ee = neighbours.next();
					
					if(dist[k][ee.second()] > dist[currK][next.second()] + ee.first()) {
						dist[k][ee.second()] = dist[currK][next.second()] + ee.first();
						
						pq.add(new IntegerTriple(dist[k][ee.second()], ee.second(), k));
					}
				}
			}
		}
		
		return dist;
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