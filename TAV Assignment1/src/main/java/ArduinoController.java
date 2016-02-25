import java.util.*;

public class ArduinoController{
	private int t = -1; // KEEP INITIAL VALUES to -1 SO WE DONT SEND
	private int u = -1; 
	private int i = -1;
	private int[] speedTorque;
	ArduinoCommunication AC = new ArduinoCommunication();

	public void main(String s[]){
		
		 Timer twoSec = new Timer();
		 twoSec.scheduleAtFixedRate(new TimerTask() {
		        @Override
		         public void run(){
		        		if(t != -1 || u != -1 || i != -1){
		        			AC.sendSensorData(t, u, i);
		        		}else{
		        			System.out.println("Send sensor data FAILED!\n");
		        		}
		         }
		 },0,2000);

		 Timer oneSec = new Timer();
		 oneSec.scheduleAtFixedRate(new TimerTask() {
		        @Override
		         public void run(){
		             speedTorque = AC.readSpeedTorque();
		         }
		 },0,1000);
	}
	
   public void setU(int u){
	   this.u = u;
   }
   
   public void setI(int i){
	   this.i = i;
   }
   
   public void setT(int t){
	   this.t = t;
   }
   public int readSpeed(){
	   return this.speedTorque[0];
   }
   public int readTorque(){
	   return this.speedTorque[1];
   }
   
}

