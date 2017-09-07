/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 4, Problem 1: MedianFinal.java
 * Student Name:  Yang Wang
 * Student cse account:  infi999	
 * Student ID number:  213894167 
 **********************************************************/
package A4sol;

public class MedianFinal {
	public static void main(String[] args) {
		// set 2 sorted arrays a and b
		int[] a = new int[] { 3, 5, 9, 15, 27, 33, 35, 41, 57, 65 };
		int[] b = new int[] { 2, 16, 18, 42, 44, 46, 48, 50, 52, 54 };

		// find the kth smallest element
		int k = 10;
		System.out.println("The " + k + "th" + " smallest element is " + Search(a, 0, a.length - 1, b, 0, b.length - 1, k));
	}

	public static int Search(int[] a, int startA, int endA, int[] b,
			int startB, int endB, int k) {
		if (k == 1) {
			if (a[startA] <= b[startB]) {
				return a[startA];
			} else {
				return b[startB];
			}
		}

		// if startA>endA, it means A is done, go find smallest element on B
		if (startA > endA) {
			return b[startB + k - 1];
		}
		// if startB>endB, it means B is done, go find smallest element on A
		if (startB > endB) {
			return a[startA + k - 1];
		}

		int al = endA - startA + 1;
		int bl = endB - startB + 1;

		
		if (al > bl) {
			return Search(b, startB, endB, a, startA, endA, k);
		}

		int ms = 0;
		int ns = 0;

		
		if ((endA - startA + 1) < k / 2) {
			ms = endA - startA + 1;
		} else {
			ms = k / 2;
		}

		ns = k - ms;

		int m = ms - 1 + startA;
		int n = ns - 1 + startB;

		if (a[m] == b[n]) {
			return a[m];
		} else if (a[m] > b[n]) {
			return Search(a, startA, m, b, n + 1, endB, k - ns);
		} else {
			return Search(a, m + 1, endA, b, startB, n, k - ms);
		}

	}
}
