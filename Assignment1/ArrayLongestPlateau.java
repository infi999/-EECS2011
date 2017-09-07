/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 2: ArrayLongestPlateau.java
 * Student Name:  Yang Wang
 * Student cse account:  infi999	
 * Student ID number:  213894167 
 **********************************************************/

package A1;

/**
 * The purpose of this class is to find the longest plateau of an array of ints. 
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArrayLongestPlateau {

  	/**
	 * longestPlateau() returns the longest plateau of an array of ints.
	 * 
	 * @return an array int[3] of the form {value, start, len} representing the longest plateau of
	 *         ints[] as a length len contiguous subarray starting at index start with common
	 *         element values value.
	 * 
	 *         For example, on the input array [2, 3, 3, 3, 3, 6, 6, 1, 1, 1], it returns [6, 5, 2],
	 *         indicating the longest plateau of this array is the subarray [6, 6]; it starts at
	 *         index 5 and has length 2.
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static int[] longestPlateau(int[] ints) {

		// TODO: Replace the following 2-line stub with your solution. Ours takes linear time and is
		// 24 lines long, not counting blank/comment lines or lines already present in this file.
		    


		int length = 0, max = 0, value = 0, index = 0;
     	int compare = ints[0];
     	
     	if (ints.length == 1){                       //special case: only 1 element. like: [4]
     		max = 1;
     		value = ints[0];
     		index = 0;
     	}else{

		
		for (int l = 1; l < ints.length-1; l++){
			
		if (ints[l] > compare){
		compare = ints[l];
		length = 1;
		}
		// for case 3312 maybe 33 is the longest plateau, need to keep it !
		else if (ints[0] == ints[1] && length == 0 && max == 0){
		compare = ints[1];                       
		length = 2;}	
		// to make sure the first element of longest plateau greater than the one in front of it
		else if (ints[l] == compare && length > 0){
		compare = ints[l];
		length = length + 1;
		}
		//if the element smaller than the one in front of it, then it is not longest plateau
		else if (ints[l] < compare){
		compare = ints[l];
		length = 0;
		}
		
		if(length > max && ints[l] >= ints[l+1]){
			max = length;                        
			value = ints[l];
	    // this value is the last element in longest plateau, so the index would be the last value's index - the length of plateau + 1 
			index = l - max + 1;
		
			}else if(length > max && ints[l] < ints[l+1]){
				max = 1;                           // for case 33334 3<4 so 3333 is not longest plateau 
			}
		
		if (max == 1 && ints[l] < ints[l+1]){
		
			value = ints[l+1]; index = l + 1;      // for case 123456
		}
		
		if (max == 0 && l == ints.length - 2){
			value = ints[0]; index = 0;max = 1;    // for case 654321
		}
		
		if (ints[ints.length - 1] == compare && length == max){
			max = max + 1;                         // for case like test 3 which the loop cannot reach the last element
            value = ints[l];
            index = l - max + 2;
          
	
		}}}

		
		int[] result = { value, index, max };
		return result; 
		}

  	/**
	 * main() runs test cases on your longestPlateau() method. Prints summary
	 * information on basic operations and halts with an error (and a stack
	 * trace) if any of the tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's find longest plateaus of arrays!\n");

		 int[] test1 = { 4, 1, 1, 6, 6, 6, 6, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test1) + ":");
		result = TestHelper.stringInts(longestPlateau(test1));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 3 , 4 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test2 = { 3, 3, 1, 2, 4, 2, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test2) + ":");
		result = TestHelper.stringInts(longestPlateau(test2));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals(
		  "[ 3 , 0 , 2 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");  
		
		int[] test3 = { 3, 3, 1, 2, 4, 0, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test3) + ":");
		result = TestHelper.stringInts(longestPlateau(test3));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 1 , 6 , 4 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test4 = { 3, 3, 3, 4, 1, 2, 4, 4, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test4) + ":");
		result = TestHelper.stringInts(longestPlateau(test4));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 6 , 2 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test5 = { 7, 7, 7, 7, 9, 8, 2, 5, 5, 5, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test5)
				+ ":");
		result = TestHelper.stringInts(longestPlateau(test5));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 7 , 3 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test6 = { 4 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test6) + ":");
		result = TestHelper.stringInts(longestPlateau(test6));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 0 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test7 = { 4, 4, 4, 5, 5, 5, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test7) + ":");
		result = TestHelper.stringInts(longestPlateau(test7));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 2 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.
		int[] test8 = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test8) + ":");
		result = TestHelper.stringInts(longestPlateau(test8));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 7 , 6 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate."); 
		
		int[] test9 = { 6,5,4,3,2,2 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test9) + ":");
		result = TestHelper.stringInts(longestPlateau(test9));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 0 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
	}
}