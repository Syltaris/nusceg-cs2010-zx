package ps0;

import java.io.*;
import java.math.BigInteger;

public class Addition { // as the class name that contains the main method is "Addition", you have to save this file as "Addition.java", and submit "Addition.java" to Codecrunch
  public static void main(String[] args) {
    IntegerScanner sc = new IntegerScanner(System.in);
    while (true) {
      // Here is the full solution for this super simple practice task in pseudo code
      // read two integers A and B
      // if both are -1, stop
      // output A+B
    	BigInteger A = new BigInteger(Integer.toString(sc.nextInt()));
    	BigInteger B = new BigInteger(Integer.toString(sc.nextInt()));
    	
    	if (checkBreak(A, B)) {
    		break;
    	}
    	
    	System.out.println(sum(A,B));
    }
  }
  
  public static boolean checkBreak(BigInteger A, BigInteger B) {
	  return A.equals(new BigInteger("-1")) && B.equals(new BigInteger("-1"));
  }
  
  public static BigInteger sum(BigInteger A, BigInteger B) {
	  return A.add(B);
  }
}



class IntegerScanner { // coded by Ian Leow, we will use this quite often in CS2010 PSes
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }
  
  public int nextInt() {
    int result = 0;
    try {
    	
    	//SELF: checks if byte is -1? return immediately, means checking for null?
      int cur = bis.read();
      if (cur == -1)
        return -1;

      //SELF: what does this do? 48? 57? 57-48 == 9, so means 0-9, finding for bytecode of digits
      //until bytecode 45 is found, if found, skip to next part?
      while ((cur < 48 || cur > 57) && cur != 45) {
        cur = bis.read(); //reads in next byte untill bytecode of digit is reached
      }

      //SELF: 45 bytecode that means negative number?
      boolean negate = false;
      if (cur == 45) {
        negate = true;
        cur = bis.read(); //reads in next byte
      }

      //SELF: continues checking next bytes, while doing numerical conversion of each byte
      while (cur >= 48 && cur <= 57) {
        result = result*10 + (cur-48);
        cur = bis.read(); //reads in next number
      }

      //SELF: returns negated number as result, if negative sign found at start
      if (negate) {
        return -result;
      }
      return result;
    }
    catch (IOException ioe) {
      return -1;
    }
  }
}