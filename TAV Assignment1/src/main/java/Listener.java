import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener{
	 public void actionPerformed(ActionEvent e){
		 Object s = e.getSource();
		 if (s == SimpleUI.send) { //Send values button...
			 String t = SimpleUI.t.getText();
			 String u = SimpleUI.u.getText();
			 String i = SimpleUI.i.getText();
			 boolean validInput = SimpleUI.areInputsInts(t,u,i);
			 if (validInput){
				 SimpleUI.errorsDisplay.setText("");
				 SimpleUI.sentDisplayText += ("\ntorque: "+t +"\nUS distance: "+u +"\nIR distance: "+i+"\n");
				 SimpleUI.sentDisplay.setText(SimpleUI.sentDisplayText);
				 System.out.println("Sending...\ntorque: "+t +"\nUS distance: "+u +"\nIR distance: "+i);
				 //TODO: call to sendSensorData
			 } else {
				 SimpleUI.errorsDisplay.setText("ERROR: only integer values accepted!");
			 } 
		 } else if (s == SimpleUI.startStop) { //Start button....
			 System.out.println("Start");
			 //TODO: call to threads
		 }
	 }
}
