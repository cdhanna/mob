/**
 * 
 */
package com.hanna.mobsters.utilities.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.hanna.mobsters.ui.Splasher;
import com.hanna.mobsters.utilities.ReflectionsHelper;
import com.hanna.mobsters.utilities.parsers.impl.IntegerParser;

/**
 * @author Chris Hanna
 *
 */
public class ParseRegistry {

	private static ParseRegistry instance;
	public static ParseRegistry getInstance(){
		if (instance == null)
			instance = new ParseRegistry();
		return instance;
	}

	private List<Parser<?>> parsers;
	private HashMap<Class<?>, Parser<?>> parseTable;
	private ParseRegistry(){
		this.parsers = new ArrayList<>();
		this.parseTable = new HashMap<>();
		try {
			Set<Class<? extends Parser>> parseTypes = ReflectionsHelper.getInstance().getSubTypes(Parser.class, "com.hanna.mobsters.utilities.parsers.impl");
			for (Class<? extends Parser> p : parseTypes)
				this.register(p.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.register(new IntegerParser());

	}

	private void register(Parser p){
		this.parsers.add(p);
		this.parseTable.put(p.getType(), p);
		
		Splasher.setMsg(p.getType().getSimpleName() + " parser");
	}

	public <T> T parse(String str, Class<T> clazz){
		if (this.parseTable.containsKey(clazz)){
			Parser<T> p = (Parser<T>) this.parseTable.get(clazz);
			return p.parse(str);
		} else return null;
	}


}
