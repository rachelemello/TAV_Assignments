
public class ArduinoCommunication2 {
	public String inputBuffer= "";
	public String outputBuffer = "";
	
	// Method 3 in the assignment
	public int writeToInputBuffer(int n, String s) {
		int error = 5;
		if (n < 0) {
			return error;
		} else {
			s = s.substring(0, n);
			inputBuffer = inputBuffer + s;
			return 0;
		}
	}
	
	// Method 4 in the assignment
	// I return the error codes as strings because I need to return the read message as well
	public String[] readFromOutputBuffer(int n) {
		String error = "8";									// Error code for this method (negativeN & positiveNTooBig)
		String[] result = new String[2];					// Storing what will be returned from this method (negativeN & positiveNTooBig & positiveNInRange)
		if (n < 0 || n > outputBuffer.length()) {			// Check if N is outside accepted range (negativeN & positiveNTooBig)
			result[0] = error;								// Add error code (negativeN & positiveNTooBig)
			result[1] = "";									// Empty string as "read" (negativeN & positiveNTooBig)
		} else {											// N is in range (positiveNInRange)
			result[0] = "0";								// Add error code (positiveInRange)
			result[1] = outputBuffer.substring(0,n);		// Read N bits from buffer and add to result (positiveInRange)
		}
		return result;										// (negativeN & positiveNTooBig & positiveNInRange)
	}
	
	
	// Method 1 in the assignment
	public String sendSensorData(float torque, float ultraD, float irD) {
		String bitstream = "";
		
		// Transforming the floats to 8 bits binaries
		String binTorque = floatTo8bit(torque);
		String binUltraD = floatTo8bit(ultraD);
		String binIrD = floatTo8bit(irD);
		
		// The delimiters we chose
		String startDel = "*s*";
		String endDel = "*e*";
		String torqueDel = "*t*";
		String ultraDel = "*u*";
		String irDel = "*i*";
		
		// Concatenating everything
		bitstream = startDel + torqueDel + binTorque + ultraDel + binUltraD + irDel + binIrD + endDel;
		
		return bitstream;
	}
	
	
	// Extra helper method:
	// Rounds the float to the nearest integer, then convert the integer into binary and
	// then add trailing zeroes if needed (until we reach 8 bits).
	// Only works for numbers <256
	public String floatTo8bit(float aFloat) {
		int aInt = Math.round(aFloat);
		String binary = Integer.toBinaryString(aInt);
		while (binary.length() < 8) {
			binary = "0" + binary;
		}
		return binary;
	}
	
	
// Method 2 in the assignment
	// In case of errors found I return [-1, -1]
	public int[] readSpeedAndTorque() {
		int[] result = new int[2];
		int[] error = new int[2];
		error[0] = -1;
		error[1] = -1;
		//int startIndx = outputBuffer.indexOf("*s0*");
		int startIndx = outputBuffer.indexOf("00101010011100110011000000101010");
		// If the starting delimiter isn't there, error
		if (startIndx == -1) {
			return error;
			
		} else {
			outputBuffer = outputBuffer.substring(startIndx); //cut away what is before the package start
			//int endIndx = outputBuffer.indexOf("*e0*");
			int endIndx = outputBuffer.indexOf("00101010011001010011000000101010");
			
			// Use the read method written before
			String[] bitsRead = readFromOutputBuffer(endIndx-startIndx);
			String message = bitsRead[1];
			
			// Checking the message for errors with the helper method
			if (readErrorDetection(message)) {
				return error;
				
			} else {
				// Parsing the string
				String binSpeed = outputBuffer.substring(64, 72);
				String binToque = outputBuffer.substring(104, 112);
				int speed = Integer.parseInt(binSpeed, 2);
				int torque = Integer.parseInt(binToque, 2);
				result[0] = speed;
				result[1] = torque;
				return result;
			}
		}
	}
	
	
	// Extra helper method:
	// Checks for errors in the read bitstream:
	//  * length has to match the expected one
	//  * delimiters should be there
	// Returns true if an error is detected
	public boolean readErrorDetection(String s) {
		int expectedLen = 112;
		//String torqueDel = "*t0*";
		//String speedDel = "*v0*";
		String torqueDel = "00101010011101000011000000101010";
		String speedDel = "00101010011101100011000000101010";
		if (s.length() != expectedLen) {
			System.out.println("Lenght is: " + s.length()+ " Should be: " + expectedLen);
			
			return true;
		} else if (s.indexOf(torqueDel) == -1 || s.indexOf(speedDel) == -1) {
			System.out.println("torqueDel or speedDel is -1");
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
