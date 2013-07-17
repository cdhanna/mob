/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import com.hanna.mobsters.actions.Action;

/**
 * @author Chris Hanna
 *
 */
public class ComboBox<T> extends JComboBox<Object> {

	private DefaultComboBoxModel<Object> model;
	public ComboBox(String defaultText){
		super();
		model = new DefaultComboBoxModel<>();
		model.addElement(defaultText);
		this.setModel(model);
	}
	
	public void addElement(T element){
		this.model.addElement(element);
	}
	
	public void setElements(T[] elements){
		T selected = this.getSelection();
		while (this.model.getSize() > 1)
			this.model.removeElementAt(1);
		for (T element : elements){
			this.model.addElement(element);
			if (element.equals(selected))
				this.setSelection(selected);
		}
		
	}
	
	public T getSelection(){
		if (this.getSelectedIndex() > 0)
			return (T)this.getSelectedItem();
		return null;
	}
	public void setSelection(T element){
		if (element == null)
			this.setSelectedIndex(0);
		else this.setSelectedItem(element);
	}

	public void setElements(List<T> elements) {
		T selected = this.getSelection();
		while (this.model.getSize() > 1)
			this.model.removeElementAt(1);
		for (T element : elements){
			this.model.addElement(element);
			if (element.equals(selected))
				this.setSelection(selected);
		}
	}
}
