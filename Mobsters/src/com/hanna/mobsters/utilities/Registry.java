/**
 * 
 */
package com.hanna.mobsters.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Chris Hanna
 * Why is this here? No one really knows. It was more of a java experiement I suppose. It seemed that since
 * there were so many Registry object about, it would be nice to consolidate their code a bit. However, 
 * since a Registry is a singleton, and a singleton must have a private constructor, then making the singleton
 * a super class spoofs the whole god dayum point.
 */
public abstract class Registry<T>{
	
	private Class<T> superType;
	
	private List<Class<? extends T>> knownChildTypes;
	
	protected Registry(){
		this.knownChildTypes = new ArrayList<>();
		this.superType = this.getType();
		this.scanForEntries();
	}
	protected abstract String getRegistryPath();
	protected abstract Class<T> getType();
	protected abstract void register(Class<? extends T> entry);
	
	protected void registerMaster(Class<? extends T> entry){
		this.knownChildTypes.add(entry);
		this.register(entry);
	}
	private final void scanForEntries(){
		Class<T> e;
		
		Set<Class<? extends T>> set = ReflectionsHelper.getInstance().getSubTypes(superType, this.getRegistryPath());
		for (Class<? extends T> entry : set)
			this.registerMaster(entry);
	}
	
}
