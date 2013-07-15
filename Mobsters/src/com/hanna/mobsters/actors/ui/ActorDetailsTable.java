/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Chris Hanna
 *
 */
public class ActorDetailsTable extends JTable{

	public ActorDetailsTable(DefaultTableModel detailsModel) {
		super(detailsModel);
		
		
	}

	@Override
	public int getRowCount(){
		return 3;
	}
	
	@Override
	public int getColumnCount(){
		return 2;
	}
	
}
