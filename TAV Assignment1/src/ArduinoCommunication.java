
public class ArduinoCommunication {
	private String buffer;
	
	// Delimiter values for bitstream packs
	private final String sDel = "001010100111001100101010"; // *s*
	private final String eDel = "001010100110010100101010"; // *e* 
	private final String vDel = "001010100111011000101010"; // *v*
	private final String tDel = "001010100111010000101010"; // *t*
	private final String uDel = "001010100111010100101010"; // *u*
	private final String iDel = "001010100110100100101010"; // *i*
	
	//Constructor: sets the buffer to empty
	public ArduinoCommunication(){
		this.buffer = "";
	}
	
	public String getBuffer(){
		return this.buffer;
	}
	
	public void setBuffer(String buffer) {
		this.buffer = buffer;
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
			buffer = buffer + s; //a3
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
			return false; //Action a2
		}
		return true;
	}
	
	
	// Method 2 in the assignment
	public int[] readSpeedTorque () {
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		
		int start = buffer.indexOf(sDel);
		int end = buffer.indexOf(eDel);
		if (start == -1) { //Condition c1
			return error; //Action a3
		} else if (end == -1) { //Condition c2
			return error; //Action a3
		} else {
			String stream = buffer.substring(start, end+24); //Action a1
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
		if (n < 0 || n > buffer.length()) {		// Check if N is outside accepted range (negativeN & positiveNTooBig)
			result[0] = error;					// Add error code (negativeN & positiveNTooBig)
			result[1] = "";						// Empty string as "read" (negativeN & positiveNTooBig)
		} else {								// N is in range (positiveNInRange)
			result[0] = "0";					// Add error code (positiveInRange)
			result[1] = buffer.substring(0,n);	// Read N bits from buffer and add to result (positiveInRange)
		}
		return result;							// (negativeN & positiveNTooBig & positiveNInRange)
	}
	
	public String build_bitstream(int t, int u, int i){
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
		
		return bitstream;
	}

}
