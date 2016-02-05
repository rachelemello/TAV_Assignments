import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ReadSpeedTorqueTest {
	
	ArduinoCommunication tester = new ArduinoCommunication();
	
	String sDel = "001010100111001100101010";
	String eDel = "001010100110010100101010";
	String vDel = "001010100111011000101010";
	String tDel = "001010100111010000101010";
	
	String number1 = "10101010";
	String number2 = "00000000";
	String number3 = "11111111";
	
	
	
	
	@Before
	public void cleanBuffer() {
		tester.setBuffer("");
	}
	
	// Tests for helper method isPackageOk
	@Test
	public void noVDelShouldFail() {
		assertEquals("s is missing v delimiter should fail", false, tester.isPackageOk(sDel+number1+tDel+number2+eDel));	
	}
	
	@Test
	public void noTDelShouldFail() {
		assertEquals("s is missing t delimiter should fail", false, tester.isPackageOk(sDel+vDel+number1+number2+eDel));	
	}
	
	@Test
	public void wrongLengthShouldFail() {
		assertEquals("s is of wrong length should fail", false, tester.isPackageOk(sDel+eDel));
	}
	
	@Test
	public void validationTestHelper() {
		assertEquals("s has delimiters and is of correct length should pass", true, tester.isPackageOk(sDel+vDel+number1+tDel+number2+eDel));
	}
	
	
	// Tests for the method readSpeedTorque
	@Test
	public void noStartDelShouldFail() {
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		
		tester.setBuffer(vDel+number1+tDel+number2+eDel);
		assertEquals("buffer is missing start delimiter should fail", error, tester.readSpeedTorque());
		
	}
	
	@Test
	public void noEndDelShouldFail() {
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		
		tester.setBuffer(sDel+vDel+number1+tDel+number2);
		assertEquals("buffer is missing end delimiter should fail", error, tester.readSpeedTorque());
	}
	
	@Test
	public void validationTest() {
		int[] result = new int[2];
		result[0] = 170;
		result[1] = 0;
		tester.setBuffer(sDel+vDel+number1+tDel+number2+eDel);
		assertEquals("buffer is valid should succeed", result, tester.readSpeedTorque());
	}

}
