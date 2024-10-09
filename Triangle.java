import java.util.ArrayList;

/*
* v1.24f from 21/09/2024 @1200
* changes from v1.1.01
*   re-do switch as HKCC lab BlueJ do not support switch expression
*       solved by re-coded into traditional switch statement
* changes from v1.21
*       improve wordings
*       setTriangleSide: added checking > 0 for input validity checking
* changes from v1.22
*       getTriangleSide: added method
* changes from v1.23
*       reverse logic in checkTriangleSidepossible for a better understanding
*       fixing bug
* changes from v1.24:
*       update calling static variable syntax in class TriangleChecker_3827A
*       v1.24f: better format
*/

/*
* Class Triangle:
* 
* Method list:
*   constructor Triangle
*       create a integer array sideList
*       no parameter needed
*   
*   void setTriangleSide
*       take in value of side and index of side
*       check and set the triangle side accordingly
*   
*   int getTriangleSide
*       take in triangles' index
*       return the value of the index side or error code based on situations
*  
*   boolean checkTriangleSidepossible
*       takes in two integer, tmpSide and i (index)
*       perform a check to see the triangle can be possible formed
*       return true or return false based on the side fullfil the requirement of
*           Triangle Inequality Theorem
* 
*   boolean checkTriangleSideLegal
*       takes in a integer side (triangle side)
*       return true if side > 0, else return false

* 
*   String triangleCheck
*       check triangle type.
*       All possible cases:
*           Equilateral Triangle
*           Right-angled Triangle
*           Isosceles Triangle
*           Scalene Triangle
*           Not a Triangle
* 
*   int printAllSide
*       print out all existed side of the triangle objectword
*/

public class Triangle{
    private final int sideList[]; // instance variable to store the side

    // constructor
    public Triangle () {
        this.sideList = new int[TriangleChecker_3827A.MAXINPUT]; // init: all value default equal to 0
    } // end of constructor

    // set Method
    // store triangle side
    public void setTriangleSide ( int side, int i ) {          
            this.sideList[i] = side;
    } // end Triangle setTriangleSide

    // get Method
    public int getTriangleSide ( int indexSide ) {
        if ( indexSide >= TriangleChecker_3827A.MAXINPUT ) {
            // error: index out of range
            return -101;
        }

        if ( this.sideList[indexSide] == 0 ) {
            // error: sideList have no input data
            return -102;
        }
        
        return this.sideList[indexSide];
    } // end Triangle getTriangleSide

    // check validity of inputted triangle side
    public boolean checkTriangleSidePossible ( int tmpSide, int i ) {
        for (int x = 0; x < TriangleChecker_3827A.MAXINPUT - 1; x++) {
            if (this.sideList[x] <= 0) {
                System.out.printf("\nError: Triangle not complete.");
                return false;
            }
        }

        // Triangle Inequality Theorem
        // true if all side can form a triangle, vice versa
        boolean condition = (this.sideList[0] + this.sideList[1] > tmpSide &&  
                                this.sideList[1] + tmpSide > this.sideList[0] && 
                                this.sideList[0] + tmpSide > this.sideList[1] );
        return condition;     
    } // end Triangle checkTriangleSide

    // check trianlges' side is possitive number or not
    public boolean checkTriangleSideLegal ( int side ) {
        if ( side <= 0 ) {
            System.out.println("\nError: side cannot be 0 or negative.");
            return false;
        }
        return true;
    } // end Triangle checkTriangleSideLegal

    // Methods
    // return: the string of words contants the result of the triangles' type
    public String triangleCheck () {
        ArrayList<String> triangleList = new ArrayList<>();

        for (int side : this.sideList) {
            if (side <= 0) {
                return "Error: Triangle not complete.";
            }
        }

        // categorize triangle
        if ( (this.sideList[0] == this.sideList[1]) && (this.sideList[1] == this.sideList[2]) ) {
            triangleList.add("Equilateral Triangle");
        }
        
        if ( (this.sideList[0] * this.sideList[0] + this.sideList[1] * this.sideList[1] == this.sideList[2] * this.sideList[2]) || 
             (this.sideList[0] * this.sideList[0] + this.sideList[2] * this.sideList[2] == this.sideList[1] * this.sideList[1]) ||
             (this.sideList[1] * this.sideList[1] + this.sideList[2] * this.sideList[2] == this.sideList[0] * this.sideList[0]) ) { // pyth. theorem
            triangleList.add("Right-angled Triangle");
        }

        if ( (((this.sideList[0] == this.sideList[1]) && (this.sideList[1] == this.sideList[2])) == false) && 
            ((this.sideList[0] == this.sideList[1] && this.sideList[0] + this.sideList[1] > this.sideList[2]) || 
             (this.sideList[1] == this.sideList[2] && this.sideList[1] + this.sideList[2] > this.sideList[0] ) || 
             (this.sideList[0] == this.sideList[2] && this.sideList[0] + this.sideList[2] > this.sideList[1])) ) {
            triangleList.add("Isosceles Triangle");
        }

        if (this.sideList[0] != this.sideList[1] && this.sideList[1] != this.sideList[2] && this.sideList[0] != this.sideList[2]) {
            triangleList.add("Scalene Triangle");
        }
        
        // formatting String for output
        String finalSentence; 
        switch (triangleList.size()) {
            case 4:
                finalSentence = String.format("\n%s and %s and %s and %s", triangleList.get(0), triangleList.get(1), triangleList.get(2), triangleList.get(3));
                break;
            case 3:
                finalSentence = String.format("\n%s and %s and %s", triangleList.get(0), triangleList.get(1), triangleList.get(2));
                break;
            case 2:
                finalSentence = String.format("\n%s and %s", triangleList.get(0), triangleList.get(1));
                break;
            case 1:
                finalSentence = String.format("\n%s", triangleList.get(0));
                break;
            default:
                finalSentence = "\nThat's not a triangle!";
                break;
        } // end switch

        return finalSentence;
    } // end Triangle triangleCheck

    // print out all sideList items
    public int printAllSide () {
        for (int x = 0, n = this.sideList.length; x < n; x++) {
            System.out.printf("item %d: %d\n", x, this.sideList[x]);
        }
        return 0;
    } // end Triangle printList
} // end class Triangle