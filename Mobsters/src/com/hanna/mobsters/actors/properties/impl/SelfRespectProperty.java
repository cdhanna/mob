/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Property;

/**
 * @author Chris Hanna
 *
 */
public class SelfRespectProperty extends Property<Double>{

	@Override
	public Double getDefaultValue() {
		return 1.0;
	}

	@Override
	public String getPropertyName() {
		return "Self Respect";
	}

}
