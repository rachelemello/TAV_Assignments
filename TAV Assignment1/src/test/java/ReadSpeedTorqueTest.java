import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;


public class ReadSpeedTorqueTest {
	
	private ArduinoController aController;
	
	private static USBConnection mockedUSB;
	
	//Delimiter values in binary
	String sDel = "001010100111001100101010"; // *s* - start of package delimiter
	String eDel = "001010100110010100101010"; // *e* - end of package delimiter
	String vDel = "001010100111011000101010"; //*v* - speed delimiter
	String tDel = "001010100111010000101010"; // *t* - torque delimiter
	//Strings of bits for testing
	String number1 = "10101010"; // 170
	String number2 = "00000000"; // 0
	String number3 = "11111111"; // 255
	
	@BeforeClass
	public static void setUp(){
		mockedUSB = Mockito.mock(USBConnection.class);
	}
	
	// Tests for helper method isPackageOk
	
	/* This test tries to send the helper method a string of bits where the speed delimiter (*v*)	
	* isn't inlcuded.
	* Test should return false 
	*/
	@Test
	public void noVDelShouldFail() {
		aController = new ArduinoController(mockedUSB);
		assertEquals("s is missing v delimiter should fail", false, aController.AC.isPackageOk(sDel+number1+tDel+number2+eDel));//number1 = 170, number2 = 0	
	}
	
	/* This test tries to send the helper method a string of bits where the torque delimiter (*v*)	
	* isn't inlcuded.
	* Test should return false 
	*/
	@Test
	public void noTDelShouldFail() {
		aController = new ArduinoController(mockedUSB);
		assertEquals("s is missing t delimiter should fail", false, aController.AC.isPackageOk(sDel+vDel+number1+number2+eDel));//number1 = 170, number2 = 0	
	}
	
	/* This test tries to send the helper method a string of bits where the lenght of the string of bits	
	* isn't as expected. Expected length is 112, test sens a string of length 48,
	* Test should return false. 
	*/
	@Test
	public void wrongLengthShouldFail() {
		aController = new ArduinoController(mockedUSB);
		assertEquals("s is of wrong length should fail", false, aController.AC.isPackageOk(sDel+eDel));
	}
	
	/* This test tries to send the helper method a string of bits where the lenght of the string of bits	
	* is as expected, expected length is 112. The string of bits has all delimiters and values and is a therefore
	* valid string of bits. Test should return true. 
	*/
	@Test
	public void validationTestHelper() {
		aController = new ArduinoController(mockedUSB);
		assertEquals("s has delimiters and is of correct length should pass", true, aController.AC.isPackageOk(sDel+vDel+number1+tDel+number2+eDel));//number1 = 170, number2 = 0
	}
	
	
	// Tests for the method readSpeedTorque
	
	/* This test set the buffer to a string of bits  which is valid apart from 
	*  the start of package-delimiter(*s*) which has been omitted.	
	*  Running readSpeedTorque() with this buffer should return error (int[] error = {-1,-1})
	*/
	@Test
	public void noStartDelShouldFail() {
		aController = new ArduinoController(mockedUSB);
		
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		
		String speedString = String.format("%8s", Integer.toBinaryString(0)).replace(' ', '0');
		String torqueString = String.format("%8s", Integer.toBinaryString(0)).replace(' ', '0');
		
		String buffer = vDel + speedString + tDel + torqueString + eDel;
		
		when(mockedUSB.readSpeedTorque()).thenReturn(buffer);
		
		aController.AC.setInBuffer(vDel+number1+tDel+number2+eDel);//number1 = 170, number2 = 0
		assertArrayEquals("buffer is missing start delimiter should fail", error, aController.AC.readSpeedTorque());
		
	}
	
	/* This test set the buffer to a string of bits  which is valid apart from 
	*  the end of package-delimiter(*e*) which has been omitted.	
	*  Running readSpeedTorque() with this buffer should return error (int[] error = {-1,-1})
	*/
	@Test
	public void noEndDelShouldFail() {
		aController = new ArduinoController(mockedUSB);
		
		String speedString = String.format("%8s", Integer.toBinaryString(0)).replace(' ', '0');
		String torqueString = String.format("%8s", Integer.toBinaryString(0)).replace(' ', '0');
		
		String buffer = sDel + vDel + speedString + tDel + torqueString;
		
		when(mockedUSB.readSpeedTorque()).thenReturn(buffer);
		
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		
		aController.AC.setInBuffer(sDel+vDel+number1+tDel+number2);//number1 = 170, number2 = 0
		assertArrayEquals("buffer is missing end delimiter should fail", error, aController.AC.readSpeedTorque());
	}
	
	/* This test set the buffer to a string of bits which is valid.	
	*  Test method parses the bits in number1 and number 2 and compares them to the result received from 
	*  readSpeedTorque().
	*  Running readSpeedTorque() with this buffer should return speed and torque (int[] outputFromFunction = {170,0})
	*/
	@Test
	public void validationTest() {
		aController = new ArduinoController(mockedUSB);
		
		int[] result = new int[2];
		result[0] = Integer.parseInt(number1,2);//parsing number1 binary to decimal
		result[1] = Integer.parseInt(number2,2);//parsing number2 binary to decimal, int[] result ={170,0}
		
		String speedString = String.format("%8s", Integer.toBinaryString(result[0])).replace(' ', '0');
		String torqueString = String.format("%8s", Integer.toBinaryString(result[1])).replace(' ', '0');
		
		String buffer = sDel + vDel + speedString + tDel + torqueString + eDel;
		
		when(mockedUSB.readSpeedTorque()).thenReturn(buffer);
		
		aController.AC.setInBuffer(sDel+vDel+number1+tDel+number2+eDel);//number1 = 170, number2 = 0
		assertArrayEquals("buffer is valid should succeed", result, aController.AC.readSpeedTorque());
	}
	
	private static String build_SpeedTorqueBitstreamString(int speed, int torque){
		
		System.out.printf("The speed %d, The torque %d", speed, torque);
		
		String speedString = String.format("%8s", Integer.toBinaryString(speed)).replace(' ', '0');
		String torqueString = String.format("%8s", Integer.toBinaryString(torque)).replace(' ', '0');
		
		String sDel = "001010100111001100101010"; // *s* - start of package delimiter
		String eDel = "001010100110010100101010"; // *e* - end of package delimiter
		String vDel = "001010100111011000101010"; //*v* - speed delimiter
		String tDel = "001010100111010000101010"; // *t* - torque delimiter
		
		String result = sDel + vDel + speedString + tDel + torqueString + eDel;
		
		return result; }   

}
