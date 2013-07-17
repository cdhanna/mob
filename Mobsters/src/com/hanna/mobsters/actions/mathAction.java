package com.hanna.mobsters.actions;

public class MathAction extends Action{
	double a,b;
	String op;
	
	public MathAction(Double a, Double b,String op, Integer priority) {
		this.a = a;
		this.b = b;
		this.op = op;
		this.priority = priority;
		if (op == "-")
			feelVal = -0.4;
		else
			feelVal = 1.0;
			
		}
	
	public String doIt(){
		String str;
		
		switch (op.charAt(0)){
		case '+': str = "I added " +a+ " and " +b+ " and got " + (a+b);
		break;
		case '-': str = "I subtracted " +b+ " from " +a+ " and got " + (a-b);
		break;
		case '*': str = "I multiplied " +a+ " and " +b+ " and got " + (a*b);
		break;
		case '/':
		if (b!=0){
			str = "I divided " +a+ " by " +b+ " and got " + (a/b);}
		else
			str = " I cannot divide " +a+ "by zero boss";
		break;
		default: str = "Unkown operation " +op;
		break;
		}
		
		return str;
			
	}
	
	@Override
	public String toString(){
		return "OPERATION IS " +a+op+b +", PRIORITY IS " + priority + " FEELING IS " + feelVal;
	}

	@Override
	public int compareTo(Action arg0) {
		return arg0.priority - this.priority;
	}

}
