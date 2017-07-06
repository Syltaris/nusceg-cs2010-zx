package ps3;

import java.util.*;
import java.io.*;

//write your matric number here: A0138915X
//write your name here:  Chong Ze Xuan
//write list of collaborators here: Tarjan (algo), Aakash Hasija (reference code)
//year 2017 hash code: AlaYnzmQ65P4x2Uc559u (do NOT delete this line)

class HospitalRenovation {
	private int V; // number of vertices in the graph (number of rooms in the hospital)
	private int[][] AdjMatrix; // the graph (the hospital)
	private int[] RatingScore; // the weight of each vertex (rating score of each room)

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class

	public HospitalRenovation() {
		// Write necessary code during construction
		//
		// write your answer here

	}

	int Query() {
		int ans = 0;

		// You have to report the rating score of the critical room (vertex)
		// with the lowest rating score in this hospital
		//
		// or report -1 if that hospital has no critical room
		//
		// write your answer here

		return ans;
	}

	// You can add extra function if needed
	// --------------------------------------------

	// --------------------------------------------

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
			RatingScore = new int[V];
			for (int i = 0; i < V; i++)
				RatingScore[i] = Integer.parseInt(st.nextToken());

			// clear the graph and read in a new graph as Adjacency Matrix
			AdjMatrix = new int[V][V];
			for (int i = 0; i < V; i++) {
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				while (k-- > 0) {
					int j = Integer.parseInt(st.nextToken());
					AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
				}
			}

			pr.println(Query());
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		HospitalRenovation ps3 = new HospitalRenovation();
		ps3.run();
	}
}

class IntegerPair implements Comparable<IntegerPair> {
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

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}
}