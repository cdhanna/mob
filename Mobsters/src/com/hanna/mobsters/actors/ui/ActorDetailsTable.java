/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import javax.swing.JTable;

/**
 * @author Chris Hanna
 *
 */
public class ActorDetailsTable extends JTable{

	ActorDetailsTableModel model;
	
	public ActorDetailsTable(ActorDetailsTableModel detailsModel) {
		super(detailsModel);
		this.model = detailsModel;
		this.getColumnModel().getColumn(0).setHeaderValue("Field");
		this.getColumnModel().getColumn(1).setHeaderValue("Value");
		this.getTableHeader().setReorderingAllowed(false);
	}

	public void addRow(Object field, Object value){
		this.model.addRow(field, value);
	}
	
}
