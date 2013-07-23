package com.hanna.mobsters.utilities.parsers;

public abstract class Parser <T>{

	protected abstract T parseLocal(String str) throws Exception;
	public final T parse(String str){
		T val = null;
		try { val = this.parseLocal(str); }
		catch ( Exception e) { val = null; }
		return val;
	}
	public abstract Class<T> getType();
	
}
