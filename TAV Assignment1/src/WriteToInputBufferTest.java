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
		assertEquals("n = -1 must fail", 5, tester.writeToInputBuffer(-1, "hello"));
		assertEquals("buffer must be empty", "", tester.getBuffer());
	}
	
	@Test
	public void nGreaterThanSLengthShouldFail() {
		assertEquals("n = 30, s = \"hello\" must fail", 5, tester.writeToInputBuffer(30, "hello"));
		assertEquals("buffer must be empty", "", tester.getBuffer());
	}
	
	@Test
	public void tooLargeNShouldFail() {
		String veryLongString = "1234567890qwertyuiopasdfghjkl";
		veryLongString += veryLongString + veryLongString + veryLongString + veryLongString; 
		assertEquals("n = 101 must fail", 5, tester.writeToInputBuffer(200, "hello"));
		assertEquals("buffer must be empty", "", tester.getBuffer());
	}
	
	@Test
	public void confirmationTest() {
		assertEquals("n = 3, s = \"hello\" must succeed", 0, tester.writeToInputBuffer(3, "hello"));
		assertEquals("buffer must be \"hel\"", "hel", tester.getBuffer());
	}


}
