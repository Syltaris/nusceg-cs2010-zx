package ps0;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
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
	
	@Test
	public void checkBreakMustReturnTrue() {
		BigInteger A = new BigInteger("-1");
		BigInteger B = new BigInteger("-1");
		
		assertTrue(Addition.checkBreak(A, B));
	}
	
	@Test
	public void checkBreakMustReturnFalse() {
		BigInteger A = new BigInteger("-1");
		BigInteger B = new BigInteger("4");
		
		assertFalse(Addition.checkBreak(A, B));
	}
	
	@Test
	public void inputsMustBeParsedCorrectly() {
		String testString = "7 4\n-1 -1";
		IntegerScanner sc = new IntegerScanner(new ByteArrayInputStream(testString.getBytes()));
		
    	BigInteger A = new BigInteger(Integer.toString(sc.nextInt()));
    	BigInteger B = new BigInteger(Integer.toString(sc.nextInt()));

		assertEquals(new BigInteger("7"), A);
		assertEquals(new BigInteger("4"), B);
		
    	A = new BigInteger(Integer.toString(sc.nextInt()));
    	B = new BigInteger(Integer.toString(sc.nextInt()));

		assertEquals(new BigInteger("-1"), A);
		assertEquals(new BigInteger("-1"), B);
	}
}
