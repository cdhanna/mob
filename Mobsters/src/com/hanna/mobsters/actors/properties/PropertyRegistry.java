/**
 * 
 */
package com.hanna.mobsters.actors.properties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.hanna.mobsters.ui.Splasher;
import com.hanna.mobsters.utilities.KryoHelper;
import com.hanna.mobsters.utilities.ReflectionsHelper;

/**
 * @author Chris Hanna
 *
 */
public class PropertyRegistry {
	
	private static PropertyRegistry instance;
	public static PropertyRegistry getInstance(){
		if (instance == null)
			instance = new PropertyRegistry();
		return instance;
	}
	
	private List<Class<? extends Property<?>>> knownProperties;
	private List<String> knownPropertyNames;
	private List<Class<?>> knownPropertyDataTypes;
	
	private PropertyRegistry(){
		this.knownProperties = new ArrayList<>();
		this.knownPropertyNames = new ArrayList<>();
		this.knownPropertyDataTypes = new ArrayList<>();
		this.scanForProperties();
		
		
	}
	
	
	/**
	 * @return a fresh list of all known property classes.
	 */
	public List<Class<? extends Property<?>>> getKnownProperties(){
		return KryoHelper.getInstance().copy(this.knownProperties);
	}
	
	/**
	 * @return a fresh list of all known property names;
	 */
	public List<String> getKnownPropertyNames(){
		return KryoHelper.getInstance().copy(this.knownPropertyNames);
	}
	
	/**
	 * @return a fresh list of all known property data types. 
	 */
	public List<Class<?>> getKnownPropertyDataTypes(){
		return KryoHelper.getInstance().copy(this.knownPropertyDataTypes);
	}
	
	/**
	 * @return a copy of the registry's table. 
	 * This copy will contain a mapping from every
	 * known property class to a unique instance of 
	 * that property that is set to the property default
	 */
	public HashMap<Class<? extends Property<?>>, Property<?>> makePropertyTable(){
		HashMap<Class<? extends Property<?>>, Property<?>> table = new HashMap<>();
		for (Class<? extends Property<?>> propType : this.knownProperties){
			
			try {
				table.put(propType, propType.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return table;
	}
	
	private void scanForProperties(){
		
		Set<Class<? extends Property>> subTypes = ReflectionsHelper.getInstance().getSubTypes(Property.class, "com.hanna.mobsters.actors.properties.impl");
		//System.out.println("Properties Scanned " +subTypes.size());
		for (Class<? extends Property> c : subTypes){
			//System.out.println("\t"+c);
			Class<? extends Property<?>> c2 = (Class<? extends Property<?>>) c;
			this.register(c2);
		}
	}
	
	private void register(Class<? extends Property<?>> clazz){
		this.knownProperties.add(clazz);
		try {
			
			Property<?> p = clazz.newInstance();
			this.knownPropertyDataTypes.add(p.getType());
			
			Method nameMethod = clazz.getMethod("getPropertyName", new Class[]{});
			Object name = nameMethod.invoke(p, new Object[]{});
			this.knownPropertyNames.add(""+name);
			
			Splasher.setMsg(clazz.getSimpleName() + " property");
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
