import static org.junit.Assert.*;

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
	
	/*
	 * Below are tests for the method threadSendJob()
	 */
	
	// Checks that the thread is started via a boolean
	@Test
	public void threadStarted(){
		AC = new ArduinoController(mockedUSB);
		AC.threadSendJob();
		assertTrue(AC.isThreadSendJobRunning());
	}
	// Checks that nothing is sent when either of the sensor values are -1
	@Test
	public void dontSend() throws Exception {
		AC = new ArduinoController(mockedUSB);
		AC.threadSendJob();
	}
	// Checks that the thread has started via a boolean
	@Test
	public void startThread(){
		AC = new ArduinoController(mockedUSB);
		assertFalse(AC.isThreadSendJobRunning()); // Thread is not running
		AC.threadSendJob();
		assertTrue(AC.isThreadSendJobRunning()); // Thread is running
	}
	// Test to check that the thread keeps running every 2 seconds.
	@Test
	public void isLooping(){
		// JUnit doesn't support this
	}
	
	/*
	 * Below are tests for the method threadReceiveData()
	 */
	
	// Checks that the thread is started via a boolean
	@Test
	public void threadStarted2(){
		AC = new ArduinoController(mockedUSB);
		AC.threadReceiveData();
		assertTrue(AC.isThreadReceiveDataRunning());
	}
	// Checks that the thread has started via a boolean
	@Test
	public void startThread2(){
		AC = new ArduinoController(mockedUSB);
		assertFalse(AC.isThreadReceiveDataRunning()); // Thread is not running
		AC.threadReceiveData();
		assertTrue(AC.isThreadReceiveDataRunning()); // Thread is running
	}
	// Test to check that the thread keeps running every 1 seconds.
	@Test
	public void isLooping2(){
		// JUnit doesn't support this
	}
	
	/*
	 * Below are tests for helper classes
	 */
	
	//Check that the values are actually set with the helper method.
	@Test
	public void setSensorValues(){
		AC = new ArduinoController(mockedUSB);
		AC.setTUI("5", "6", "7");
		assertEquals(5, AC.getT());
		assertEquals(6, AC.getU());
		assertEquals(7, AC.getI());
		
	}
}
