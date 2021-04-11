package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ViewHomeRems {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	// The table column holding the Edit buttons
	private static int EDITCOL = 3;
	
	// The integer ID for the current type of table - symptom table
	private static int HRTABLE = 1;
	
	MakeTable mt = new MakeTable();


	/**
	 * Launch the ViewHomeRems application, where
	 * user can view and edit their logged home remedies.
	 */
	public void newViewHomeRems() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHomeRems window = new ViewHomeRems();
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
	public ViewHomeRems() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 611, 301);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// New table
		table = new JTable(mt.newTableModel(HRTABLE));
		// Add to scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 22, 589, 244);
		
		// Remove first column containing home remedy instance ID
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
        frame.getContentPane().setLayout(null);
		
        // Set Cell Renderer for Edit column
        table.getColumn("Edit").setCellRenderer(new ButtonColumn(table, EDITCOL, HRTABLE));
		frame.getContentPane().add(scrollPane);
		
		// Refresh stats button
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setVerticalAlignment(SwingConstants.TOP);
		btnRefresh.setBounds(0,0,438,265);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Set new model
				table.setModel(mt.newTableModel(HRTABLE));
				
				//TableColumnModel tcm = table.getColumnModel();
				tcm.removeColumn(tcm.getColumn(0));
				
				table.getColumn("Edit").setCellRenderer(new ButtonColumn(table, EDITCOL, HRTABLE));
				
			}
		});
		btnRefresh.setBounds(322, 0, 106, 23);
		frame.getContentPane().add(btnRefresh);
	}
}
