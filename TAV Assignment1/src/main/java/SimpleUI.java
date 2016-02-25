
import java.awt.GridLayout;

import javax.swing.*;

public class SimpleUI {
	
	public static JButton send;
	public static Listener l = new Listener();

	// Constructor for testing
	public SimpleUI(){
	}
	
	/* 
	 * Helper method: checks if a given string s represents an integer.
	 * Return true if it does, false otherwise
	 */
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	/*
	 * This method checks that the values inputed by the user for torque, US distance
	 * and IR distance are valid, i.e. they are all non-empty strings representing
	 * an integer.
	 * The method returns true if they are, false otherwise.
	 */
	public boolean areInputsInts(String t, String u, String i) {
		if (t.length() == 0 || u.length() == 0 || i.length() == 0) {
			return false;
		} else if (!(isInteger(t) & isInteger(u) & isInteger(i))) {
			return false;
		} else {
			return true;
		}
	}


	public static void main(String s[]) {
		
		JFrame frame = createFrame();
		
		JPanel main = createMainPanel(frame);
		
		createTopPanel(main);
		
		createMiddlePanel(main);
		 
		createBottomPanel(main);

        setFrameParameters(frame);

		}

	
	/*
	 * METHODS TO CREATE THE GUI PANELS, GRIDS, BUTTONS, LABELS, ETC.
	 * NO LOGIC HERE.
	 */
	private static void setFrameParameters(JFrame frame) {
		frame.setSize(700, 400);;

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
	}

	private static void createBottomPanel(JPanel main) {
		JPanel bottom = new JPanel();
        main.add(bottom);
        
        GridLayout bottomGrid = new GridLayout(1,3);
        bottom.setLayout(bottomGrid);
        
        JLabel empty3 = new JLabel("");
        JLabel empty4 = new JLabel("");
              
        JButton startStop = new JButton();  
        startStop.setText("Start/Stop");

        bottom.add(empty3);
        bottom.add(startStop);
        bottom.add(empty4);
	}

	private static void createMiddlePanel(JPanel main) {
		JPanel middle = new JPanel();
		main.add(middle);
		
		GridLayout middleGrid = new GridLayout(1,2);
		middle.setLayout(middleGrid);
		
		JPanel middleL = new JPanel();
        JPanel middleR = new JPanel();
        
        middle.add(middleL);
        middle.add(middleR);
        
        middleL.setLayout(new GridLayout(3,2));
        middleR.setLayout(new GridLayout(2,1));
        
        JTextField sendtorque = new JTextField("input torque");
        JTextField sendir = new JTextField("input ir");
        JTextField sendur = new JTextField("input ur");
        
        JTextArea displaySent = new JTextArea("display sent values", 20, 25);
        JScrollPane scrollPaneSent = new JScrollPane(displaySent);
		displaySent.setEditable(false);
		
		JTextArea displayReceived = new JTextArea("display received values", 20, 25);
	    JScrollPane scrollPaneReceived = new JScrollPane(displayReceived);
	    displayReceived.setEditable(false);
	    
	    send = new JButton();
	    send.setText("Send values");
	    send.addActionListener(l);
       
        JLabel empty1 = new JLabel("");
        JLabel empty2 = new JLabel("");
        
        middleL.add(sendtorque);
        middleL.add(empty1);
        middleL.add(sendur);
        middleL.add(send);
        middleL.add(sendir);
        middleL.add(empty2);
        
        middleR.add(scrollPaneSent);
        middleR.add(scrollPaneReceived);
	}

	private static void createTopPanel(JPanel main) {
		JPanel top = new JPanel();
        main.add(top);
		JTextArea errors = new JTextArea("Errors display", 5, 50);
		JScrollPane scrollPaneError = new JScrollPane(errors);
		errors.setEditable(false);
        top.add(scrollPaneError);
	}

	private static JPanel createMainPanel(JFrame frame) {
		JPanel main = new JPanel();
        GridLayout mainGrid = new GridLayout(3,1);
        main.setLayout(mainGrid);
        frame.add(main);
		return main;
	}

	private static JFrame createFrame() {
		JFrame frame = new JFrame("SimpleUI");
		return frame;
	}


}
