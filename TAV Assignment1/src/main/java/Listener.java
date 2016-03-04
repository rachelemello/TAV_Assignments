import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener{
	 public void actionPerformed(ActionEvent e){
		 USBConnection usb = new USBConnection();
		 ArduinoController controller = new ArduinoController(usb);
		 Object s = e.getSource();
		 if (s == SimpleUI.send) { //Send values button...
			 if (!SimpleUI.started) {
				 SimpleUI.displayError("ERROR: You need to start the threads first!");
			 } else {
				 String t = SimpleUI.t.getText();
				 String u = SimpleUI.u.getText();
				 String i = SimpleUI.i.getText();
				 boolean validInput = SimpleUI.areInputsInts(t,u,i);
				 if (validInput){
					 controller.setTUI(t, u, i);
					 SimpleUI.errorsDisplay.setText("");
					 SimpleUI.displaySentData(t, u, i);
					 System.out.println("Sending...\ntorque: "+t +"\nUS distance: "+u +"\nIR distance: "+i);
				 } else {
					 SimpleUI.displayError("ERROR: only integer values accepted!");
				 } 
			 }
		 } else if (s == SimpleUI.startStop) { //Start button....
			 controller.threadSendJob();
			 controller.threadReceiveData();
		 }
	 }
}
