package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class NewClientWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newNewClientWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewClientWindow window = new NewClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
