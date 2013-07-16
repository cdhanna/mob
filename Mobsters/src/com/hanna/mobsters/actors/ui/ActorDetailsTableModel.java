/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Chris Hanna
 *
 */
public class ActorDetailsTableModel extends AbstractTableModel {
	
	List<Object[]> data;
	
	public ActorDetailsTableModel(){
		this.data = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addRow(Object field, Object value) {
		
	}

}
