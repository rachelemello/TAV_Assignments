import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class GUItest {

private static USBConnection mockedUSB;
	
	private ArduinoController aController;
	
	@BeforeClass
	public static void setUp(){
		mockedUSB = Mockito.mock(USBConnection.class);
	}
	
	@Test
	public void sendSensorDataFromGUIAreBeingSent() throws InterruptedException{
		SimpleUI.runUI();
		
		int expectedT = 5;
		int expectedI = 6;
		int expectedU = 7;
		
		SimpleUI.t.setText(Integer.toString(expectedT));
		SimpleUI.u.setText(Integer.toString(expectedU));
		SimpleUI.i.setText(Integer.toString(expectedI));
		
		SimpleUI.startStop.doClick();
		
		Thread.sleep(1000);
		
		SimpleUI.send.doClick();
		
		Thread.sleep(1000);
		
		ArduinoController aController = new ArduinoController(mockedUSB);
		
		int actualT = aController.getT();
		int actualU = aController.getU();
		int actualI = aController.getI();
		
		
		assertEquals(expectedT,actualT);
		assertEquals(expectedU,actualU);
		assertEquals(expectedI,actualI);
	}
	
	
	@Test
	public void startThreadSendJobAndThreadReceiveJobFromGUIHaveStarted(){
		SimpleUI.runUI();
		
		SimpleUI.t.setText("5");
		SimpleUI.u.setText("5");
		SimpleUI.i.setText("5");
		
		SimpleUI.startStop.doClick();
		
		ArduinoController aController = new ArduinoController(mockedUSB);
		boolean actualThreadReceiveDatadRunning = ArduinoController.isThreadReceiveDataRunning();
		boolean actualThreadSendJobRunning = ArduinoController.isThreadSendJobRunning();
		
		assertEquals(true, actualThreadReceiveDatadRunning);
		assertEquals(true, actualThreadSendJobRunning);
	}
	
	@Test
	public void displayReceivedDataByReadingSpeedTorqueShouldDisplay(){
		SimpleUI.runUI();
		
		aController = new ArduinoController(mockedUSB);
		
		int[] values = {0,0};
		
		String initialZeroValues = build_SpeedTorqueBitstreamString(values[0],values[1]);
		
		when(mockedUSB.readSpeedTorque()).thenReturn(initialZeroValues);
		
		aController.displayReceivedDataByReadingSpeedTorque();
		
		String actual = SimpleUI.receivedDisplay.getText();
		
		String expected = "\nspeed: " + values[0] + "\ntorque: " + values[1] + "\n";
		
		assertEquals(expected, actual);
	}
	
	private static String build_SpeedTorqueBitstreamString(int speed, int torque){
		
		System.out.printf("The speed %d, The torque %d", speed, torque);
		
		String speedString = String.format("%8s", Integer.toBinaryString(speed)).replace(' ', '0');
		String torqueString = String.format("%8s", Integer.toBinaryString(torque)).replace(' ', '0');
		
		String sDel = "001010100111001100101010"; // *s* - start of package delimiter
		String eDel = "001010100110010100101010"; // *e* - end of package delimiter
		String vDel = "001010100111011000101010"; //*v* - speed delimiter
		String tDel = "001010100111010000101010"; // *t* - torque delimiter
		
		String result = sDel + vDel + speedString + tDel + torqueString + eDel;
		
		return result; }
	
}
