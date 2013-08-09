/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Property;

/**
 * @author cdhan_000
 *
 */
public class MedicalStateProperty extends Property<Double>{

	@Override
	public Double getDefaultValue() {
		return 70.0; //standard good health
	}

	@Override
	public String getPropertyName() {
		return "Medical State";
	}

}
