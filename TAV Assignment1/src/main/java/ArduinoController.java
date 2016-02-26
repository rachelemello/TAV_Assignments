import java.util.*;

public class ArduinoController {
	private static int t; 
	private static int u; 
	private static int i;
	private static boolean twoSecStarted = false;
	private static boolean oneSecStarted = false;
	ArduinoCommunication AC;
	
	public ArduinoController(USB usb){
		AC = new ArduinoCommunication(usb);
		t=-1; // KEEP INITIAL VALUES to -1 SO WE DONT SEND
		i=-1;
		u=-1;
	}

	// Starts a timed thread that runs every 2 seconds and sends sensor data 
	// Uses a method from ArduinoCommunication
	public void threadSendJob(){
		
		System.out.printf("We called threadSendJob and the arduinocontroller %b\n", ArduinoController.twoSecStarted);
		
		if(!ArduinoController.twoSecStarted){
			setIsRunningSendJob();
			Timer twoSec = new Timer();
			twoSec.scheduleAtFixedRate(new TimerTask() {
			       @Override
			        public void run(){
			       		if(getT() != -1 || getU() != -1 || getI() != -1){
			       			String result = AC.sendSensorData(getT(), getU(), getI());
			       			if(result.equals("success")){
			       				System.out.println("Sensor data valid, sending it to AC sendsensordata");
			       			} else {
			       				SimpleUI.displayError(String.format("Bad input for sensor data: %s", result));
			       			}
			       		} else {
			       			System.out.println("The values are -1");
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
	   ArduinoController.t = Integer.parseInt(t);
	   ArduinoController.u = Integer.parseInt(u);
	   ArduinoController.i = Integer.parseInt(i);
   }
   public int getT(){
	   return ArduinoController.t;
   }
   public int getU(){
	   return ArduinoController.u;
   }
   public int getI(){
	   return ArduinoController.i;
   }
   public static void setIsRunningSendJob(){
	   System.out.println("We are setting twosecstarted to true");
	   ArduinoController.twoSecStarted = true;
	   System.out.printf("tWO SEC IS %B RIGHT AFTER SETTING IT \n", isThreadSendJobRunning());
   }
   public static void setIsRunningReceiveData(){
	   
	   ArduinoController.oneSecStarted = true;;
   }
   public static boolean isThreadSendJobRunning(){
	   return ArduinoController.twoSecStarted;
   }
   public static boolean isThreadReceiveDataRunning(){
	   return ArduinoController.oneSecStarted;
   }
   
   public void displayReceivedDataByReadingSpeedTorque(){
	   SimpleUI.displayReceivedData(AC.readSpeedTorque());
   }
}