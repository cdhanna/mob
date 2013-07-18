/**
 * 
 */
package com.hanna.mobsters.ui.shared;

import java.util.HashMap;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import com.hanna.mobsters.actions.core.Action;

/**
 * @author Chris Hanna
 *
 */
public class ComboBox<T> extends JComboBox<String> {

	private HashMap<T, String> valueToString;
	private HashMap<String, T> stringToValue;
	
	private DefaultComboBoxModel<String> model;

	public ComboBox(String defaultText){
		super();
		//this.valueToString = new HashMap<>();
		this.stringToValue = new HashMap<>();

		model = new DefaultComboBoxModel<>();
		model.addElement(defaultText);
		
		this.setModel(this.model);
	}
	
	public String elementToString(T element){
		return element.toString();
	}
	
	public void addElement(T element){
		this.model.addElement(this.elementToString(element));
		//this.strings.addElement(this.elementToString(element));
		//this.valueToString.put(element, this.elementToString(element));
		this.stringToValue.put(this.elementToString(element), element);
	}
	
	public void setElements(T[] elements){
		T selected = this.getSelection();
		while (this.model.getSize() > 1)
			this.model.removeElementAt(1);
		for (T element : elements){
			this.addElement(element);
			if (element.equals(selected))
				this.setSelection(selected);
		}
		
//		T selected = this.getSelection();
//		while (this.model.getSize() > 1)
//			this.model.removeElementAt(1);
//		for (T element : elements){
//			this.model.addElement(element);
//			if (element.equals(selected))
//				this.setSelection(selected);
//		}
		
	}
	
	public T getSelection(){
		
		if (this.getSelectedIndex() > 0){
			String selectedKey = (String)this.getSelectedItem();
			return this.stringToValue.get(selectedKey);
		}
		return null;
		
//		if (this.getSelectedIndex() > 0)
//			return (T)this.getSelectedItem();
//		return null;
		
	}
	public void setSelection(T element){
		if (element == null)
			this.setSelectedIndex(0);
		else this.setSelectedItem(this.elementToString(element));
//		if (element == null)
//			this.setSelectedIndex(0);
//		else this.setSelectedItem(element);
	}

	public void setElements(List<T> elements) {
		T selected = this.getSelection();
		while (this.model.getSize() > 1)
			this.model.removeElementAt(1);
		for (T element : elements){
			this.addElement(element);
			if (element.equals(selected))
				this.setSelection(selected);
		}
	}
}
