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
			       			//System.out.println("Send sensor data SUCCESS!\n");
			       		}else{
			       			throw new IllegalArgumentException("Send sensor data FAILED!");
			       		}
			        }
			},0,2000);
		}else{
			throw new IllegalArgumentException("threadSendJob is already running.");
			//System.out.println("threadSendJob is already running.");
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
			             speedTorque = AC.readSpeedTorque();
			             System.out.println("Receive Speed&Torque SUCCESS!\n");
			         }
			 },0,1000);
		}else{
			System.out.println("threadReceiveData is already running.");
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
   
   public int readSpeed(){
	   return this.speedTorque[0];
   }
   public int readTorque(){
	   return this.speedTorque[1];
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
   
}

