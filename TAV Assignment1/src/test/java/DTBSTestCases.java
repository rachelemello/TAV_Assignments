import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class DTBSTestCases {

	// Used in DTBS 7 8 9 because they actually send the data (as validation succeeds) to the USB object.
	private static USBConnection mockedUSB;
	
	@BeforeClass
	public static void setUp(){
		mockedUSB = Mockito.mock(USBConnection.class);
	}
	
	@Test
	public void dtbs1() {
		ArduinoCommunication junit = new ArduinoCommunication();
		String result = junit.sendSensorData(-1, 0, 0);
		assertEquals("Error: Bad T", result);
	}
	
	@Test
	public void dtbs2() {
		ArduinoCommunication junit = new ArduinoCommunication();
		String result = junit.sendSensorData(0, -1, 0);
		assertEquals("Error: Bad U", result);
	}
	
	@Test
	public void dtbs3() {
		ArduinoCommunication junit = new ArduinoCommunication();
		String result = junit.sendSensorData(0, 0, -1);
		assertEquals("Error: Bad I", result);
	}
	
	@Test
	public void dtbs4() {
		ArduinoCommunication junit = new ArduinoCommunication();
		String result = junit.sendSensorData(256, 0, 0);
		assertEquals("Error: Bad T", result);
	}
	
	@Test
	public void dtbs5() {
		ArduinoCommunication junit = new ArduinoCommunication();
		String result = junit.sendSensorData(0, 256, 0);
		assertEquals("Error: Bad U", result);
	}
	
	@Test
	public void dtbs6() {
		ArduinoCommunication junit = new ArduinoCommunication();
		String result = junit.sendSensorData(0, 0, 256);
		assertEquals("Error: Bad I", result);
	}
	
	@Test
	public void dtbs7() {
		
		int t = 1;
		int u = 1;
		int i = 1;
		
		ArduinoCommunication junit = new ArduinoCommunication(mockedUSB);
		
		String result = junit.sendSensorData(t, u, i);
		
		String expected = "success"; 
		
		assertEquals(expected, result);
	}
	
	@Test
	public void dtbs8() {
		
		int t = 255;
		int u = 255;
		int i = 255;
		
		ArduinoCommunication junit = new ArduinoCommunication(mockedUSB);
		
		String result = junit.sendSensorData(t, u, i);
		
		String expected = "success"; 
		
		assertEquals(expected, result);
	}

	@Test
	public void dtbs9() {
		
		int t = 50;
		int u = 100;
		int i = 150;
		
		ArduinoCommunication junit = new ArduinoCommunication(mockedUSB);
		
		String result = junit.sendSensorData(t, u, i);
		
		String expected = "success"; 

		assertEquals(expected, result);
	}
	
}
