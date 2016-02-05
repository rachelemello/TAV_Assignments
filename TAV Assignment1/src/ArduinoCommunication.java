
public class ArduinoCommunication {
	private String buffer;
	
	// Delimiter values for build_bitstream (with torque, udistance, infradistance)
	private final String start_del = "001010100111001100101010"; // *s*
	private final String tval_del = "001010100111010000101010"; // *t*
	private final String uval_del = "001010100111010100101010"; // *u*
	private final String ival_del = "001010100110100100101010"; // *i*
	private final String end_del = "001010100110010100101010"; // *e*
		
	
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
		
		String bitstream = start_del + tval_del + ts + uval_del + us + ival_del + is + end_del;
		
		return bitstream;
	}	

}
