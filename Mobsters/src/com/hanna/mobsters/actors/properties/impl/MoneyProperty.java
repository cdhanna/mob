/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Property;

/**
 * @author Chris Hanna
 *
 */
public class MoneyProperty extends Property<Double> {

	@Override
	public Double getDefaultValue() {
		return 10.0;
	}

	@Override
	public String getPropertyName() {
		return "Money";
	}

}
