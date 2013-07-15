package actions;

public abstract class Action implements Comparable<Action> {
	
	int priority; // must have a priority.
	double feelVal; // must have a feeling value (set of them eventually)
	
	// all actions must have a method that describes how to complete the action
	public void doIt(){
	
	}
	
	// the decider method in the actor class needs these parameters for decision making
	public double getFeelVal(){
		return feelVal;
	}

	
}
