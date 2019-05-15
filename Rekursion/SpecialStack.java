import java.util.ArrayList; 

public class SpecialStack {
	public ArrayList<Integer> stack = new ArrayList<Integer>(); 

	SpecialStack() {

	}

	SpecialStack(int n) {
		for(int i=n; i>0; i--) {
			stack.add(i);
		}
	}

	public void push(int x) {
		if (stack.isEmpty()) stack.add(x);
		else {
			if (x < stack.get(stack.size()-1)) {
				stack.add(x);	
			} else throw new RuntimeException ("The number cannot be pushed");	
		}
	}

	public int pop() {
		if (!stack.isEmpty()) {
			return stack.remove(stack.size()-1); 
		} else throw new RuntimeException ("The stack is empty");
	}

	public String toString() {
		String s = new String(); 

		for (int i=stack.size()-1; i>=0; i--) {
			if (i == stack.size()-1) {
				s = "[ " + stack.get(stack.size()-1) + " ]\n";  
			} else if (i == 0) {
				s = s + "[ " + stack.get(i) + " ]"; 
			} else {
				s = s + "[ " + stack.get(i) + " ]" + "\n";  
			}
		}
		return s; 
	}
}