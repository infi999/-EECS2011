/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 3: AugmentedStack.java
 * Student Name:  Yang Wang
 * Student cse account:  infi999	
 * Student ID number:  213894167 
 **********************************************************/
package A2sol;

import java.util.Stack;

/**
 * The purpose of this class is build a ADT stack class and set the worst-case time for push pop and getMin to O(1)
 * 
 * The main method runs some tests.
 * 
 * @author Yang
 * 
 */
public class AugmentedStack<E> implements Comparable<E>{

	public Stack<E> stack = new Stack<E>();
	public Stack<E> stackMin = new Stack<E>();
/**
 * push() is used to push element S into stack, if S is smallest, push it to stackMin
 * @param S
 */
@SuppressWarnings("unchecked")
public void push(E S){
	//If stackMin is empty, S is the smallest one, push it into stackMin
	if (stackMin.isEmpty()){
		stackMin.push(S);
	}
	//If S is the current smallest one, push it into stackMin
	else if( ((Comparable<E>) S).compareTo(stackMin.peek()) <= 0){
		stackMin.push(S);
	}
	stack.push(S);
}

/**
 * pop() is used to pop element S out of the stack, if S is smallest, pop it out of stackMin too
 * @return S
 */
public E pop(){
	if (stack.isEmpty()){
		return null;
	}
	E S = stack.pop();
	//If S is the smallest one, pop it out of the stackMin
	if (stackMin.peek() == S){
		stackMin.pop();
	}
	return S;
	
}

/**
 * getMin() is used to return the smallest element
 * @return smallest element
 */
public E getMin(){
	
	if (stackMin.isEmpty()){
		return null;
	}
	else{
		return stackMin.peek();
	}
}

/**
 * top() is used to return the last in element
 * @return top element
 */
public E top(){

if(stack != null){
	return stack.peek();
	}
	return null;
   
}


/**
 * main() runs some test cases
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AugmentedStack <Integer> stack1 = new AugmentedStack <Integer>();
		stack1.push(3);
	    stack1.pop();
	 // To test return null if it's empty in stack   
	System.out.println("The top of stack is: " + stack1.getMin());
	    stack1.push(3);
		stack1.push(5);
		stack1.push(4);
		stack1.push(2);
		stack1.push(1);
	System.out.println("The min of stack is: " + stack1.getMin());
	stack1.pop();
	System.out.println("The min of stack is: " + stack1.getMin());
	stack1.pop();
	System.out.println("The min of stack is: " + stack1.getMin());
	System.out.println("The top of stack is: " + stack1.top());}
/**
 * compareTo() is used to compare two element in generic type
 */
	@Override
	public int compareTo(E o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
