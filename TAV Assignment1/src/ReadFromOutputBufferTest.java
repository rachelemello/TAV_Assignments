import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ReadFromOutputBufferTest {
	
	ArduinoCommunication2 arduino = new ArduinoCommunication2();
	
	@Before
	public void setBuffer(){
		arduino.outputBuffer = "randomstringfortestingstuff";
	}
	@Test
	public void negativeN(){
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(-1);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveNTooBig(){
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(50);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveNInRange(){
		String[] expected = {"0", "rando"};
		String[] actual = arduino.readFromOutputBuffer(5);
		assertEquals(expected, actual);
	}
}
