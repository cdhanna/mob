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

	public ActorDetailsTable(ActorDetailsTableModel detailsModel) {
		super(detailsModel);
		
		this.getColumnModel().getColumn(0).setHeaderValue("Field");
		this.getColumnModel().getColumn(1).setHeaderValue("Value");
	}

}
