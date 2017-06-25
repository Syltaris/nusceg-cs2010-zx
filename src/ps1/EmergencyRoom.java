package ps1;

//Given the names of N patients admitted into the emergency room, their initial emergency level, and subsequent updates of their emergency level, determine which patient the only doctor on duty (we assume here there is only one) has to give his/her most attention to.
//
//A patient with higher emergency level will have higher priority. If there are more than one patient with the same highest emergency level, this only doctor will give priority to the patient who arrived at the hospital earlier.
//
//The skeleton program EmergencyRoom.java (click to view) that can handle all input/output details is already written for you.
//
//You just need to implement four more methods/functions:
//
//    void ArriveAtHospital(String patientName, int emergencyLvl)
//    Insert this patientName and his/her initial emergency level (emergencyLvl) upon arrival at hospital into a suitable data structure of your choice.
//    patientName is a String that contains only uppercase alphabets with length between 1 to 15 characters.
//    The patient names are all unique.
//    emergency level is an integer between [30..100].
//    void UpdateEmergencyLvl(String patientName, int incEmergencyLvl)
//    emergency level can only go up to emergencyLvl = 100 and our test data will ensure that this method will not cause a patientName to have emergency level greater than 100.
//    What we guarantee is that incEmergencyLvl is an integer between [0..70] and before calling this method, patientName has arrived at the hospital.
//    void Treat(String patientName)
//    Upon calling this method, we assume the patientName will have been treated by the doctore and no longer need to be in the emergency room.
//    We guarantee that before calling this method, patientName has arrived at the hospital.
//    String Query() Query your data structure and report the name of the patient that the only doctor on duty has to give the most attention to.
//    See the priority criteria defined above.
//    If there is no more patient to be taken care of, return a String: "The emergency room is empty".
//
//Example:
//Let the chronological sequence of 15 events be as follows:
//
//    ArriveAtHospital("GRACE", 31)
//    ArriveAtHospital("THOMAS", 55)
//    ArriveAtHospital("MARIA", 42)
//    Query()
//    You have to print out "THOMAS", as he is currently the one with the highest emergency level.
//    To be precise, at the moment the order is: (THOMAS, 55), (MARIA, 42), (GRACE, 31).
//    ArriveAtHospital("CINDY", 77)
//    Query()
//    Now you have to print out "CINDY".
//    The current order is: (CINDY, 77), (THOMAS, 55), (MARIA, 42), (GRACE, 31).
//    UpdateEmergencyLvl("GRACE", 24)
//    After this event, the one with the highest emergency is still CINDY with emergencyLvl = 77.
//    "GRACE" now has emergencyLvl = 31+24 = 55, but this is still smaller than "CINDY".
//    Note that "THOMAS" also has emergencyLvl = 55 but "GRACE" is in front of "THOMAS" because "GRACE" arrived at the hospital earlier.
//    The current order is: (CINDY, 77), (GRACE, 55), (THOMAS, 55), (MARIA, 42).
//    Treat("CINDY")
//    "CINDY" is now treated by the doctor 'instantly', and she will be removed from the emergency room.
//    Query()
//    Now you have to print out "GRACE", as the current order is: (GRACE, 55), (THOMAS, 55), (MARIA, 42).
//    Treat("MARIA")
//    For whatever reasons "MARIA" suddenly reaches an emergency level of 100 and is treated by the doctor. She will now be removed from the emergency room.
//    Query()
//    The answer is still: "GRACE".
//    The current order is: (GRACE, 55), (THOMAS, 55).
//    Treat("GRACE")
//    Query()
//    You have to answer: "THOMAS".
//    The current order is: (THOMAS, 55).
//    Treat("THOMAS")
//    Query()
//    You have to answer: "The emergency room is empty".

// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0138915X
// write your name here: Chong Ze Xuan
// write list of collaborators here: 
// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)

class EmergencyRoom {
	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	private static final int MAX_EMERGENCY_LEVEL = 100;
	private ERPatient_MaxPriorityQueue patientQueue;

	public EmergencyRoom() {
		this.patientQueue = new ERPatient_MaxPriorityQueue(MAX_EMERGENCY_LEVEL);
	}

	void ArriveAtHospital(String patientName, int emergencyLvl) {
		patientQueue.insert(new ERPatient(patientName, emergencyLvl));
	}

