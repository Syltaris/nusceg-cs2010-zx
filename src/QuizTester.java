

public class QuizTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		int[] A = {15, 36, 16, 1, 12, 3, 7, 10};
//		int n = A.length;
//		for (int i = 0; i < 3; i++) { // 3 passes
//			  int cur_min = i;
//			  for (int j = i+1; j < n; j++)
//			    if (A[j] < A[cur_min])
//			      cur_min = j;
//			  
//			  int temp = A[i];
//			  A[i] = A[cur_min];
//			  A[cur_min] = temp;
//			}
//		
//		int i = 0;
//		while(n-- > 0) {
//			System.out.print(A[i++]+",");
//		}
		
//		//INSERTION SORT
// 		int[] A = {33, 31, 4, 28, 8, 15, 1, 26};
// 		int n = A.length;
// 		int comparisons = 0;
// 		int swaps = 0;
//		for (int i = 1; i < n; i++) {
//			  int e = A[i]; int j = i;
//			  while (j > 0) {
//				comparisons++;
//			    if (A[j-1] > e) {// each execution of this if-statement is counted as one comparison
//			      A[j] = A[j-1];
//			      swaps++;
//			    }
//			    else
//			      break;
//			    j--;
//			  }
//			  A[j] = e;
//			}
//		
//		
//		System.out.println("COM: "+ comparisons);
//		System.out.println("SWA: "+ swaps);	
//		int i = 0;
//		while(n-->0) {
//			System.out.print(A[i++]+",");
//		}
//		
//		
		// BUBBLE SORT
		int[] A = {36, 33, 31, 16, 13, 17, 22, 9};
		int n = A.length;
		int j = 0;
		int i = 0;
		boolean swapped = false;
		int comparisons = 0;
		int swaps = 0;
		int passes = 0;
		int e = 0;
		int cur_min = 9999;

		for (i = 1; i <= 5; i++) { // 5 passes
			  e = A[i]; j = i;
			  while (j > 0) {
			    if (A[j-1] > e)
			      A[j] = A[j-1];
			    else
			      break;
			    j--;
			  }
			  A[j] = e;
			}




		
		System.out.println("PAS: " + passes);
		System.out.println("COM: " + comparisons);
		System.out.println("SWA: " + swaps);		
		int k = 0;
		while(n-->0) {
			System.out.print(A[k++]+",");
		}
//		
//		int temp = A[i];
//		A[i] = A[cur_min];
//		A[cur_min] = temp;
		
//		int temp = A[i];
//		A[i] = A[i+1];
//		A[i+1] = temp;
		
//		//SELECTION SORT
//		int[] A = { 39, 4, 20, 3, 31, 27, 37, 24 };
//		int n = A.length;
//		boolean swapped = false;
//		int comparisons = 0;
//		int swaps = 0;
//		int passes = 0;
//		
//		int cur_min = 0;
//		for (int i = 0; i < 3; i++) { // 3 passes
//			passes++;
//			  cur_min = i;
//			  for (int j = i+1; j < n; j++) {
//			    if (A[j] < A[cur_min]) {
//			      comparisons++;
//			    	cur_min = j;
//			    }
//			  }
//			  
//			  int temp = A[i];
//			  A[i] = A[cur_min];
//			  A[cur_min] = temp;
//			  
//			  swaps++;
//			  
//			}
//		
//		System.out.println("PAS: " + passes);
//		System.out.println("COM: " + comparisons);
//		System.out.println("SWA: " + swaps);
//		
//		int i = 0;
//		while(n-->0) {
//			System.out.print(A[i++]+",");
//		}
		
	}

}
