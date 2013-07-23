package com.hanna.mobsters.utilities.parsers.impl;

import com.hanna.mobsters.utilities.parsers.Parser;

public class DoubleParser extends Parser<Double>{


	@Override
	public Class<Double> getType() {
		return Double.class;
	}

	@Override
	protected Double parseLocal(String str) throws Exception {
		return Double.parseDouble(str);
	}

}
