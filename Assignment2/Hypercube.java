/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 2: Hypercube.java
 * Student Name:  Yang Wang
 * Student cse account:  infi999	
 * Student ID number:  213894167 
 **********************************************************/
package A2sol;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The purpose of this class is using recursion and iteration two ways to finish a walk on the hypercube. The walk starts
 * at some corner and visits each corner just once and print the path.
 * 
 * The main method runs some tests.
 * 
 * @author Yang
 * 
 */
public class Hypercube {
	/**
	 * recursiveWalk() is used recursive method to save 2^n corners in string corners
	 * @param n
	 * @return corners
	 */
	private static String[] recursiveWalk(int n){  
		// For any given n, it should has 2^n corners, build a String to save those corners
	    String[] corners = new String[2 << (n-1)];  
	  
	    //The last one is either 0 or 1
	    if (n == 1) {  
	        corners[0] = "0";  
	        corners[1] = "1";  
	        return corners;  
	    }  
	  
	    //Call recursive method to get n-1
	    String[] last = recursiveWalk(n - 1);  
	  
	    for (int i = 0; i < last.length; i++) {  
	    	//the value of 0 + n - 1
	        corners[i] = "0" + last[i];  
	        //the value of 1 + n - 1 and the index is n - 1 - i is to keep the symmetry
	        corners[corners.length - 1 - i] = "1" + last[i];  
	    }  
	  
	    return corners;  
	} 
	
	
	/**
	 * iterativeWalk() is used to save 2^n corners into queue
	 * @param n
	 * @return queue
	 */
	public static Queue<Corner> iterativeWalk(int n) {  
		
		Queue<Corner> queue = new LinkedList<Corner>();
        for (int i = 0; i < 2 << (n-1); i++) {  
        	//this function I will discuss it on report
            int code = (i >> 1) ^ i; 
            Corner corner = new Hypercube().new Corner();
            corner.corner = num2Binary(code, n);
            queue.add(corner);  
        }  
        return queue;
    }  
	/**
	 * num2Binary() is used to convert decimal number to binary
	 * @param num
	 * @param bitNum
	 * @return
	 */
    public static String num2Binary(int code, int n) {  
        String ret = "";  
        //convert the decimal number to binary
        for (int i = n - 1; i >= 0; i--) {  
            // loop once get one bit binary number
        	ret += (code >> i) & 1;  
        }  
        return ret;  
    }  
	
	/**
	 * main() calls two kinds of functions to run some test cases
	 * @param args
	 */
	public static void main(String[] args) {  
		//
	    String[] corners = recursiveWalk(3);  
	    System.out.println("A Walk:");
	    for (int i = 0; i < corners.length; i ++) {
	    	System.out.println("  "+corners[i]);
	    } 
	    
	    
	    Queue<Corner> queue = iterativeWalk(2);  
	    System.out.println("A Walk:");
	    for (Corner q: queue) {
	    	System.out.println("  "+q.corner);
	    }
	} 
	
    //nested class corner
	public class Corner {
		private String corner;
	}

}


// the running times of iterativeWalk is one, while the runnint times of recursiveWalk is n， which is from n to 1.
