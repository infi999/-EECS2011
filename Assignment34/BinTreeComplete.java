/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 3, Problem 3: BinTreeComplete.java
 * Student Name:  Yang Wang
 * Student cse account:  infi999	
 * Student ID number:  213894167 
 **********************************************************/
package A3sol;

import java.util.ArrayList;
import java.util.List;

public class BinTreeComplete {

	private static class Entry {
		double x;
		double y;

		Entry(double x_val, double y_val) {
			x = x_val;
			y = y_val;
		}
	}

	private Entry[] arrayTree;

	public BinTreeComplete(int n, List<Entry> entryList) {
		arrayTree = new Entry[2 * n - 1];
		for (int i = n - 1; i < 2 * n - 1; i++) {
			arrayTree[i] = entryList.get(i - (n - 1));
		}
	}

	protected void downheap(int j) {
		while (hasLeft(j) || hasRight(j)) { // continue to bottom (or break
											// statement)
			if (arrayTree[j] != null) {
				int largeChildIndex = j;
				if (hasLeft(j)) {
					int leftIndex = left(j);
					largeChildIndex = leftIndex; // although right may be
													// smaller
				}
				if (hasRight(j)) {
					int rightIndex = right(j);
					if (arrayTree[rightIndex].y > arrayTree[largeChildIndex].y)
						largeChildIndex = rightIndex; // right child is smaller
				}
				if (arrayTree[j] != null
						&& arrayTree[largeChildIndex].y < arrayTree[j].y)
					break; // heap property has been restored
				swap(j, largeChildIndex);
				j = largeChildIndex; // continue at position
			} else {
				int largeChildIndex;
				if (hasLeft(j) && hasRight(j)) {
					int leftIndex = left(j);
					largeChildIndex = leftIndex; // although right may be
													// smaller
					int rightIndex = right(j);
					if (arrayTree[rightIndex].y > arrayTree[largeChildIndex].y)
						largeChildIndex = rightIndex; // right child is smaller
				} else if (hasLeft(j)) {
					int leftIndex = left(j);
					largeChildIndex = leftIndex; // although right may be
													// smaller
				} else {
					int rightIndex = right(j);
					largeChildIndex = rightIndex;
				}
				swap(j, largeChildIndex);
				j = largeChildIndex; // continue at position
			}

		}
	}

	protected void heapify(int n) {
		int startIndex = n - 2; // start at PARENT of last entry
		for (int j = startIndex; j >= 0; j--)
			// loop until processing the root
			downheap(j);
	}

	protected void swap(int i, int j) {
		Entry temp = arrayTree[i];
		arrayTree[i] = arrayTree[j];
		arrayTree[j] = temp;
	}

	protected int parent(int j) {
		return (j - 1) / 2;
	} // truncating division

	protected int left(int j) {

		return 2 * j + 1;
	}

	protected int right(int j) {
		return 2 * j + 2;
	}

	protected boolean hasLeft(int j) {
		if (left(j) >= arrayTree.length)
			return false;
		else
			return arrayTree[left(j)] != null;
	}

	protected boolean hasRight(int j) {
		if (right(j) >= arrayTree.length)
			return false;
		else
			return arrayTree[right(j)] != null;
	}
	
	/**
	 * main() runs some test cases, the y value are all random numbers
	 * @param args
	 */    
	public static void main(String[] args) {

		int n = 8;
		List<Entry> entryList = new ArrayList<Entry>();
		for (int i = 0; i < n; i++) {
			Entry entry = new Entry(i, 10 * Math.random());
			entryList.add(entry);
			System.out.println(entry.x);
			System.out.println(entry.y);
		}

		BinTreeComplete binTreeComplete = new BinTreeComplete(n, entryList);

		binTreeComplete.heapify(n);

		for (int i = 0; i < 2 * n - 1; i++) {
			if (binTreeComplete.arrayTree[i] == null)
				System.out.println("null");
			else {
				System.out.println(binTreeComplete.arrayTree[i].x);
				System.out.println(binTreeComplete.arrayTree[i].y);
			}
		}

	}
}

