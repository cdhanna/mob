package com.hanna.mobsters.actions.core;

/**
 * @author Will
 * A wrapper class for packaging the results of an action into an element to be stored in a GameEvent.
 */
public class ActionResult {

	protected String resultText;
	
	/**
	 * Default constructor for an action which has not been done yet.
	 */
	public ActionResult(){
		this.resultText = "Action has not been completed.";
	}
	public ActionResult(String resultText) {
		this.resultText = resultText;
	}
	
	@Override
	public String toString(){
		return this.resultText;
	}

}
