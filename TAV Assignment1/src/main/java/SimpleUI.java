import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.*;

public class SimpleUI {

	public static void main(String s[]) {
		JTextArea errors = new JTextArea("Errors display", 5, 50);
		JScrollPane scrollPaneError = new JScrollPane(errors);
		errors.setEditable(false);
		
        JTextField sendtorque = new JTextField("input torque");
        JTextField sendir = new JTextField("input ir");
        JTextField sendur = new JTextField("input ur");
        
        JTextArea displaySent = new JTextArea("display sent values", 20, 25);
        JScrollPane scrollPaneSent = new JScrollPane(displaySent);
		displaySent.setEditable(false);
		
		JTextArea displayReceived = new JTextArea("display received values", 20, 25);
	    JScrollPane scrollPaneReceived = new JScrollPane(displayReceived);
	    displayReceived.setEditable(false);
       
        JLabel empty1 = new JLabel("");
        JLabel empty2 = new JLabel("");
        JLabel empty3 = new JLabel("");
        JLabel empty4 = new JLabel("");
      

        JButton send = new JButton();
        JButton startStop = new JButton();

        send.setText("Send values");
        startStop.setText("Start/Stop");
	
		JFrame frame = new JFrame("SimpleUI");
		

		        JPanel main = new JPanel();
		        
		        GridLayout mainGrid = new GridLayout(3,1);
		        main.setLayout(mainGrid);
		        frame.add(main);
		        
		        JPanel top = new JPanel();
		        JPanel middle = new JPanel();
		        JPanel bottom = new JPanel();
		        main.add(top);
		        main.add(middle);
		        main.add(bottom);
		        
		        GridLayout middleGrid = new GridLayout(1,2);
		        GridLayout bottomGrid = new GridLayout(1,3);
		        
		        middle.setLayout(middleGrid);
		        bottom.setLayout(bottomGrid);
		        
		        JPanel middleL = new JPanel();
		        JPanel middleR = new JPanel();
		        middle.add(middleL);
		        middle.add(middleR);
		        
		        middleL.setLayout(new GridLayout(3,2));
		        middleR.setLayout(new GridLayout(2,1));
		        
		        top.add(scrollPaneError);
		        
		        middleL.add(sendtorque);
		        middleL.add(empty1);
		        middleL.add(sendur);
		        middleL.add(send);
		        middleL.add(sendir);
		        middleL.add(empty2);
		        
		        middleR.add(scrollPaneSent);
		        middleR.add(scrollPaneReceived);
		        
		        bottom.add(empty3);
		        bottom.add(startStop);
		        bottom.add(empty4);
		        
		        

		        frame.setSize(700, 400);;

		        frame.setLocationRelativeTo(null);

		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        frame.setVisible(true);

		    }


}
