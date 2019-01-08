/**
 * @author Ethan Xiong
 * Class: ICS 240
 * Professor Thanaa Ghanem
 * Date: 11/2/18
 *
 *StackDriver.java
 *This java file tests all listed methods in Assignment 5: Exercises on the Stack Data Structure
 *The main application of this java class is to work with input parameters of type IntStackInterface
 */



public class StackDriver {

	public static void main(String[] args) {
		
		int output = 0;
		IntArrayStack s = new IntArrayStack(7); 
		
		s.push(9);
		s.push(1);
		s.push(8);
		s.push(2);
		s.push(7);
		s.push(3);
		s.push(6);

		//test stackToInt method
		output = stackToInt(s);
		System.out.println("stackToInt method prints the stack s: ");
		System.out.println(output);
		
		//test flip method
		IntArrayStack s2 = new IntArrayStack(7); 
		s2 = (IntArrayStack) flip(s);
		System.out.println(" ");
		System.out.println("flip method prints the stack s in reverse: ");
		while(!s2.isEmpty())
			System.out.print(s2.pop());
		
		//test popBottom method
		int bottom = 0;
		bottom = popBottom(s);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("popBottom method prints bottom Value of the stack s: ");
		System.out.println(bottom);
		
		//test popSome method
		int popSomeOutput;
		popSomeOutput = popSome(s, 3);
		System.out.println(" ");
		System.out.println("popSome method removes 4 numbers from stack s and adds them: ");
		System.out.println("popSome total: " + popSomeOutput);
		System.out.println("The rest of the numbers in stack s after popSome: ");
		while(!s.isEmpty())
			System.out.print(s.pop());
		
		System.out.println(" ");
		popSomeOutput = popSome(s, 6);
		System.out.println("When count for popSomeOutput is bigger than the stack, output should be -1: ");
		System.out.println("popSome output when count = 6 and stack size is 3: " + popSomeOutput);
		
		//refill stack with original numbers
		s.push(9);
		s.push(1);
		s.push(8);
		s.push(2);
		s.push(7);
		s.push(3);
		s.push(6);
		output = stackToInt(s);
		System.out.println(" ");
		System.out.println("refill stack s with its original numbers ");
		System.out.println(output);
		
		//test extractFromStack
		extractFromStack(s, 8);
		output = stackToInt(s);
		System.out.println(" ");
		System.out.println("extractFromStack method removes 8 from the stack s ");
		System.out.println(output);
		
		//test equalStacks
		IntArrayStack s3 = new IntArrayStack(7); 
		s3.push(9);
		s3.push(1);
		s3.push(2);
		s3.push(7);
		s3.push(3);
		s3.push(6);
		boolean same = false;
		same = equalStacks(s, s3);
		System.out.println(" ");
		System.out.println("stack s3 is made to be an equal stack to s");
		output = stackToInt(s);
		System.out.println("output of stack s ");
		System.out.println(output);
		output = stackToInt(s3);
		System.out.println("output of stack s3 ");
		System.out.println(output);
		System.out.println("test if stack s is equal to stack s3");
		System.out.println(same);
		output = stackToInt(s2);
		System.out.println("output of stack s2 ");
		System.out.println(output);
		same = equalStacks(s, s2);
		System.out.println("test if stack s is equal to stack s2");
		System.out.println(same);
	}

	/**
	 * method stackToInt: takes an int stack and outputs an integer that shows stack with most significant bit first. 
	 * Algorithm: takes a popped number from the stack, multiples the current result by 10, then adds 
	 * the stacked single digit to recreate the numbers in the stack. A tempstack is used to re-populate the 
	 * original stack
	 * @param s
	 * @return
	 */
	public static int stackToInt(IntStackInterface s) {

		int output = 0;
    	IntStackInterface tempStack = new IntArrayStack(7);
    	
    	while (!s.isEmpty()){
    		int tempInt = s.pop();
    			output *= 10;
    			output += tempInt;
    		tempStack.push(tempInt);
    		
    	}
    	//returning all the values to the original stack again
    	while (!tempStack.isEmpty()){
    		int tempInt = tempStack.pop();
    		s.push(tempInt);
    	}
		return output;
	}
	
