package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

public class LogSymWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void newLogSymWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogSymWindow window = new LogSymWindow();
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
	public LogSymWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Labels
		JLabel lblNewLabel = new JLabel("Location");
		lblNewLabel.setBounds(10, 11, 71, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Symptom Type");
		lblNewLabel_1.setBounds(10, 36, 86, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setBounds(10, 61, 48, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Severity");
		lblNewLabel_3.setBounds(10, 86, 48, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duration (min)");
		lblNewLabel_4.setBounds(10, 116, 105, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Final Score");
		lblNewLabel_5.setBounds(10, 141, 71, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		// Symptom Location dropdown 
		
		JComboBox comboBox = new JComboBox(Getters.getSymptomLocations());
		comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        String location = (String)cb.getSelectedItem();
				LogSymptom.setLocation(location); 
			}
		});
		comboBox.setBounds(125, 7, 86, 22);
		frame.getContentPane().add(comboBox);
		
		// Symptom Type dropdown
		JComboBox comboBox_1 = new JComboBox(Getters.getSymptoms());
		comboBox_1.setSelectedItem(null);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
				String location = (String)cb.getSelectedItem();
				LogSymptom.setSymptom(location);
			}
		});
		comboBox_1.setBounds(125, 32, 86, 22);
		frame.getContentPane().add(comboBox_1);
		
		
		// Final score label 
		JLabel lblNewLabel_6 = new JLabel("CALCULATING...");
		lblNewLabel_6.setBounds(125, 141, 128, 14);
		frame.getContentPane().add(lblNewLabel_6);
				
		
		// Severity radio buttons
		JRadioButton rdbtnNewRadioButton = new JRadioButton("1");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymptom.setSeverity(1);
				int length = LogSymptom.getLength();
				if (length != -1) {
					lblNewLabel_6.setText(Integer.toString(length));
					LogSymptom.setScore(length);
				}
			}
		});
		rdbtnNewRadioButton.setBounds(125, 82, 37, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("2");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymptom.setSeverity(2);
				int length = LogSymptom.getLength();
				if (length != -1) {
					int score = 2*length;
					lblNewLabel_6.setText(Integer.toString(score));
					LogSymptom.setScore(score);

				}
			}
		});
		rdbtnNewRadioButton_1.setBounds(164, 82, 37, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("3");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymptom.setSeverity(3);
				int length = LogSymptom.getLength();
				if (length != -1) {
					int score = 3*length;
					lblNewLabel_6.setText(Integer.toString(score));
					LogSymptom.setScore(score);

				}
			}
		});
		rdbtnNewRadioButton_2.setBounds(203, 82, 37, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("4");
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymptom.setSeverity(4);
				int length = LogSymptom.getLength();
				if (length != -1) {
					int score = 4*length;
					lblNewLabel_6.setText(Integer.toString(score));
					LogSymptom.setScore(score);
				}
			}
		});
		rdbtnNewRadioButton_3.setBounds(242, 82, 37, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("5");
		rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymptom.setSeverity(5);
				int length = LogSymptom.getLength();
				if (length != -1) {
					int score = 5*length;
					lblNewLabel_6.setText(Integer.toString(score));
					LogSymptom.setScore(score);
				}
			}
		});
		rdbtnNewRadioButton_4.setBounds(281, 82, 37, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_4);
		
		// Group radio buttons
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnNewRadioButton);
	    group.add(rdbtnNewRadioButton_1);
	    group.add(rdbtnNewRadioButton_2);
	    group.add(rdbtnNewRadioButton_3);
	    group.add(rdbtnNewRadioButton_4);
		
	    // Duration text field
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    String text = textField.getText();
			    if (isValid(text)) {
			    	Integer dur = Integer.parseInt(text);
			    	LogSymptom.setLength(dur);
			    	int sev = LogSymptom.getSeverity();
			    	if (sev != -1) {
			    		int score = sev*dur;
			    		lblNewLabel_6.setText(Integer.toString(score));
						LogSymptom.setScore(score);
			    	}
			     } else {
			    	 JOptionPane.showMessageDialog(frame, "Whoops! Positive numbers only!");
			    	 }
			  }
		});
		textField.setBounds(125, 113, 42, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// SAVE button
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogSymptom.runLogSymptom();
				System.out.println("SAVE button was clicked.");
				frame.dispose();
			}
		});
		btnNewButton.setBounds(174, 166, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(125, 61, 115, 20);
		dateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent e) {
			            if ("date".equals(e.getPropertyName())) {
			            	java.util.Date utilDate = (java.util.Date) e.getNewValue();
			            	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			            	LogSymptom.setDate(sqlDate);
			            }
			        }
			    });
		frame.getContentPane().add(dateChooser);
	}
	
	// Checks if text is valid
	private static boolean isValid(String text) {
	   try {
	      Integer.parseInt(text);
	      if (Integer.parseInt(text) <= 0)
	    	  return false;
	      return true;
	   } catch (NumberFormatException e) {
	      return false;
	   }
	}
}
