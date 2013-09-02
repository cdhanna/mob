/**
 * 
 */
package com.hanna.mobsters.utilities.inputbox;

import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author cdhan_000
 *
 */
public abstract class InputBox<T> extends Panel{

	protected T instance;
	protected Class<T> type;
	
	public InputBox(Class<T> type, T instance){
		super();
		this.type = type;
		this.instance = instance;
		
		this.setContentForValue(instance);
	}
	
	protected String getDefaultText(Class<T> type){
		String base = "Enter ";
		
		String typeName = type.getSimpleName();
		
		if ("aeiou".contains((""+typeName.charAt(0)).toLowerCase()))
			base += "an ";
		else base += "a ";
		base += typeName;
		
		return base;
	}
	
	public abstract void setContentForValue(T instance);
	public abstract void setValue();
	public T getValue(){
		return this.instance;
	}
	
	protected final void fireValueChanged(){
		for (ValueChangedListener<T> listener : this.listeners){
			listener.valueChanged(instance);
		}
	}
	public final void addValueChangedListener(ValueChangedListener<T> listener){
		this.listeners.add(listener);
	}
	
	List<ValueChangedListener<T>> listeners = new ArrayList<>();
	public interface ValueChangedListener<T>{
		public void valueChanged(T instance);
	}
}