	/**
	 * method flip: takes an int stack and flips the stack into another stack which is output. 
	 * Algorithm: create two stacks in the method. One will be popped to for output. The other will be used 
	 * as a temp stack to return the original stack as is.
	 * @param s
	 * @return
	 */
	public static IntStackInterface flip (IntStackInterface s) {
		
    	IntStackInterface tempStack = new IntArrayStack(7);
    	IntStackInterface outputStack = new IntArrayStack(7);
    	
    	while (!s.isEmpty()){
    		int tempInt = s.pop();
    		tempStack.push(tempInt);
    		outputStack.push(tempInt);
    	}
    	
    	//returning all the values to the original stack again
    	while (!tempStack.isEmpty()){
    		int tempInt = tempStack.pop();
    		s.push(tempInt);
    	}
    	return outputStack;	
	}
	
	/**
	 * method popBottom: takes an int stack and pops the bottom of the stack out as input. 
	 * Algorithm: Check if stack size is = 1, if so, output the next pop as output
	 * Everything else in the stack should remain the same.
	 * @param s
	 * @return
	 */
	public static int popBottom (IntStackInterface s) {
		
		IntStackInterface tempStack = new IntArrayStack(7);
		int output = 0;
		
		while (!s.isEmpty()){
			if(s.size() == 1) {
				output = s.pop();
				break;
			}
    		int tempInt = s.pop();
    		tempStack.push(tempInt);
		}
		
    	//returning all the values to the original stack again
    	while (!tempStack.isEmpty()){
    		int tempInt = tempStack.pop();
    		s.push(tempInt);
    	}
		
		return output;
	}
	
	/**
	 * method popSome: takes an int array stack and int count. Pop count values from the stack. 
	 * Algorithm: If stack<count, then return -1. Otherwise, use a for loop to pop as many numbers = count.
	 * @param s
	 * @param count
	 * @return
	 */
	public static int popSome (IntStackInterface s, int count) {
		
		int output = 0;
		
		if(s.size() < count)
			return -1;
		
		for(int i = 0; i < count; i++) {
			output += s.pop();
		}
		
		return output;
	}
	
	/**
	 * method extractFromStack: takes an int array stack and int value, all values found in stack are removed. 
	 * Algorithm: if value is equal to popped value, remove it from stack. Rest of the stack remains unchanged.
	 * @param s
	 * @param value
	 */
	public static void extractFromStack (IntStackInterface s, int value) {
		
		IntStackInterface tempStack = new IntArrayStack(7);
		
		while (!s.isEmpty()){
    		int tempInt = s.pop();
    		tempStack.push(tempInt);
    		if(value == tempInt)
    			tempStack.pop();
		}
		
    	//returning all the values to the original stack again
    	while (!tempStack.isEmpty()){
    		int tempInt = tempStack.pop();
    		s.push(tempInt);
    	}
	}
	
	/**
	 * method equalStacks: takes two int array  stacks as input. 
	 * Algorithm: If all popped values in both stacks are equal, return true. For any other case, return false. 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equalStacks (IntStackInterface s1, IntStackInterface s2) {
		
		IntStackInterface tempStack1 = new IntArrayStack(7);
		IntStackInterface tempStack2 = new IntArrayStack(7);
		
		if (s1.size() != s2.size())
			return false;
		
		while (!s1.isEmpty()){
    		int tempInt1 = s1.pop();
    		int tempInt2 = s2.pop();
    		tempStack1.push(tempInt1);
    		tempStack2.push(tempInt2);
    		if(tempInt1 != tempInt2)
    			return false;
		}
		
    	//returning all the values to the first stack 
    	while (!tempStack1.isEmpty()){
    		int tempInt = tempStack1.pop();
    		s1.push(tempInt);
    	}
    	
    	//returning all the values to the second stack 
    	while (!tempStack2.isEmpty()){
    		int tempInt = tempStack2.pop();
    		s2.push(tempInt);
    	}
		
		return true;
	}
	
}
