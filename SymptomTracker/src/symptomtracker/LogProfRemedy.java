package symptomtracker;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogProfRemedy {

	public LogProfRemedy() {
		// New window to log symptoms
		JFrame window = new JFrame("Log Professional Remedy");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		
		// back panel
		JPanel back = new JPanel();
		back.setLayout(new GridLayout(3,0, 1, 10));
		window.add(back);
		back.setVisible(true);
		
		// Add panel for logging remedy
		JPanel remedyPan = new JPanel();
		remedyPan.setLayout(new GridLayout(1,2));
		back.add(remedyPan);
		
			// Add label
		JLabel remedyLab = new JLabel("Remedy");
		remedyPan.add(remedyLab);
		
		
		// Add panel for tracking which location the
		// remedy is for
		JPanel forLocationPan = new JPanel();
		forLocationPan.setLayout(new GridLayout(1,2));
		back.add(forLocationPan);
		
			// Add label
		JLabel forLocationLab = new JLabel("For");
		forLocationPan.add(forLocationLab);

		
		// Add panel for logging date
		JPanel datePan = new JPanel();
		datePan.setLayout(new GridLayout(1,2));
		back.add(datePan);
		
			// Add label
		JLabel dateLab = new JLabel("Date");
		datePan.add(dateLab);
		
		window.pack();
	}

}
