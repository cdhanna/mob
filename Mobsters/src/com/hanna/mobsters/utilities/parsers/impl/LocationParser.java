/**
 * 
 */
package com.hanna.mobsters.utilities.parsers.impl;

import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.utilities.parsers.ParseRegistry;
import com.hanna.mobsters.utilities.parsers.Parser;

/**
 * @author Chris Hanna
 *
 */
public class LocationParser extends Parser<Location>{

	@Override
	protected Location parseLocal(String str) throws Exception {
		String letters = str.substring(0, str.indexOf(" "));
		String street = str.substring(str.indexOf(" ")+1);
		Integer number = ParseRegistry.getInstance().parse(letters, Integer.class);
		if (number == null)
			number = 0;
		Location location = new Location(street, number);
		return location;
	}

	@Override
	public Class<Location> getType() {
		return Location.class;
	}

}
