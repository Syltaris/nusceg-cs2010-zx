package ps1;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import org.junit.Test;

public class EmergencyRoomTest {

	public static final int MAX_HEAP_SIZE = 100;
	ERPatient_MaxPriorityQueue patientQueue = new ERPatient_MaxPriorityQueue(MAX_HEAP_SIZE);
	
	@Test
	public void mainTestUsingFile() {
		EmergencyRoom ps1 = new EmergencyRoom();
		
//		String testInput = "15\n0 GRACE 31\n0 THOMAS 55\n0 MARIA 42\n3\n0 CINDY 77\n3\n1 GRACE 24\n2 CINDY\n3\n2 MARIA\n3\n2 GRACE\n3\n2 THOMAS\n3";
		
		File testFile = new File("D:\\Repos\\cs2010-zx\\src\\ps1\\test2.txt");
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(testFile));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int numCMD = 0;
		try {
			numCMD = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // note that numCMD is >=
														// N
		while (numCMD-- > 0) {
			StringTokenizer st;
			try {
				st = new StringTokenizer(br.readLine());

			int command = Integer.parseInt(st.nextToken());
			switch (command) {
			case 0:
				ps1.ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken()));
				break;
			case 1:
				ps1.UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken()));
				break;
			case 2:
				ps1.Treat(st.nextToken());
				break;
			case 3:
				pr.println(ps1.Query());
				break;
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println((26-numCMD) + ps1.showQueue());
		}
		pr.close();
	}
	
	@Test
	public void shiftUpTests() {
		patientQueue.insert(new ERPatient("SECOND PATIENT", 60));
		patientQueue.insert(new ERPatient("FIRST PATIENT", 30));
		patientQueue.insert(new ERPatient("FOURTH PATIENT", 100));
		patientQueue.insert(new ERPatient("THIRD PATIENT", 80));

	}
	
	@Test
	public void shiftUp_mustShiftHigherElementUpCorrectly() {
		fail("Not yet implemented");
	}
	
	@Test
	public void shiftUp_mustNotShiftLowerElementUp() {
		fail("Not yet implemented");
	}

	
	
	
	//TODO: test ERPatient_MaxPriorityQueue functions
	
}
