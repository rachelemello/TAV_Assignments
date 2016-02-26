public class ArduinoCommunication {
	private String inBuffer;
	private USB usb;
	
	// Delimiter values for bitstream packs
	private final String sDel = "001010100111001100101010"; // *s*
	private final String eDel = "001010100110010100101010"; // *e* 
	private final String vDel = "001010100111011000101010"; // *v*
	private final String tDel = "001010100111010000101010"; // *t*
	private final String uDel = "001010100111010100101010"; // *u*
	private final String iDel = "001010100110100100101010"; // *i*
	
	//Constructor: sets the inBuffer to empty
	public ArduinoCommunication(USB usb){
		this.inBuffer = "";
		this.usb = usb;
	}
	
	public ArduinoCommunication(){
		this.inBuffer = "";
	}
	
	public String getInBuffer(){
		return this.inBuffer;
	}
	
	public void setInBuffer(String buffer) {
		this.inBuffer = buffer;
	}
	
	// Method 3 in the assignment
	public int writeToInputBuffer(int n, String s) {
		int error = 5;
		if (n < 0) { //Checking c1
			return error; //a2
		} else if (n > s.length()) { //Checking c2
			return error; //a2
		} else if (n > 100) { //Checking c3
			return error; //a2
		} else {
			s = s.substring(0, n);
			inBuffer = inBuffer + s; //a3
			return 0; //a1
		}
	}
	
	// Helper method for the readSpeedTorque
	public boolean isPackageOk(String s) {
		int expectedLength = 112;
		if (s.indexOf(vDel) == -1) { //Condition c3.1
		return false; //Action a2
		} else if (s.indexOf(tDel) == -1) { //Condition c3.2
			return false; //Action a2
		} else if (s.length() != expectedLength) { //Condition c3.3
			return false;
		} else {
		return true;
		}
	}
	
	
	// Method 2 in the assignment
	public int[] readSpeedTorque () {
		
		//get data from odroid mock and save the data to the inBuffer
		

		String buffer = usb.readSpeedTorque();
		System.out.printf("String is %s\n",buffer);
		
		setInBuffer(buffer);
		
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		
		int start = inBuffer.indexOf(sDel);
		int end = inBuffer.indexOf(eDel);
		if (start == -1) { //Condition c1
			return error; //Action a3
		} else if (end == -1) { //Condition c2
			return error; //Action a3
		} else {
			String stream = inBuffer.substring(start, end+24); //Action a1
			if (!isPackageOk(stream)) { //Condition c3
				return error; //Action a3
			} else {
				int[] result = new int[2];
				result[0] = Integer.parseInt(stream.substring(48, 56), 2);
				result[1] = Integer.parseInt(stream.substring(80, 88), 2);
				return result; //Action a2
			}
		}
	}
	
	// Method 4 in the assignment
	// I return the error codes as strings because I need to return the read message as well
	public String[] readFromOutputBuffer(int n) {
		String error = "8";						// Error code for this method (negativeN & positiveNTooBig)
		String[] result = new String[2];		// Storing what will be returned from this method (negativeN & positiveNTooBig & positiveNInRange)
		if (n < 0 || n > inBuffer.length()) {		// Check if N is outside accepted range (negativeN & positiveNTooBig)
			result[0] = error;					// Add error code (negativeN & positiveNTooBig)
			result[1] = "";						// Empty string as "read" (negativeN & positiveNTooBig)
		} else {								// N is in range (positiveNInRange)
			result[0] = "0";					// Add error code (positiveInRange)
			result[1] = inBuffer.substring(0,n);	// Read N bits from inBuffer and add to result (positiveInRange)
			setInBuffer(inBuffer.substring(n));		// Sets the inBuffer to be what is left after after reading (removeNBits)
		}
		return result;							// (negativeN & positiveNTooBig & positiveNInRange)
	}
	
	public String sendSensorData(int t, int u, int i){
		if(t<0 || t>255){
			return "Error: Bad T";
		}
		if(u<0 || u>255){
			return "Error: Bad U";
		}
		if(i<0 || i>255){
			return "Error: Bad I";
		}
		
		String ts = String.format("%8s", Integer.toBinaryString(t)).replace(' ', '0');
		String us = String.format("%8s", Integer.toBinaryString(u)).replace(' ', '0');
		String is = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
		
		String bitstream = sDel + tDel + ts + uDel + us + iDel + is + eDel;
		
		usb.sendSensorData(bitstream);
		return "success";
		
	}

}
