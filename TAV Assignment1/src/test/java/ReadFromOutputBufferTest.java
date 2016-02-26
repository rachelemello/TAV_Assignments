import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ReadFromOutputBufferTest {
	
	ArduinoCommunication arduino = new ArduinoCommunication();
	
	// Sets the buffer to something just for testing
	@Before
	public void setOutputBuffer(){
		arduino.setInBuffer("randomstringfortestingstuff");
	}
	
	// Test a negative value, checking that the return value from the method is correct
	@Test
	public void negativeN(){
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(-1);
		assertArrayEquals(expected, actual);
	}
	
	// Test a value that is larger than the buffer, checking that the return value from the method is correct
	@Test
	public void positiveNTooBig(){
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(50);
		assertArrayEquals(expected, actual);
	}
	
	// Test a value that is in range, checking that the return value from the method is correct
	@Test
	public void positiveNInRange(){
		String[] expected = {"0", "rando"};
		String[] actual = arduino.readFromOutputBuffer(5);
		assertArrayEquals(expected, actual);
	}
	
	// Test that N bits is removed from the beginning of the buffer, check the buffer
	@Test
	public void removeNBits(){
		arduino.readFromOutputBuffer(5); //Just to have the method run once
		String expected = "mstringfortestingstuff";
		String actual = arduino.getInBuffer();
		assertEquals(expected, actual);
	}
}
