import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener{
	 public void actionPerformed(ActionEvent e){
		 Object s = e.getSource();
		 if (s == SimpleUI.send) {
			 System.out.println("sending");
		 }
	 }
}
