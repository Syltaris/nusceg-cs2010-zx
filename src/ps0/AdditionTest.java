package ps0;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

public class AdditionTest {
	@Test
	public void sumOfValidBigIntMustBeCorrect() {
		//sum of the 2 numbers, A and B, must be correct
		//NOTE: must skip limits, else no way to break out of loop!
		BigInteger A = new BigInteger(64, new Random());
		BigInteger B = new BigInteger(64, new Random());
		
		BigInteger localResult = A.add(B);
		BigInteger sumResult = Addition.sum(A, B);
		
		assertEquals(localResult, sumResult);
	}

}
