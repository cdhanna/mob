/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.utilities.KryoHelper;

/**
 * @author Chris Hanna
 *
 */
public class LocationProperty extends Property<Location> {

	@Override
	public Location getDefaultValue() {
		return KryoHelper.getInstance().copy(Location.MOB_HOME);
		//return Location.MOB_HOME;
	}

	@Override
	public String getPropertyName() {
		return "Location";
	}


	
}
