/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author Chris Hanna
 *
 */
public class ObjectList<T> extends JList<T>{

	private DefaultListModel<T> model;
	
	
	
	public ObjectList(){
		super();
		this.model = new DefaultListModel<T>();
		this.setModel(this.model);
	}
	
	public void addElement(T object){
		this.model.addElement(object);
	}
	
	public void removeElement(T object){
		this.model.removeElement(object);
	}
	
	public void removeAllElements(){
		this.model.removeAllElements();
	}
}
