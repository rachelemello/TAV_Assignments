import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ReadFromOutputBufferTest {

	@Test
	public void negativeN(){
		ArduinoCommunication2 arduino = new ArduinoCommunication2();
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(-1);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveNTooBig(){
		ArduinoCommunication2 arduino = new ArduinoCommunication2();
		String[] expected = {"8", ""};
		String[] actual = arduino.readFromOutputBuffer(50);
		assertEquals(expected, actual);
	}
	@Test
	public void positiveNInRange(){
		ArduinoCommunication2 arduino = new ArduinoCommunication2();
		arduino.outputBuffer = "randomstringfortestingstuff";
		String[] expected = {"0", "rando"};
		String[] actual = arduino.readFromOutputBuffer(5);
		assertEquals(expected, actual);
	}
}
