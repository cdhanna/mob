/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Chris Hanna
 *
 */
public class ActorDetailsTableModel extends AbstractTableModel {
	
	public ActorDetailsTableModel(){
		
	}

	@Override
	public int getRowCount() {
		return 3;
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

}
