/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 3: Window.java
 * Student Name:  Yang Wang
 * Student cse account:  infi999		
 * Student ID number:  213894167
 **********************************************************/


package A1;

/**
 * The purpose of this class is to distinguish two windows overlap or enclose and count the overlap and enclose pairs.
 * 
 * The main method runs some tests.
 * 
 * @author Yang Wang
 * 
 */

public class Window {

protected double left;
protected double right;
protected double bottom;
protected double top;

/**
 * Constructor with 4 parameters
 * @param left
 * @param right
 * @param bottom
 * @param top
 * @throws InvalidWindowException if left > right or bottom > top
 */
public Window(double left, double right, double bottom, double top) throws InvalidWindowException{
// report error if left > right or bottom > top
	if(left > right || bottom > top){
  throw new InvalidWindowException("invalid window!");
}

this.left = left;
this.right = right;
this.bottom = bottom;
this.top = top;
}

/**
 * 
 * @return left
 */
public double getLeft() {
return left;
}

/**
 * 
 * @param left
 * @throws InvalidWindowException if left > right
 */
public void setLeft(double left) throws InvalidWindowException{
if (left > right){
  throw new InvalidWindowException("invalid window!");
}

this.left = left;
}

/**
 * 
 * @return right
 */
public double getRight(){
return right;
}

/**
 * 
 * @param right
 * @throws InvalidWindowException if right < left
 */
public void setRight(double right) throws InvalidWindowException {
if (right < left){
  throw new InvalidWindowException("invalid window!");
}

this.right = right;}


/**
 * 
 * @return bottom
 */
public double getBottom() {
return bottom;
}

/**
 * 
 * @param bottom
 * @throws InvalidWindowException if bottom > top
 */
public void setBottom(double bottom) throws InvalidWindowException {
if (bottom > top){
  throw new InvalidWindowException("invalid window!");
}

this.bottom = bottom;
}

/**
 * 
 * @return top
 */
public double getTop() {
return top;
}

/**
 * 
 * @param top
 * @throws InvalidWindowException if top < bottom
 */
public void setTop(double top) throws InvalidWindowException{
  if ( top < bottom) {
 throw new InvalidWindowException("invalid window!");
  }
this.top = top;
}

/**
 * encloses() distinguish whether one instance is enclose argument w
 * @param w
 * @return true if and only if the instance is enclose argument w
 */
public boolean encloses(Window w){

 if (left <= w.left && right >= w.right && bottom <= w.bottom && top >= w.top){

	 return true;}
 else{

	 return false;
 }
}

/**
 * overlaps() distinguish whether one instance is overlap argument w
 * @param w
 * @return true if and only if the instance is overlap argument w
 */
public boolean overlaps(Window w){
 // that is the only 4 conditions for not overlap and if A encloses B, then A is also overlap B
 if (bottom >= w.top || left >= w.right || right <= w.left || top <= w.bottom){
	 return false;
 }

    return true;
}

/**
 * overlapCount() compare the nearest two instance A and B, if A overlaps B, count++
 * @param windows - the input array
 * @return count
 * @throws InvalidWindowException
 */
public static int overlapCount(Window[] windows) throws InvalidWindowException {
int count = 0;
for ( int i = 0; i < windows.length; i++){
// when i = 0, windowA equal to first window and compare to second window(j=1) and so on
Window windowA = new Window (windows[i].left, windows[i].right, windows[i].bottom, windows[i].top );
for ( int j = i + 1; j < windows.length; j++){
Window windowB = new Window (windows[j].left, windows[j].right, windows[j].bottom, windows[j].top );
   Boolean isOverlaps = false;
// it's count unordered pair, so A overlaps B is the same as B overlaps A   
   isOverlaps = windowA.overlaps(windowB);
   if(isOverlaps == true){
    count++;
   }
}
}
return count;
}

/**
 * enclosureCount() compare the nearest two instance A and B each other, if A enclose B or B enclose A, count++; if A and B enclose each other, count = count + 2
 * @param windows - the input array
 * @return count
 * @throws InvalidWindowException
 */
public static int enclosureCount(Window[] windows) throws InvalidWindowException {
int count = 0;
for ( int i = 0; i < windows.length; i++){
// when i = 0, windowA equal to first window and compare to second window(j=1) and so on
Window windowA = new Window (windows[i].left, windows[i].right, windows[i].bottom, windows[i].top );
for ( int j = i + 1; j < windows.length; j++){
Window windowB = new Window (windows[j].left, windows[j].right, windows[j].bottom, windows[j].top );
   Boolean isEnclosed = false;
// it's an ordered pair, so need to check both A encloses B and B encloses A
   isEnclosed = windowA.encloses(windowB) || windowB.encloses(windowA);
// special case, if A enclose B each other, for ordered pair, need count twice   
   if(isEnclosed == true && (windowA.encloses(windowB)) == (windowB.encloses(windowA))){
    count = count + 2;
   }else if (isEnclosed == true){
	   count++;
   }
}
}
return count;
}


/**
 * main() runs test cases on encloses(), overlaps(), overlapCount() and enclosureCount() methods. Prints result
 * @param args
 * @throws InvalidWindowException
 */
public static void main(String[] args) throws InvalidWindowException {

	  //Window bad = new Window(6,5,4,9);  6 > 5 bad example, throw exception
	  // test case 1
      Window w1 = new Window(0, 5, 4, 9); 
      Window w  = new Window(1, 4, 5, 8);
      System.out.println(" Did w1 encloses w? : " + w1.encloses(w));
      System.out.println(" Did w encloses w1? : " + w.encloses(w1));
      System.out.println(" Did w1 overlaps w? : " + w1.overlaps(w)); 
      System.out.println(" Did w1 overlaps w? : " + w.overlaps(w1));
      
      // test case 2
      w1.setLeft(2);
      //w1.setLeft(6);  6 > 5 bad example, throw exception
      System.out.println(" Did w1 encloses w now? : " + w1.encloses(w));
      System.out.println(" Did w1 overlaps w now? : " + w1.overlaps(w)); 
      
      // test case 3
      Window w2 = new Window(1, 4, 5, 8);
      Window w3 = new Window(0, 1, 2, 5);
      System.out.println(" Did w2 encloses w3? : " + w2.encloses(w3));
      System.out.println(" Did w2 encloses w3? : " + w2.encloses(w3));
      System.out.println(" Did w3 overlaps w2? : " + w3.overlaps(w2));
      System.out.println(" Did w3 overlaps w2? : " + w3.overlaps(w2));
     
      // test case 4
      Window windowA = new Window(0, 5, 4, 9);
      Window windowB = new Window(1, 4, 5, 8);
      Window windowC = new Window(0, 5, 4, 9); 
      Window windowD = new Window(2, 4, 6, 8); 
      Window [] windows = {windowA,windowB,windowC,windowD};
      
     System.out.println( " number of enclosure pairs : " + Window.enclosureCount(windows));
     System.out.println( " number of overlap pairs : " + Window.overlapCount(windows));
      

}}
