package symptomtracker;

import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ButtonColumn implements TableCellRenderer, ActionListener, TableCellEditor {

	private JTable table;
	private JButton b;
	private int col;
	
	private static int CURTABLE;
	private boolean editSymptoms;
	
	public ButtonColumn(JTable table, int col, int curTable) {
		this.table = table;
		this.col = col;
		CURTABLE = curTable;
		b = new JButton("Edit");
		b.addActionListener(this);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(col).setCellRenderer( this );
		columnModel.getColumn(col).setCellEditor( this );
	}
	
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
		int s = (int)MakeTable.getValueAt(table.getSelectedRow(), 0, CURTABLE);
		System.out.println(s);

		
		
		// Make symptom log table
		if (CURTABLE == 0) {
			EditLogsWindow.setSymptomInstance(s);
			EditLogsWindow.newEditLogsWindow();
		} 
		// Make home remedy log table
		if (CURTABLE == 1) {
			EditHomeRemsWindow.setHRInstance(s);
			EditHomeRemsWindow.newEditHomeRemsWindow();
		}
		
		
		
	}
}


