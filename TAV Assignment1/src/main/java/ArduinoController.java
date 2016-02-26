import java.util.*;

public class ArduinoController{
	private static int t; 
	private static int u; 
	private static int i;
	private static int[] speedTorque;
	private static boolean twoSecStarted;
	private static boolean oneSecStarted;
	ArduinoCommunication AC;
	
	public ArduinoController(USB usb){
		AC = new ArduinoCommunication(usb);
		t=-1; // KEEP INITIAL VALUES to -1 SO WE DONT SEND
		i=-1;
		u=-1;
		twoSecStarted = false; // Thread is not started
		oneSecStarted = false;
	}

	// Starts a timed thread that runs every 2 seconds and sends sensor data 
	// Uses a method from ArduinoCommunication
	public void threadSendJob(){
		if(!twoSecStarted){
			setIsRunningSendJob();
			Timer twoSec = new Timer();
			twoSec.scheduleAtFixedRate(new TimerTask() {
			       @Override
			        public void run(){
			       		if(getT() != -1 || getU() != -1 || getI() != -1){
			       			AC.sendSensorData(getT(), getU(), getI());
			       		}
			        }
			},0,2000);
		}
	}
	
	// Starts a timed thread that runs every 1 seconds and receives speed and torque data 
	// Uses a method from ArduinoCommunication
	public void threadReceiveData(){
		if(!oneSecStarted){
			setIsRunningReceiveData();
			Timer oneSec = new Timer();
			 oneSec.scheduleAtFixedRate(new TimerTask() {
			        @Override
			         public void run(){
			        	displayReceivedDataByReadingSpeedTorque();
			         }
			 },0,1000);
		}
		
			
	}

   public void setTUI(String t, String u, String i) {
	   this.t = Integer.parseInt(t);
	   this.u = Integer.parseInt(u);
	   this.i = Integer.parseInt(i);
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
   public void setIsRunningSendJob(){
	   this.twoSecStarted = true;
   }public void setIsRunningReceiveData(){
	   this.oneSecStarted = true;;
   }
   public boolean isThreadSendJobRunning(){
	   return twoSecStarted;
   }
   public boolean isThreadReceiveDataRunning(){
	   return oneSecStarted;
   }
   
   public void displayReceivedDataByReadingSpeedTorque(){
	   SimpleUI.displayReceivedData(AC.readSpeedTorque());
   }
   
}

