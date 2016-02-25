import static org.mockito.Mockito.when;

import org.mockito.Mockito;

class USBConnection implements USB {
	    
	private int i = 5;
	
	@Override
	public void sendSensorData(String s) {
		System.out.printf("WE ARE ODROID AND GOT BUFFER %s\n", s);
	}

	@Override
	public String readSpeedTorque() {
		// TODO Auto-generated method stub
		if(i < 0 || i > 255){
			i = 0;
		}
		String buffer = build_SpeedTorqueBitstreamString(i, i);
		i++;
		
		return buffer;
		
	}
	
private String build_SpeedTorqueBitstreamString(int speed, int torque){
		
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