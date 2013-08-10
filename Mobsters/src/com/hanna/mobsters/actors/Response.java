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
