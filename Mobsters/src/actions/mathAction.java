package actions;

public class mathAction extends Action{
	double a,b;
	char op;
	
	public mathAction(double a, double b,char op, int priority) {
		this.a = a;
		this.b = b;
		this.op = op;
		this.priority = priority;
			
		}
	
	public void doIt(){
		double result;
		
		switch (op){
		case '+': result = a + b; break;
		case '-': result = a - b; break;
		case '*': result = a * b; break;
		case '/':
		if (b!=0)
			result = a/b;
		else
			result = 0; // not right
		break;
		default: result = 0; break;
		}
		
		System.out.println("I performed the operation. The result was " + result);
			
	}

	@Override
	public int compareTo(Action arg0) {
		// TODO Auto-generated method stub
		return arg0.priority - this.priority;
	}

}
