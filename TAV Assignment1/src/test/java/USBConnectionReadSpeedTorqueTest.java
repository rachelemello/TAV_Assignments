import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class USBConnectionReadSpeedTorqueTest {
	
	//Delimiter values in binary used for testing corrupt packages
		String sDel = "001010100111001100101010"; // *s* - start of package delimiter
		String eDel = "001010100110010100101010"; // *e* - end of package delimiter
		String vDel = "001010100111011000101010"; //*v* - speed delimiter
		String tDel = "001010100111010000101010"; // *t* - torque delimiter
	//Strings of bits for testing
		String number1 = "10101010"; // 170
		String number2 = "00000000"; // 0
		String number3 = "11111111"; // 255
	
	private static USBConnection mockedUSB;
	
	private ArduinoController aController;
	
	@BeforeClass
	public static void setUp(){
		mockedUSB = Mockito.mock(USBConnection.class);
	}
	
	@Test
	public void testReadMinimallValues() throws Exception{
		
		int[] values = {0,0};
		int[] results = new int[2];
		
		String initialZeroValues = build_SpeedTorqueBitstreamString(values[0],values[1]);
		
		when(mockedUSB.readSpeedTorque()).thenReturn(initialZeroValues);
		
		aController = new ArduinoController(mockedUSB);
		
		results = aController.AC.readSpeedTorque();
		
		assertArrayEquals(new int[]{0,0}, results);
	}
	
	@Test
	public void testReadMaximalValues() throws Exception{
		
		int[] values = {255,255};
		int[] results = new int[2];
		
		String initialZeroValues = build_SpeedTorqueBitstreamString(values[0],values[1]);
		
		when(mockedUSB.readSpeedTorque()).thenReturn(initialZeroValues);
		
		aController = new ArduinoController(mockedUSB);
		
		results = aController.AC.readSpeedTorque();
		
		assertArrayEquals(new int[]{255,255}, results);
	}
	
	@Test
	public void testReadMediumValues() throws Exception{
		
		int[] values = {120,80};
		int[] results = new int[2];
		
		String initialZeroValues = build_SpeedTorqueBitstreamString(values[0],values[1]);
		
		when(mockedUSB.readSpeedTorque()).thenReturn(initialZeroValues);
		
		aController = new ArduinoController(mockedUSB);
		
		results = aController.AC.readSpeedTorque();
		
		assertArrayEquals(new int[]{120,80}, results);
	}
	
	@Test
	public void testCorruptPackage() throws Exception{
		
		int[] values = {120,80};
		int[] results = new int[2];
		
		//Package without an end delimiter
		String corrupt = sDel+vDel+number1+tDel+number2;
		when(mockedUSB.readSpeedTorque()).thenReturn(corrupt);
		
		aController = new ArduinoController(mockedUSB);
		
		results = aController.AC.readSpeedTorque();
		
		assertArrayEquals(new int[]{-1,-1}, results);
		
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