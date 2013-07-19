/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Property;

/**
 * @author Chris Hanna
 *
 */
public class MoneyProperty extends Property<Integer> {

	@Override
	public Integer getDefaultValue() {
		return 0;
	}

	@Override
	public String getName() {
		return "Money";
	}

}
