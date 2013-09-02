package com.hanna.mobsters.actions.impl.results;

import com.hanna.mobsters.actions.core.ActionResult;

public class RefusedResult extends ActionResult {
	static String str = "Action was refused by Actor and not completed.";
	public RefusedResult() {
		super(str);
	}

}
