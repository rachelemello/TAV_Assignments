import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ReadFromOutputBufferTest {
	
	ArduinoCommunication arduino = new ArduinoCommunication();
	
	@Before
	public void setOutputBuffer(){
		arduino.setBuffer("randomstringfortestingstuff");
	}
	@Test
	public void negativeN(){
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(-1);
		assertArrayEquals(expected, actual);
	}
	@Test
	public void positiveNTooBig(){
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(50);
		assertArrayEquals(expected, actual);
	}
	@Test
	public void positiveNInRange(){
		String[] expected = {"0", "rando"};
		String[] actual = arduino.readFromOutputBuffer(5);
		assertArrayEquals(expected, actual);
	}
}
