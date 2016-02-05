import static org.junit.Assert.*;

import org.junit.Test;

public class DTBSTestCases {

	// Delimiter values
	private final String start_del = "001010100111001100101010"; // *s*
	private final String tval_del = "001010100111010000101010"; // *t*
	private final String uval_del = "001010100111010100101010"; // *u*
	private final String ival_del = "001010100110100100101010"; // *i*
	private final String end_del = "001010100110010100101010"; // *e*
	
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
		
		String ts = String.format("%8s", Integer.toBinaryString(t)).replace(' ', '0');
		String us = String.format("%8s", Integer.toBinaryString(u)).replace(' ', '0');
		String is = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
		
		ArduinoCommunication junit = new ArduinoCommunication();
		
		String result = junit.sendSensorData(t, u, i);
		
		String expected = start_del + tval_del + ts + uval_del + us + ival_del + is + end_del; 
		
		assertEquals(expected, result);
	}
	
	@Test
	public void dtbs8() {
		
		int t = 255;
		int u = 255;
		int i = 255;
		
		String ts = String.format("%8s", Integer.toBinaryString(t)).replace(' ', '0');
		String us = String.format("%8s", Integer.toBinaryString(u)).replace(' ', '0');
		String is = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
		
		ArduinoCommunication junit = new ArduinoCommunication();
		
		String result = junit.sendSensorData(t, u, i);
		
		String expected = start_del + tval_del + ts + uval_del + us + ival_del + is + end_del; 
		
		assertEquals(expected, result);
	}

	@Test
	public void dtbs9() {
		
		int t = 50;
		int u = 100;
		int i = 150;
		
		String ts = String.format("%8s", Integer.toBinaryString(t)).replace(' ', '0');
		String us = String.format("%8s", Integer.toBinaryString(u)).replace(' ', '0');
		String is = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
		
		ArduinoCommunication junit = new ArduinoCommunication();
		
		String result = junit.sendSensorData(t, u, i);
		
		String expected = start_del + tval_del + ts + uval_del + us + ival_del + is + end_del; 
		System.out.println(expected);
		assertEquals(expected, result);
	}
	
}
