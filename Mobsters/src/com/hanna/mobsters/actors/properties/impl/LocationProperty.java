/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.properties.Property;

/**
 * @author Chris Hanna
 *
 */
public class LocationProperty extends Property<Location> {

	@Override
	public Location getDefaultValue() {
		return Location.MOB_HOME;
	}

	@Override
	public String getPropertyName() {
		return "Location";
	}


	
}
