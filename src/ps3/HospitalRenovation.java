package ps3;

import java.util.*;
import java.io.*;

//write your matric number here: A0138915X
//write your name here:  Chong Ze Xuan
//write list of collaborators here: Tarjan (algo), Aakash Hasija (reference code)
//year 2017 hash code: AlaYnzmQ65P4x2Uc559u (do NOT delete this line)

class HospitalRenovation {
	private int V; // number of vertices in the graph (number of rooms in the hospital)
	private Weighted_UDGraph adjList; // the graph (the hospital)
	private ArrayList<Integer> RatingScore; // the weight of each vertex (rating score of each room)

	public HospitalRenovation() {}
	int Query() {return adjList.MinAPFinder();}

	void run() throws Exception {
		// for this PS3, you can alter this method as you see fit

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int TC = Integer.parseInt(br.readLine()); // there will be several test cases
		while (TC-- > 0) {
			br.readLine(); // ignore dummy blank line
			V = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			// read rating scores, A (index 0), B (index 1), C (index 2), ..., until the
			// V-th index
			RatingScore = new ArrayList<Integer>(V);
			for (int i = 0; i < V; i++)
				RatingScore.add(Integer.parseInt(st.nextToken()));

			// clear the graph and read in a new graph as Adjacency Matrix
			adjList = new Weighted_UDGraph(V, RatingScore);
			for (int i = 0; i < V; i++) {
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				while (k-- > 0) {
					int j = Integer.parseInt(st.nextToken());
					adjList.insert(i,j); // edge weight is always 1 (the weight is on vertices now)
				}
			}
			pr.println(Query());
			Time.time = 0;
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		HospitalRenovation ps3 = new HospitalRenovation();
		ps3.run();
	}
}

class Time {
	public static int time = 0;
}

class Weighted_UDGraph {
	ArrayList<LinkedList<Integer>> adjList;
	ArrayList<Integer> RatingScore;
	
	public Weighted_UDGraph (int size, ArrayList<Integer> RatingScore) {
		this.RatingScore = RatingScore;
		this.adjList = new ArrayList<LinkedList<Integer>>(size);
		for(int i=0; i<size; i++)
			this.adjList.add(new LinkedList<Integer>());
	}
	
	public void insert(int vertex, int neighbour) {
		adjList.get(vertex).add(neighbour);
		adjList.get(neighbour).add(vertex);
	}
	
	//returns list of Articulation Points
	public int MinAPFinder() {
		boolean[] visited = new boolean[adjList.size()];
		int[] parents = new int[adjList.size()];
		int[] discTime = new int[adjList.size()];
		int[] minDiscTime = new int[adjList.size()];
		int[] ans = {-1};
		
		for(int i=0; i<adjList.size(); i++) {
			visited[i] = false;
			parents[i] = -1;
		}
		
		for(int i=0; i<adjList.size(); i++) {
			if(visited[i] == false) {
				DFS_MinAPFind(i, parents, visited, discTime, minDiscTime, ans);
			}
		}
		
		return ans[0];
	}
	
	private void DFS_MinAPFind(int vertexIndex, int[] parents, boolean[] visited, int[] discTime, int[] minDiscTime, int[] ans) {
		Iterator<Integer> neighbours = adjList.get(vertexIndex).iterator();
		int noOfChildren = 0;
		
		visited[vertexIndex] = true; //mark as visited
		discTime[vertexIndex] = Time.time; //assign disc times
		minDiscTime[vertexIndex] = Time.time++;
		
		while(neighbours.hasNext()) {
			int neighbourIndex = neighbours.next();
			if(!visited[neighbourIndex]) {
				
				noOfChildren++;
				parents[neighbourIndex] = vertexIndex;
				DFS_MinAPFind(neighbourIndex, parents, visited, discTime, minDiscTime, ans);

				minDiscTime[vertexIndex] = Math.min(minDiscTime[neighbourIndex], minDiscTime[vertexIndex]);
				
				if((parents[vertexIndex] == -1 && noOfChildren >= 2) 
						|| (parents[vertexIndex] != -1 && minDiscTime[neighbourIndex] >= discTime[vertexIndex])) {
					if(ans[0] == -1) {
						ans[0] = RatingScore.get(vertexIndex);
					} else if(ans[0] > RatingScore.get(vertexIndex)) {
						ans[0] = RatingScore.get(vertexIndex);
					}
				}
			} else if (neighbourIndex != parents[vertexIndex]){
				minDiscTime[vertexIndex] = Math.min(discTime[neighbourIndex], minDiscTime[vertexIndex]);           
			}
		}
	}
}