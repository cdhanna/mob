/**
 * 
 */
package com.hanna.mobsters.utilities.parsers.impl;

import com.hanna.mobsters.utilities.parsers.Parser;

/**
 * @author Chris Hanna
 *
 */
public class StringParser extends Parser<String>{

	@Override
	protected String parseLocal(String str) throws Exception {
		return str;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

}
