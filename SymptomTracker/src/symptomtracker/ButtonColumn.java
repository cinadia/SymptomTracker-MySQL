package symptomtracker;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ButtonColumn implements TableCellRenderer, ActionListener, TableCellEditor {

	MakeTable mt = new MakeTable();
	private JTable table;
	private JButton b;
	private int col;
	private boolean editSymptoms;
	private static int CURTABLE; // integer ID for the current table being created
	// 0: Symptom Table 
	// 1: Home Remedy Table
	

	/**
	 * Makes a new ButtonColumn object to implement
	 * renderer and edit models
	 * @param table the table object containing the column to be implemented
	 * @param col the column to be implemented
	 * @param curTable the int ID for the current table. 0 for Symptom, 1 for Home Remedy
	 */
	public ButtonColumn(JTable table, int col, int curTable) {
		this.table = table;
		this.col = col;
		CURTABLE = curTable;
		b = new JButton("Edit");
		b.addActionListener(this);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(col).setCellRenderer(this);
		columnModel.getColumn(col).setCellEditor(this);
	}
	
	// Implementing the TableCellEditor interface
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return b;
	}
	
	@Override
	public boolean isCellEditable(EventObject anEvent) {
	    return true;
	}
	
	@Override
    public void addCellEditorListener(CellEditorListener arg0) {      
    } 
  
    @Override
    public void cancelCellEditing() {
    } 
  
    @Override
    public Object getCellEditorValue() {
      return "";
    }
    
    @Override
    public void removeCellEditorListener(CellEditorListener arg0) {
    }
  
    @Override
    public boolean shouldSelectCell(EventObject arg0) {
      return true;
    }
  
    @Override
    public boolean stopCellEditing() {
      return true;
    }
	
	// Implementing TableCellRenderer interface
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return b;
	}
	
	// Implementing ActionListener interface
	public void actionPerformed(ActionEvent e) {
		// Get the ID/PK of the Symptom or Home Remedy instance
		int s = (int)mt.getValueAt(table.getSelectedRow(), 0, CURTABLE);
		//System.out.println("value" + s);
		
		// Make symptom log table
		if (CURTABLE == 0) {
			EditLogsWindow elw = new EditLogsWindow(s);
			//System.out.println("symptom instance set to " + s);
		} 
		// Make home remedy log table
		if (CURTABLE == 1) {			
			EditHomeRemsWindow ehrw = new EditHomeRemsWindow(s);
			//System.out.println("hr instance set to " + s);
		}
		
		
		
	}
}


