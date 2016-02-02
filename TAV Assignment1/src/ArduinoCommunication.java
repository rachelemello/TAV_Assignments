
public class ArduinoCommunication {
	private String buffer;
	
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
	
	

}