	void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
		patientQueue.update_key(patientName, incEmergencyLvl);
	}

	void Treat(String patientName) {
		// This patientName is treated by the doctor
		// remove him/her from your chosen data structure
		//
		// write your answer here
		patientQueue.update_key(patientName, MAX_EMERGENCY_LEVEL);
		ERPatient nextPatient = patientQueue.extract_max();
	}

	// if null, don't change ans
	String Query() {
		String ans = "The emergency room is empty";

		// You have to report the name of the patient that the doctor
		// has to give the most attention to currently. If there is no more
		// patient to
		// be taken care of, return a String "The emergency room is empty"
		//
		// write your answer here
		ERPatient nextPatient = patientQueue.peek();
		if(nextPatient != null) {
			ans = nextPatient.getName();
		}

		return ans;
	}

	void run() throws Exception {
		// do not alter this method

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >=
														// N
		while (numCMD-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			switch (command) {
			case 0:
				ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken()));
				break;
			case 1:
				UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken()));
				break;
			case 2:
				Treat(st.nextToken());
				break;
			case 3:
				pr.println(Query());
				break;
			}
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		EmergencyRoom ps1 = new EmergencyRoom();
		ps1.run();
	}
}

class ERPatient_MaxPriorityQueue {
	private ERPatient[] arrayHeap;
	private int numOfPatients;

	public ERPatient_MaxPriorityQueue(int heapSize) {
		arrayHeap = new ERPatient[heapSize];
		arrayHeap[0] = null; // SELF: necessary?
		numOfPatients = 0;
	}

	public ERPatient peek() {
		return numOfPatients == 0? null : arrayHeap[1];
	}
	
	public void insert(ERPatient newPatient) {
		// simply insert patient to root if 1st element, increment numOfPatients
		// else, add patient to end of heap, check if need shiftUp starting from
		// last node
		if (numOfPatients == 0) {
			arrayHeap[++numOfPatients] = newPatient;
		} else {
			arrayHeap[++numOfPatients] = newPatient;

			shiftUp(numOfPatients);
		}
	}

	public ERPatient extract_max() {
		ERPatient outputPatient = arrayHeap[1];

		// if left 1 patient, just return last patient and lazy delete root
		if (numOfPatients == 1) {
			return arrayHeap[numOfPatients--];
		} else {
			arrayHeap[1] = arrayHeap[numOfPatients--]; // lazy delete of last
														// node, put to root
			shiftDown(1);
		}

		return outputPatient;
	}

	public void update_key(String patientName, int incEmergencyLvl) {
		int currPos = 0;
		
		for(int i = 1; i <= numOfPatients; i++) {
			//find patient and update priorityLevel
			if(arrayHeap[i].getName().equals(patientName)) {
				arrayHeap[i].setPriority(arrayHeap[i].getPriority() + incEmergencyLvl);
				currPos = i;
				break;
			}
		}
		//shift-up if curr node bigger than parent node
		//else shift down
		if(currPos<=1) {
			return;
		}
		else if((arrayHeap[currPos/2].getPriority() < arrayHeap[currPos].getPriority())) {
			shiftUp(currPos);
		} else if(!(currPos*2 > numOfPatients)){
			shiftDown(currPos);
		}
	}

	// helper methods
	// check if parent of current node has lower priority level,
	// if so, swap and check parent of updated node and repeat
	// else, continue checking next node sequentially in descending index
	private void shiftUp(int startPos) {
		int currPos = startPos;
		int parentPos = currPos / 2;

		while (currPos > 1 && (arrayHeap[parentPos].getPriority() < arrayHeap[currPos].getPriority())) {
			swap(parentPos, currPos);
			currPos = parentPos;
			parentPos = currPos / 2; // if odd, will round down to correct pos
		}
	}

	private void shiftDown(int startPos) {
		int currPos = startPos;
		int childPos = currPos * 2; // left child of current parent

		// find larger element of the 2 children
		if (arrayHeap[childPos + 1].getPriority() > arrayHeap[childPos].getPriority() && !(childPos >= numOfPatients)) {
			childPos++;
		}

		while (currPos < numOfPatients && (arrayHeap[childPos].getPriority() < arrayHeap[currPos].getPriority())) {
			swap(childPos, currPos);
			currPos = childPos;
			childPos = currPos * 2; // if odd, will round down to correct pos
		}
	}

	// receives 2 indexes of ERPatient to be swapped in arrayHeap and swaps them
	private void swap(int indexOfElement1, int indexOfElement2) {
		ERPatient tempERPatient = arrayHeap[indexOfElement1];
		arrayHeap[indexOfElement1] = arrayHeap[indexOfElement2];
		arrayHeap[indexOfElement2] = tempERPatient;
	}
}

// ERPatient bean class
class ERPatient {
	private String patientName;
	private int emergencyLevel;

	public ERPatient(String patientName, int emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
		this.patientName = patientName;
	}

	public String getName() {
		return this.patientName;
	}

	public int getPriority() {
		return this.emergencyLevel;
	}

	public void setPriority(int emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}
}
