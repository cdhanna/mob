/**
 * 
 */
package com.hanna.mobsters.actors;

/**
 * @author Chris Hanna
 *
 */
public class Response {

	private boolean yesno;
	private String message;
	private Decision decision;
	
	/**
	 * @param yesno - will the actor do the action or not? Note that even if yes, the action may be modified
	 * @param message - the verbal response returned to the issuer of the order
	 * @param decision - contains the traits and corresponding magnitudes that went into the decision making process
	 */
	public Response(boolean yesno, String message, Decision decision){
		this.yesno = yesno;
		this.message = message;
		this.decision = decision;
	}
	
	public boolean getYesNo(){
		return this.yesno;
	}
	public String getMessage(){
		return this.message;
	}
	public Decision getDecision(){
		return this.decision;
	}
}
