/**
 * 
 */
package com.hanna.mobsters.utilities.parsers.impl;

import com.hanna.mobsters.utilities.parsers.Parser;

/**
 * @author Chris Hanna
 *
 */
public class IntegerParser extends Parser<Integer>{


	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	protected Integer parseLocal(String str) throws Exception {
		return Integer.parseInt(str);
	}

}
