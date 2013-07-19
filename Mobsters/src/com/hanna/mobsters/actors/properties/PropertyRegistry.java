/**
 * 
 */
package com.hanna.mobsters.actors.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.hanna.mobsters.actions.core.Action;

/**
 * @author Chris Hanna
 *
 */
public class PropertyRegistry {

	public static void main(String[] args){
		getInstance();
	}
	
	private static PropertyRegistry instance;
	public static PropertyRegistry getInstance(){
		if (instance == null)
			instance = new PropertyRegistry();
		return instance;
	}
	
	private List<Class<? extends Property<?>>> knownProperties;
	
	private PropertyRegistry(){
		this.knownProperties = new ArrayList<>();
		this.scanForProperties();
		
		
	}
	
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
		Reflections r = new Reflections("com.hanna.mobsters.actors.properties.impl");
		Set<Class<? extends Property>> subTypes = r.getSubTypesOf(Property.class);
		System.out.println("Properties Scanned " +subTypes.size());
		for (Class<? extends Property> c : subTypes){
			System.out.println("\t"+c);
			Class<? extends Property<?>> c2 = (Class<? extends Property<?>>) c;
			this.register(c2);
		}
	}
	
	private void register(Class<? extends Property<?>> clazz){
		this.knownProperties.add(clazz);
	}
}
