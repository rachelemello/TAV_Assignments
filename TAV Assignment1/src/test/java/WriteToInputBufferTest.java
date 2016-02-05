import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WriteToInputBufferTest {
	
	ArduinoCommunication tester = new ArduinoCommunication();
	
	@Before
	public void cleanBuffer() {
		tester.setBuffer("");
	}

	@Test
	public void negativeNShouldFail() {
		assertEquals("n = -1, s not empty, must fail", 5, tester.writeToInputBuffer(-1, "hello"));
		assertEquals("buffer must be empty", "", tester.getBuffer());
		
		tester.setBuffer("");
		assertEquals("n = -1, s empty, must fail", 5, tester.writeToInputBuffer(-1, ""));
		assertEquals("buffer must be empty", "", tester.getBuffer());
		
		tester.setBuffer("hello");
		assertEquals("n = -1, s empty, must fail", 5, tester.writeToInputBuffer(-1, ""));
		assertEquals("buffer must be same as before", "hello", tester.getBuffer());
		
	}
	
	@Test
	public void nGreaterThanSLengthShouldFail() {
		assertEquals("n = 30, s = \"hello\" must fail", 5, tester.writeToInputBuffer(30, "hello"));
		assertEquals("buffer must be empty", "", tester.getBuffer());
		
		tester.setBuffer("");
		assertEquals("n = 30, s empty must fail", 5, tester.writeToInputBuffer(30, ""));
		assertEquals("buffer must be empty", "", tester.getBuffer());
	}
	
	@Test
	public void tooLargeNShouldFail() {
		String veryLongString = "1234567890qwertyuiopasdfghjkl";
		veryLongString += veryLongString + veryLongString + veryLongString + veryLongString; 
		
		assertEquals("n = 101 must fail", 5, tester.writeToInputBuffer(101, veryLongString));
		assertEquals("buffer must be empty", "", tester.getBuffer());
	}
	
	@Test
	public void confirmationTest() {
		assertEquals("n = 3, s = \"hello\" must succeed with empty buffer", 0, tester.writeToInputBuffer(3, "hello"));
		assertEquals("buffer must be \"hel\"", "hel", tester.getBuffer());
		
		tester.setBuffer("hello");
		assertEquals("n = 3, s = \"hello\" must succeed with unempty buffer", 0, tester.writeToInputBuffer(3, "hello"));
		assertEquals("buffer must be \"hellohel\"", "hellohel", tester.getBuffer());
		
		tester.setBuffer("");
		assertEquals("n = 0, s = \"hello\" must succeed, with empty buffer", 0, tester.writeToInputBuffer(0, "hello"));
		assertEquals("buffer must be \"\"", "", tester.getBuffer());
		
	}


}
