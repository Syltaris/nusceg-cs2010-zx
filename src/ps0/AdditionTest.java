package ps0;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdditionTest {
	@Test
	public void sumOfValidIntMustBeCorrect() {
		//sum of the 2 numbers, A and B, must be correct
		//NOTE: must skip limits, else no way to break out of loop!
		for(int A = -2147483648; A < 2147483647; A++) {
			for(int B = -A+1 ; B > (-2147483648 - A); B--) {
				long sumResult = Addition.sum(A,B);
				long localResult = A+B;
				assertEquals(localResult, sumResult);
			}
		}
	}
	
	@Test
	public void sumOfInvalidIntMustOverflow() {
		int A = -2147483648;
		
		for(int i=-1; i>-5; i--) {
			long localResult = (long)A + i;
			long sumResult = Addition.sum(A, i);
			assertNotEquals(localResult, sumResult);
		}
		
		A = 2147483647;
		
		for(int i=1; i<5; i++) {
			long localResult = (long)A + i;
			long sumResult = Addition.sum(A, i);
			assertNotEquals(localResult, sumResult);
		}
	}

}
