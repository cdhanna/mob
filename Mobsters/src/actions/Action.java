package actions;

public abstract class Action implements Comparable<Action> {
	
	int priority;
	double feelVal;
	
	public void doIt(){
	
	}
	
	public double getFeelVal(){
		return feelVal;
	}

	
}
