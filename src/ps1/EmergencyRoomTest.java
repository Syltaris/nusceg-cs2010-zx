package ps1;
import static org.junit.Assert.*;

import org.junit.Test;

public class EmergencyRoomTest {

	public static final int MAX_HEAP_SIZE = 100;
	ERPatient_MaxPriorityQueue patientQueue = new ERPatient_MaxPriorityQueue(MAX_HEAP_SIZE);
	
	@Test
	public void shiftUpTests() {
		patientQueue.insert(new ERPatient("FIRST PATIENT", 30));
		patientQueue.insert(new ERPatient("SECOND PATIENT", 60));
		patientQueue.insert(new ERPatient("THIRD PATIENT", 100));

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
