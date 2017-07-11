package ps222;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.junit.Test;



public class PatientNamesTest {

	public static final String filePath = "C:\\Users\\sylta\\Documents\\Git Repositories\\cs2010-zx\\src\\ps222\\";
	
	PatientNames exe = new PatientNames();
	
	@Test
	public void mainFile1Test() throws IOException {
		File testFile = new File(filePath + "test1.in");
		File outputFile = new File(filePath + "test1.out");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));

		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int counter = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddPatient
				exe.AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemovePatient
				exe.RemovePatient(st.nextToken());
			else // if (command == 3) // Query
				assertEquals(Integer.parseInt(brout.readLine()),
						exe.Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER

			System.out.println("INSTRUCTION " + counter++);
			System.out.println(exe.getMaleBST());
			System.out.println(exe.getFemaleBST());
		}
		
		br.close();
		brout.close();
		pr.close();
	}
	
	@Test
	public void mainFile2Test() throws IOException {
		File testFile = new File(filePath + "test2.in");
		File outputFile = new File(filePath + "test2.out");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));

		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int counter = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddPatient
				exe.AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemovePatient
				exe.RemovePatient(st.nextToken());
			else // if (command == 3) // Query
				assertEquals(Integer.parseInt(brout.readLine()),
						exe.Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER

			System.out.println("INSTRUCTION " + counter++);
			System.out.println(exe.getMaleBST());
			System.out.println(exe.getFemaleBST());
		}
		
		br.close();
		brout.close();
		pr.close();
	}
	
	@Test
	public void mainFile3Test() throws IOException {
		File testFile = new File(filePath + "test3.in");
		File outputFile = new File(filePath + "test3.out");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));

		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int counter = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddPatient
				exe.AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemovePatient
				exe.RemovePatient(st.nextToken());
			else // if (command == 3) // Query
				assertEquals(Integer.parseInt(brout.readLine()),
						exe.Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER

			System.out.println("INSTRUCTION " + counter++);
			System.out.println(exe.getMaleBST());
			System.out.println(exe.getFemaleBST());
		}
		
		br.close();
		brout.close();
		pr.close();
	}
	
	@Test
	public void mainFile4Test() throws IOException {
		File testFile = new File(filePath + "test4.in");
		File outputFile = new File(filePath + "test4.out");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));

		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int counter = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddPatient
				exe.AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemovePatient
				exe.RemovePatient(st.nextToken());
			else // if (command == 3) // Query
				assertEquals(Integer.parseInt(brout.readLine()),
						exe.Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER

			System.out.println("INSTRUCTION " + counter++);
			System.out.println(exe.getMaleBST());
			System.out.println(exe.getFemaleBST());
		}
		
		br.close();
		brout.close();
		pr.close();
	}
	
	@Test
	public void mainFile5Test() throws IOException {
		File testFile = new File(filePath + "test5.in");
		File outputFile = new File(filePath + "test5.out");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));

		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int counter = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddPatient
				exe.AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemovePatient
				exe.RemovePatient(st.nextToken());
			else // if (command == 3) // Query
				assertEquals(Integer.parseInt(brout.readLine()),
						exe.Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER

			System.out.println("INSTRUCTION " + counter++);
			System.out.println(exe.getMaleBST());
			System.out.println(exe.getFemaleBST());
		}
		
		br.close();
		brout.close();
		pr.close();
	}

	
	@Test
	public void fileInputValidTest() throws IOException {
		File testFile = new File(filePath + "test1.in");
		File outputFile = new File(filePath + "test1.out");
		
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		int maxL = 0;
	    int N = 0, Q = 0;
	    TreeSet names = new TreeSet();
	    while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
	      if (command == 0) // end of input
	        break;
	      else if (command == 1) { // AddPatient
	        String name = st.nextToken();
	        int gender = Integer.parseInt(st.nextToken());
	        if (name.length() > 30) {
	          System.out.println("LONGER THAN 30 CHARS: " + name);
	          fail();
	          return;
	        }
	        if (gender != 1 && gender != 2) {
	          System.out.println("ERROR, GENDER MUST BE 1 OR 2");
	          fail();
	          return;
	        }
	        if (names.contains(name)) { // make it unique
	          System.out.println("NOT UNIQUE: " + name + " " + gender);
	          fail();
	          return;
	        }
	        names.add(name);
	        N++;
	        if (N > 20000) {
	          System.out.println("ERROR, TOO MANY patient names");
	          fail();
	          return;
	        }
	      }
	      else if (command == 2) { // RemovePatient
		        String name = st.nextToken();
	        if (name.length() > 30) {
	          System.out.println("LONGER THAN 30 CHARS: " + name);
	          fail();
	          return;
	        }
	        if (!names.contains(name)) { // name has to be added before
	          System.out.println("NOT IN THE DATA STRUCTURE BEFORE: " + name);
	          fail();
	          return;
	        }
	        names.remove(name);
	        N--;
	      }
	      else { // if (command == 3) // Query
	        String START = st.nextToken();
	        String END = st.nextToken();
	        if (START.length() > 30 || END.length() > 30) {
	          System.out.println("LONGER THAN 30 CHARS: " + START + ", " + END);
	          fail();
	          return;
	        }
	        if (START.compareTo(END) >= 0) {
	          System.out.println("ERROR, START MUST BE < END");
	          fail();
	          return;
	        }
	        int gender = Integer.parseInt(st.nextToken());
	        if (gender < 0 || gender > 2) {
	          System.out.println("ERROR, GENDER MUST BE 0, 1, OR 2");
	          fail();
	          return;
	        }
	        Q++;
	      }
	    }

	    if (Q > 20000) {
	      System.out.println("ERROR, TOO MANY QUERIES");
	      fail();
	      return;
	    }

	    System.out.println("Test data is valid"); 
	}
}
