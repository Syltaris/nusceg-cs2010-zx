package ps1;

import java.util.*;
import java.io.*;

// write your matric number here: A0138915X
// write your name here: Chong Ze Xuan
// write list of collaborators here: 
// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)

class EmergencyRoom {
	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	private static final int MAX_EMERGENCY_LEVEL = 200000;
	private ERPatient_MaxPriorityQueue patientQueue;

	public EmergencyRoom() {
		this.patientQueue = new ERPatient_MaxPriorityQueue(MAX_EMERGENCY_LEVEL+1);
	}

	void ArriveAtHospital(String patientName, int emergencyLvl) {
		patientQueue.insert(new ERPatient(patientName, emergencyLvl));
	}

	void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
		patientQueue.update_key(patientName, incEmergencyLvl);
	}

	void Treat(String patientName) {
		try {		
			patientQueue.update_key(patientName, MAX_EMERGENCY_LEVEL);
			patientQueue.extract_max();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// if null, don't change ans
	String Query() {
		String ans = "The emergency room is empty";

		ERPatient nextPatient = patientQueue.peek();
		if(nextPatient != null) {
			ans = nextPatient.getName();
		}

		return ans;
	}
	
	public String showQueue() {
		return patientQueue.toString();
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
		return numOfPatients == 0 ? null : arrayHeap[1];
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

	public ERPatient extract_max() throws Exception {
		ERPatient outputPatient = arrayHeap[1];

		if(numOfPatients <= 0) {
			throw new Exception("invalid array index");
		}
		
		if (numOfPatients == 1) {
			return arrayHeap[numOfPatients--]; 	// if left 1 patient, just return last patient and lazy delete root	

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
		else if((arrayHeap[currPos/2].getPriority() <= arrayHeap[currPos].getPriority())) {
			shiftUp(currPos);
		} else if(currPos*2 <= numOfPatients){
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

		while ((parentPos > 0) && (arrayHeap[parentPos].getPriority() <= arrayHeap[currPos].getPriority())) {
			//if same emergency level but curr patient came later than parent patient
			if(arrayHeap[parentPos].getPriority() == arrayHeap[currPos].getPriority()
					&& arrayHeap[parentPos].getID() < arrayHeap[currPos].getID()) {
				break;
			}
			swap(parentPos, currPos);
			currPos = parentPos;
			parentPos = currPos / 2; // if odd, will round down to correct pos
		}
	}

	private void shiftDown(int startPos) {
		int currPos = startPos;
		int childPos = currPos * 2; // left child of current parent

		// find larger element of the 2 children
		if (!(childPos >= numOfPatients) && (arrayHeap[childPos + 1].getPriority() > arrayHeap[childPos].getPriority())) {
			childPos++;
		}
		
		while ((childPos <= numOfPatients) && (arrayHeap[childPos].getPriority() >= arrayHeap[currPos].getPriority())) {
			//if same emergency level but child came before curr patient
			if(arrayHeap[childPos].getPriority() == arrayHeap[currPos].getPriority()
					&& arrayHeap[childPos].getID() > arrayHeap[currPos].getID()) {
				break;
			}
			
			swap(childPos, currPos);
			currPos = childPos;
			childPos = currPos * 2; // if odd, will round down to correct pos
			
			if (!(childPos >= numOfPatients) && (arrayHeap[childPos + 1].getPriority() > arrayHeap[childPos].getPriority())) {
				childPos++;
			}
		}
	}

	// receives 2 indexes of ERPatient to be swapped in arrayHeap and swaps them
	private void swap(int indexOfElement1, int indexOfElement2) {
		ERPatient tempERPatient = arrayHeap[indexOfElement1];
		arrayHeap[indexOfElement1] = arrayHeap[indexOfElement2];
		arrayHeap[indexOfElement2] = tempERPatient;
	}
	
	@Override
	public String toString() {
		String output = new String();
		for(int i=1; i<=numOfPatients; i++) {
			output = output.concat(arrayHeap[i].getName());
			output = output.concat(" " + arrayHeap[i].getPriority()+"\n");
		}
		
		return output;
	}
}

// ERPatient bean class
class ERPatient {
	private static int nextPatientID = 0;
	
	private String patientName;
	private int emergencyLevel;
	private int patientID;

	public ERPatient(String patientName, int emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
		this.patientName = patientName;
		this.patientID = nextPatientID++;
	}

	public String getName() {
		return this.patientName;
	}

	public int getPriority() {
		return this.emergencyLevel;
	}
	
	public int getID() {
		return this.patientID;
	}

	public void setPriority(int emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}
}
