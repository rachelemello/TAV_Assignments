import java.util.*;

public class ArduinoController{
	private static int t; // KEEP INITIAL VALUES to -1 SO WE DONT SEND
	private static int u; 
	private static int i;
	private static int[] speedTorque;
	ArduinoCommunication AC;
	
	public ArduinoController(){
		AC = new ArduinoCommunication();
		t=-1;
		i=-1;
		u=-1;
	}

	public static void main(String s[]){
		System.out.print("Class started");
		 
	}
	public void threadSendJob(){
		 Timer twoSec = new Timer();
		 twoSec.scheduleAtFixedRate(new TimerTask() {
		        @Override
		         public void run(){
		        		if(getT() != -1 || getU() != -1 || getI() != -1){
		        			AC.sendSensorData(getT(), getU(), getI());
		        			System.out.println("Send sensor data SUCCESS!\n");
		        		}else{
		        			System.out.println("Send sensor data FAILED!\n");	
		        		}
		         }
		 },0,2000);
	}
	
	public void threadReceiveData(){
		Timer oneSec = new Timer();
		 oneSec.scheduleAtFixedRate(new TimerTask() {
		        @Override
		         public void run(){
		             speedTorque = AC.readSpeedTorque();
		             System.out.println("Receive Speed&Torque SUCCESS!\n");
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
   public void setTUI(String t, String u, String i) {
	   this.t = Integer.parseInt(t);
	   this.u = Integer.parseInt(u);
	   this.i = Integer.parseInt(i);
   }
   public void printstuff(){
	   System.out.println("t = " + t + "\nu = " + u + "\ni = " + i);
   }
   
   public int getT(){
	   return this.t;
   }
   public int getU(){
	   return this.u;
   }
   public int getI(){
	   return this.i;
   }
   
   public int readSpeed(){
	   return this.speedTorque[0];
   }
   public int readTorque(){
	   return this.speedTorque[1];
   }
   
}

