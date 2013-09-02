/**
 * 
 */
package com.hanna.mobsters.utilities.inputbox;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.ui.Splasher;
import com.hanna.mobsters.utilities.ReflectionsHelper;
import com.hanna.mobsters.utilities.parsers.Parser;

/**
 * @author cdhan_000
 *
 */
public class InputBoxRegistry {

	
	private static InputBoxRegistry instance;
	public static InputBoxRegistry getInstance(){
		if (instance == null)
			instance = new InputBoxRegistry();
		return instance;
	}
	private List<Class<? extends CustomInputBox>> customBoxes;
	private HashMap<Class<?>, Class<? extends CustomInputBox>> customBoxTable;
	
	private InputBoxRegistry(){
		this.customBoxes = new ArrayList<>();
		this.customBoxTable = new HashMap<>();
		Set<Class<? extends CustomInputBox>> parseTypes = ReflectionsHelper.getInstance().getSubTypes(CustomInputBox.class, "com.hanna.mobsters.utilities.inputbox.impl");
		for (Class<? extends CustomInputBox> p : parseTypes)
			this.register(p);

	}

	private void register(Class<? extends CustomInputBox> box){
		//this.customBoxTable.put(box.get, arg1)
		
		Class<?> type = box.getConstructors()[0].getParameterTypes()[0];
		this.customBoxTable.put(type, box);
		this.customBoxes.add(box);
		
		Splasher.setMsg(type.getSimpleName() + " input box");
		
	}
	public <T> InputBox<T> getInputBoxFor(Class<T> type, T object){
		
		if (this.customBoxTable.containsKey(type)){
			try {
				return (InputBox<T>) this.customBoxTable.get(type).getConstructors()[0].newInstance(object);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| SecurityException e) {
				e.printStackTrace();
				return null;
			}
		} else return new DefaultInputBox<T>(type, object);
		
	}

	public InputBox getInputBoxForUnsafe(Class type, Object obj) {
		return this.getInputBoxFor(type, type.cast(obj));
	}

}
