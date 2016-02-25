import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class ArduinoControllerTest {
	
	ArduinoController AC;
	private static USBConnection mockedUSB;
	
	@BeforeClass
	public static void setUp(){
		mockedUSB = Mockito.mock(USBConnection.class);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/*
	 * Below are tests for the method threadSendJob()
	 */
	@Test
	public void threadStarted(){
		AC = new ArduinoController(mockedUSB);
		AC.threadSendJob();
		assertTrue(AC.isThreadSendJobRunning());
	}
	@Test
	public void dontSend() throws Exception {
		AC = new ArduinoController(mockedUSB);
		AC.threadSendJob();
	}
	@Test
	public void startThread(){
		AC = new ArduinoController(mockedUSB);
		assertFalse(AC.isThreadSendJobRunning()); // Thread is not running
		AC.threadSendJob();
		assertTrue(AC.isThreadSendJobRunning()); // Thread is running
	}
	@Test
	public void isLooping(){
		// JUnit doesn't support this
	}
	
	/*
	 * Below are tests for the method threadReceiveData()
	 */
	@Test
	public void threadStarted2(){
		AC = new ArduinoController(mockedUSB);
		AC.threadReceiveData();
		assertTrue(AC.isThreadReceiveDataRunning());
	}
//	@Test - WE ALREADY COVERED THIS IN DTBSTestCases and USBConnectionSendSensorDataTest
//	public void receivedValues(){
//		AC.threadReceiveData();
//		assertEquals(expected, actual); Can't set the value coming from ArduinoCommunication maybe mocking?
//	}
	@Test
	public void startThread2(){
		AC = new ArduinoController(mockedUSB);
		assertFalse(AC.isThreadReceiveDataRunning()); // Thread is not running
		AC.threadReceiveData();
		assertTrue(AC.isThreadReceiveDataRunning()); // Thread is running
	}
	@Test
	public void isLooping2(){
		// JUnit doesn't support this
	}
	
	/*
	 * Below are tests for helper classes
	 */
	@Test
	public void setSensorValues(){
		AC = new ArduinoController(mockedUSB);
		AC.setTUI("5", "6", "7");
		assertEquals(5, AC.getT());
		assertEquals(6, AC.getU());
		assertEquals(7, AC.getI());
		
	}
}
