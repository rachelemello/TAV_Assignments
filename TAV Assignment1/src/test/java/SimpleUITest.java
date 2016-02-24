import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleUITest {
	SimpleUI tester = new SimpleUI();
	
	// Variables used in tests:
	String empty = ""; //empty string
	String text = "blabla"; //some text string
	String integer = "208"; //some string representing an int
	String floatNum = "3.6"; //some string representing a floating number
	
	// Test cases for method areInputsInts()
	
	/* In this test one of the parameters (i, meaning the IR distance) is an empty 
	 * String (as if the user left the field empty). 
	 * The method should return false.
	 */
	@Test
	public void emptyFieldShouldFail() {
		assertEquals("i is empty string should return false", false, tester.areInputsInts(text, integer, empty));
	}
	
	/* In this test one of the parameters (first torque, then UR distance,
	 * then IR distance) is a string containing text, not an integer.
	 * The method should return false.
	 */
	@Test
	public void notIntegerParameterShouldFail() {
		assertEquals("t is a text string should return false", false, tester.areInputsInts(text, integer, floatNum));
		assertEquals("u is a decimal string should return false", false, tester.areInputsInts(integer, floatNum, text));
		assertEquals("i is a text string should return false", false, tester.areInputsInts(integer, integer, text));	
	}
	
	
	/* In this test all the parameters are valid: they are non empty strings that
	 * represent an integer value.
	 * The method should return true.
	 */
	@Test
	public void validationTest() {
		assertEquals("t, u, i are all strings representing integers should return true", true, tester.areInputsInts(integer, integer, integer));
	}
}
