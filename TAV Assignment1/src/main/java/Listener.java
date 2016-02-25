import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener{
	 public void actionPerformed(ActionEvent e){
		 ArduinoController controller = new ArduinoController();
		 Object s = e.getSource();
		 if (s == SimpleUI.send) { //Send values button...
			 String t = SimpleUI.t.getText();
			 String u = SimpleUI.u.getText();
			 String i = SimpleUI.i.getText();
			 boolean validInput = SimpleUI.areInputsInts(t,u,i);
			 if (validInput){
				 controller.setTUI(t, u, i);
				 controller.printstuff();
				 SimpleUI.errorsDisplay.setText("");
				 SimpleUI.displaySentData(t, u, i);
				 System.out.println("Sending...\ntorque: "+t +"\nUS distance: "+u +"\nIR distance: "+i);
				 //TODO: call to sendSensorData
			 } else {
				 SimpleUI.displayError("ERROR: only integer values accepted!");
			 } 
		 } else if (s == SimpleUI.startStop) { //Start button....
			 System.out.println("Start");
			 //TODO: call to threads
			 controller.threadSendJob();
			 controller.threadReceiveData();
		 }
	 }
}
