import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;


public class ReadSpeedAndTorqueTest {
	
	ArduinoCommunication2 arduino = new ArduinoCommunication2();
	
	// Delimiter values
	private final String start_del = "00101010011100110011000000101010"; // *s0* 32
	private final String end_del   = "00101010011001010011000000101010"; // *e0*
	private final String tor_del   = "00101010011101000011000000101010"; // *t0*
	private final String spd_del   = "00101010011101100011000000101010"; // *v0*
	
	// Test values 
	private final int intSpeed1  = 12;
	private final int intSpeed2  = 15;
	private final int intTorque1  = 30;
	private final int intTorque2  = 17;
	
	String speed1 = String.format("%8s", Integer.toBinaryString(intSpeed1)).replace(' ', '0');
	String speed2 = String.format("%8s", Integer.toBinaryString(intSpeed2)).replace(' ', '0');
	String torque1 = String.format("%8s", Integer.toBinaryString(intTorque1)).replace(' ', '0');
	String torque2 = String.format("%8s", Integer.toBinaryString(intTorque2)).replace(' ', '0');
	
	
	@Test
	public void noStartDelimiter(){		
		
		arduino.outputBuffer = spd_del + speed1 + tor_del + torque1 + end_del;
		int[] expected= new int[2];
		expected[0] = -1;
		expected[1] = -1;
		int[] actual = arduino.readSpeedAndTorque();
		assertArrayEquals(expected, actual);
	}
	
	
	@Test
	public void packageCorrupted(){
		
		arduino.outputBuffer = start_del + spd_del + speed1 + torque1 + end_del;
		int[] expected = new int[2];
		expected[0] = -1;
		expected[1] = -1;
		int[] actual = arduino.readSpeedAndTorque();
		assertArrayEquals(expected, actual);
		
	}
	
	@Test
	public void validPackage(){
		
		arduino.outputBuffer = start_del + spd_del + speed1 + tor_del + torque1 + end_del;
		int[] expected = new int[2];
		expected[0] = intSpeed1;
		expected[1] = intTorque1;
		int[] actual = arduino.readSpeedAndTorque();
		assertArrayEquals(expected, actual);
		
	}
}

