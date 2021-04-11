package symptomtracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewSymLogs {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private static int EDITCOL = 6; // The table column holding the Edit buttons
	
	// The integer ID for the current type of table - symptom table
	private static int SYMTABLE = 0; 
	
	MakeTable mt = new MakeTable();


	/**
	 * Create the application.
	 */
	public ViewSymLogs() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 617, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Make table
		table = new JTable(mt.newTableModel(SYMTABLE));
		// Add to scrollpane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 22, 595, 243);
		
		// Remove first column of table that contains symptom_instance_id
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
        frame.getContentPane().setLayout(null);
		
        // Set new CellRenderer for Edit column
        table.getColumn("Edit").setCellRenderer(new ButtonColumn(table, EDITCOL, SYMTABLE));
		frame.getContentPane().add(scrollPane);
		
		// Refresh button
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(485,0,110,23);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Set new table model upon refresh
				table.setModel(mt.newTableModel(SYMTABLE));
				
				tcm.removeColumn(tcm.getColumn(0));
				
				table.getColumn("Edit").setCellRenderer(new ButtonColumn(table, EDITCOL, SYMTABLE));
				
			}
		});
		btnRefresh.setBounds(328, 0, 110, 23);
		frame.getContentPane().add(btnRefresh);
		frame.setVisible(true);
	}
}
