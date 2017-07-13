package ps4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;

public class GettingFromHereToThereTest {

	public static final String filepath = "C:\\Users\\sylta\\Documents\\Git Repositories\\cs2010-zx\\src\\ps3";

	ArrayList<ArrayList<IntegerPair>> AdjList;
	int V;
	
	@Test
	public void MainFileTest1() throws NumberFormatException, IOException {
	    // do not alter this method
		GettingFromHereToThere exe = new GettingFromHereToThere();
		File inputFile = new File(filepath + "test1.in");
		File outputFile = new File(filepath + "test1.in");

		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));
	    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	    
		int TC = Integer.parseInt(br.readLine()); // there will be several test cases
	    while (TC-- > 0) {
	      V = Integer.parseInt(br.readLine());

	      // clear the graph and read in a new graph as Adjacency List
	      AdjList = new ArrayList < ArrayList < IntegerPair > >();
	      for (int i = 0; i < V; i++) {
	        AdjList.add(new ArrayList < IntegerPair >());

	        int k = Integer.parseInt(br.readLine());
	        while (k-- > 0) {
	          int j = Integer.parseInt(br.readLine()), w = Integer.parseInt(br.readLine());
	          AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
	        }
	      }

	      exe.PreProcess(); // you may want to use this function or leave it empty if you do not need it

	      int Q = Integer.parseInt(br.readLine());
	      while (Q-- > 0)
	        pr.println(exe.Query(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
	      pr.println(); // separate the answer between two different graphs
	    }

	    pr.close();
	}

}
