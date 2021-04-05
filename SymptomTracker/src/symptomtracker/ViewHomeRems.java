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

public class ViewHomeRems {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private static int EDITCOL = 3;
	private static int HRTABLE = 1;


	/**
	 * Launch the application.
	 */
	public static void newViewHomeRems() {
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
		frame.setBounds(100, 100, 450, 300);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		table = new JTable(MakeTable.newTable(HRTABLE));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 22, 428, 243);
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.removeColumn(tcm.getColumn(0));
        frame.getContentPane().setLayout(null);
		
        table.getColumn("Edit").setCellRenderer(new ButtonColumn(table, EDITCOL, HRTABLE));
		frame.getContentPane().add(scrollPane);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(0,0,438,265);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(MakeTable.newTable(HRTABLE));
				
				TableColumnModel tcm = table.getColumnModel();
				tcm.removeColumn(tcm.getColumn(0));
				
				table.getColumn("Edit").setCellRenderer(new ButtonColumn(table, EDITCOL, HRTABLE));
				
			}
		});
		btnRefresh.setBounds(322, 0, 106, 23);
		frame.getContentPane().add(btnRefresh);
	}
}
