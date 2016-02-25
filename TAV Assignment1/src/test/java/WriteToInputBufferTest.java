import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WriteToInputBufferTest {
	
	ArduinoCommunication tester = new ArduinoCommunication();
	
	@Before
	public void cleanBuffer() {
		tester.setInBuffer("");
	}
	
       /*
	* First test tries to write the inputBuffer by passing a negative int (-1) 	
	* as parameter n and also s is tested with values "hello", "", "" respectively in that order.
	* Test should return 5 which is the error code. Buffer should empty. 
	*/
	@Test
	public void negativeNShouldFail() {
		assertEquals("n = -1, s not empty, must fail", 5, tester.writeToInputBuffer(-1, "hello")); // n=-1, s= "hello"
		assertEquals("buffer must be empty", "", tester.getInBuffer());
		
		tester.setInBuffer(""); // seting empty buffer
		assertEquals("n = -1, s empty, must fail", 5, tester.writeToInputBuffer(-1, ""));// n=-1, s= ""
		assertEquals("buffer must be empty", "", tester.getInBuffer());
		
		tester.setInBuffer("hello");
		assertEquals("n = -1, s empty, must fail", 5, tester.writeToInputBuffer(-1, ""));// n=-1, s= ""
		assertEquals("buffer must be same as before", "hello", tester.getInBuffer());
		
	}
	
	/*
	* The test tries to write the inputBuffer by passing an int value	
	* as parameter n where n > s.length(). s is set to values "hello", "", "" respectively in that order
	* throughout the test.
	* Test should return 5 which is the error code. Buffer should be empty. 
	*/
	@Test
	public void nGreaterThanSLengthShouldFail() {
		assertEquals("n = 30, s = \"hello\" must fail", 5, tester.writeToInputBuffer(30, "hello"));// n=30, s= "hello"
		assertEquals("buffer must be empty", "", tester.getInBuffer());
		
		tester.setInBuffer("");
		assertEquals("n = 30, s empty must fail", 5, tester.writeToInputBuffer(30, ""));// n=30, s= ""
		assertEquals("buffer must be empty", "", tester.getInBuffer());
	}
	
	/*
	* The test tries to write the inputBuffer by passing an int value	
	* as parameter n where n = 101 (n > 100).
	* s = "1234567890qwertyuiopasdfghjkl1234567890qwertyuiopasdfghjkl1234567890qwertyuiopasdfghjkl1234567890qwertyuiopasdfghjkl1234567890qwertyuiopasdfghjkl"
	* Test should return 5 which is the error code. Buffer should be empty. 
	*/
	@Test
	public void tooLargeNShouldFail() {
		String veryLongString = "1234567890qwertyuiopasdfghjkl";
		veryLongString += veryLongString + veryLongString + veryLongString + veryLongString; 
		
		assertEquals("n = 101 must fail", 5, tester.writeToInputBuffer(101, veryLongString));
		assertEquals("buffer must be empty", "", tester.getInBuffer());
	}
	
	/*
	* The test tries to write the inputBuffer by passing an int value	
	* as parameter n where n <= s.length(), n is tested with the values 3,3,o respectively  in that order
	* s is set to value "hello", throughout the test.
	* 
	* Test should return 0. Buffer should be updated, see comments in method. 
	*/
	@Test
	public void confirmationTest() {
		assertEquals("n = 3, s = \"hello\" must succeed with empty buffer", 0, tester.writeToInputBuffer(3, "hello"));// n = 3, s = "hello"
		assertEquals("buffer must be \"hel\"", "hel", tester.getInBuffer()); //check buffer, should be "hel"
		
		tester.setInBuffer("hello"); // set buffer to "hello"
		assertEquals("n = 3, s = \"hello\" must succeed with unempty buffer", 0, tester.writeToInputBuffer(3, "hello"));// n = 3, s = "hello"
		assertEquals("buffer must be \"hellohel\"", "hellohel", tester.getInBuffer()); //check buffer, should be "hellohel"
		
		tester.setInBuffer("");//Set buffer empy
		assertEquals("n = 0, s = \"hello\" must succeed, with empty buffer", 0, tester.writeToInputBuffer(0, "hello"));// n = 3, s = ""
		assertEquals("buffer must be \"\"", "", tester.getInBuffer()); //check buffer, should be empty
		
	}


}
