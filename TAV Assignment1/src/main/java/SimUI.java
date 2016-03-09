import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimUI {
	public static JButton connLoss;
	public static Listener l = new Listener();
	
	public static void runSimUI() {
		JFrame frame = new JFrame("Simulation");
		frame.setSize(200, 80);
		frame.setLocation(10,10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel main = new JPanel();
		frame.add(main);
		
		connLoss = new JButton();
	    connLoss.setText("Cut connection!");
	    connLoss.addActionListener(l);
	    main.add(connLoss);
	}
	
}
