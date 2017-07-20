package ps3;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import org.junit.Test;

public class HospitalRenovationTest {

//	public static final String filepath = "C:\\Users\\sylta\\Documents\\Git Repositories\\cs2010-zx\\src\\ps3";
	public static final String filepath = "D:\\Repos\\cs2010-zx\\src\\ps3\\";

	
	private int V; // number of vertices in the graph (number of rooms in the hospital)
	private Weighted_UDGraph adjList; // the graph (the hospital)
	private int[] RatingScore; // the weight of each vertex (rating score of each room)

	HospitalRenovation exe = new HospitalRenovation();
	
	
	@Test
	public void mainFileTest() throws IOException {
		File inputFile = new File(filepath + "test1.in");
		File outputFile = new File(filepath + "test1.in");

		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));
		
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int counter = 0;
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
			adjList = new Weighted_UDGraph(V, RatingScore);
			for (int i = 0; i < V; i++) {
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				while (k-- > 0) {
					int j = Integer.parseInt(st.nextToken());
					adjList.insert(i,j); // edge weight is always 1 (the weight is on vertices now)
				}
			}
			assertEquals(Integer.parseInt(brout.readLine()), exe.Query());
			System.out.println("INSTRUCTION" + ++counter);
		}
		pr.close();
	}

}
