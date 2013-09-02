package com.hanna.mobsters.histories;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionResult;
import com.hanna.mobsters.actions.impl.NoMutateAction;
import com.hanna.mobsters.actors.Decision;
import com.hanna.mobsters.actors.Response;

/**
 * A wrapper class for containing all relevant information about an action being requested, responded to,
 * mutated, and completed.
 * @author Will
 *
 */
public class GameEvent {

	private Action originalAction;
	private Action mutatedAction;
	private Response response;
	private ActionResult result;
	
	public GameEvent(){
		
		
	}
	public GameEvent(Action originalAction) {
		this.originalAction = originalAction;
		Decision d = new Decision();
		this.response = new Response(false,"No response given.",d);
		this.mutatedAction = new NoMutateAction();
		this.result = new ActionResult();
	}
	
	public void setResponse(Response response){
		this.response = response;
	}
	
	public void setMutation(Action mutatedAction){
		if (mutatedAction != null)
			this.mutatedAction = mutatedAction;
		//TODO make the mutated Action some sort of default 'blank' action
	}
	
	public void setResult(ActionResult result){
		this.result = result;
	}
	
	@Override
	public String toString(){
		return   "ORIGINAL ORDER: " + this.originalAction.toString() + ". " +
				 "\nRESPONSE: " + this.response.getMessage() + ". " +
				 "\nMUTATED ACTION: " + this.mutatedAction.toString() + ". " + 
				 "\nRESULT: " + this.result.toString();
		
	}

}
