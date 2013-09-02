package com.hanna.mobsters.actions.impl.results;

import com.hanna.mobsters.actions.core.ActionResult;

public class KilledResult extends ActionResult{
	static String str = "Actor was killed. Action was not completed.";
	public KilledResult() {
		super(str);
	}

}
