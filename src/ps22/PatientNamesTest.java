package ps22;

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

public class PatientNamesTest {

	PatientNames exe = new PatientNames();
	
	File testFile = new File("D:\\Repos\\cs2010-zx\\src\\ps22\\test1.in");
	File outputFile = new File("D:\\Repos\\cs2010-zx\\src\\ps22\\test1.out");

	@Test
	public void mainFileTest() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(testFile));
		BufferedReader brout = new BufferedReader(new FileReader(outputFile));

		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
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
				assertEquals(brout.readLine(),
						exe.Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER
			
			System.out.println(exe.getMaleBST());
			System.out.println(exe.getFemaleBST());
		}
		
		br.close();
		brout.close();
		pr.close();
	}

}
