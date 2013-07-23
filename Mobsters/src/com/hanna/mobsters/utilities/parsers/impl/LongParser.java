package com.hanna.mobsters.utilities.parsers.impl;

import com.hanna.mobsters.utilities.parsers.Parser;

public class LongParser extends Parser<Long>{

	@Override
	protected Long parseLocal(String str) throws Exception {
		return Long.parseLong(str);
	}

	@Override
	public Class<Long> getType() {
		return Long.class;
	}

}
