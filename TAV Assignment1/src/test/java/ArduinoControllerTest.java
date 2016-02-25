import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArduinoControllerTest {
	
	ArduinoController AC = new ArduinoController();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/*
	 * Below are tests for the method threadSendJob()
	 */
	@Test
	public void threadStarted(){
		AC.threadSendJob();
		assertTrue(AC.isThreadSendJobRunning());
	}
	@Test
	public void dontSend(){
		thrown.expect(IllegalArgumentException.class);
	    thrown.expectMessage("Send sensor data FAILED!");
		AC.threadSendJob();
	}
	@Test
	public void startThread(){
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
		AC.threadReceiveData();
		assertTrue(AC.isThreadReceiveDataRunning());
	}
	@Test
	public void receivedValues(){
		//AC.threadReceiveData();
		//assertEquals(expected, actual); Can't set the value coming from ArduinoCommunication maybe mocking?
	}
	@Test
	public void startThread2(){
		assertFalse(AC.isThreadReceiveDataRunning()); // Thread is not running
		AC.threadReceiveData();
		assertTrue(AC.isThreadReceiveDataRunning()); // Thread is running
	}
	@Test
	public void isLooping2(){
		// JUnit doesn't support this
	}
}
