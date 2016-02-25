import java.util.*;

public class ArduinoController{
	private static int t; // KEEP INITIAL VALUES to -1 SO WE DONT SEND
	private static int u; 
	private static int i;
	private static int[] speedTorque;
	private static boolean twoSecStarted;
	private static boolean oneSecStarted;
	ArduinoCommunication AC;
	
	public ArduinoController(){
		AC = new ArduinoCommunication();
		t=-1;
		i=-1;
		u=-1;
		twoSecStarted = false;
		oneSecStarted = false;
	}

	public static void main(String s[]){
		 
	}
	public void threadSendJob(){
		if(!twoSecStarted){
			setIsRunningSendJob();
			Timer twoSec = new Timer();
			twoSec.scheduleAtFixedRate(new TimerTask() {
			       @Override
			        public void run(){
			       		if(getT() != -1 || getU() != -1 || getI() != -1){
			       			AC.sendSensorData(getT(), getU(), getI());
			       			System.out.println("Send sensor data SUCCESS!\n");
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

