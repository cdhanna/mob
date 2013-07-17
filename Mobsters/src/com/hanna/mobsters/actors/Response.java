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
	
	public Response(boolean yesno, String message){
		this.yesno = yesno;
		this.message = message;
	}
	
	public boolean getYesNo(){
		return this.yesno;
	}
	public String getMessage(){
		return this.message;
	}
}
