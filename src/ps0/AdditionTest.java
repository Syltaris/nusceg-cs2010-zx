package ps0;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

public class AdditionTest {
	@Test
	public void sumOfValidBigIntMustBeCorrect() {
		
    	BigInteger A = new BigInteger("734534534534647673");
    	BigInteger B = new BigInteger("899998989894895385");
		
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
		String testString = "12 128\n234234234234 4\n-1 -1";
		IntegerScanner sc = new IntegerScanner(new ByteArrayInputStream(testString.getBytes()));
		
    	BigInteger A = sc.nextInt();
    	BigInteger B = sc.nextInt();

		assertEquals(new BigInteger("12"), A);
		assertEquals(new BigInteger("128"), B);
    	
    	A = sc.nextInt();
    	B = sc.nextInt();

		assertEquals(new BigInteger("234234234234"), A);
		assertEquals(new BigInteger("4"), B);
		
    	A = sc.nextInt();
    	B = sc.nextInt();

		assertEquals(new BigInteger("-1"), A);
		assertEquals(new BigInteger("-1"), B);
	}
}
