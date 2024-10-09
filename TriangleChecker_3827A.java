import java.util.Scanner;
// import Triangle.java;

/*
* v1.41f form 21/09/2024 @1200
* - purpose
*   - Different way to solve OOPIA: in a object way
*       - get 3 integer input, check the type of triangle inputted
*   - do not let user input a invalid triangle until all side can form a triangle
*   - will reset inputted side if reached maximum number of tries.
*
* - refines
*   - better syntax (following the java codeconventions-150003.pdf)
* 
* changes from v1.3
*   - added return key check with Integer.parseInt()
*
* changes from v1.4
*   - alter logic in inputting: do miss-input counting in all attempts of inputting
*   - do better commenting
*   - v1.41: update calling static variable syntax
*   - v1.41f: better format
*/

public class TriangleChecker_3827A {

    public static final int MAXINPUT = 3; // Question requirement
    public static final int MAXTRY = 3; // Customizable maximum number of attempts

    public static void main (String argv[]) {

        Triangle triangle = new Triangle();
        char sideList [] = {'A', 'B', 'C'};
        
        // Display prompt
        System.out.println("TriangleChecker_3827A");
        System.out.println("Please enter your triangle's side (integer) to check");

        // get user input
        int i = 0;
        int misInputCounter = 0;
        int tmp = 0;
        Scanner input = new Scanner(System.in);

        while (i < TriangleChecker_3827A.MAXINPUT) {
            System.out.printf("Side %c: ", sideList[i]);
            boolean next = true;

            try {
                String userInput = input.nextLine();
                tmp = Integer.parseInt(userInput);

            } catch (Exception e) { // catch datetype error (NumberFormatException): re-input 
                System.out.println("Error: wrong data type. Only accept **integer**.\n");
                misInputCounter++;
                next = false;
            } // end of catch

            if (i == 2) {
                // check if the triangle can be form
                if (triangle.checkTriangleSidePossible (tmp, i) == false) {
                    System.out.println("Error: input cannot form a triangle.\n");

                    misInputCounter++;
                    next = false;
                }
            } else if (next) {
                // fail to store side into the triangle: side value <= 0
                if (triangle.checkTriangleSideLegal(tmp) == false) {
                    misInputCounter++;
                    next = false; // continue;
                }
            }

            // reset all input values
            // when reaches the maximum number of attempts
            if (misInputCounter >= TriangleChecker_3827A.MAXTRY) {
                System.out.println("You have reached maximus number of attempts.");
                System.out.println("Please re-input once more from the beginning.");
                
                // reset all indexs and counters
                i = 0;
                misInputCounter = 0;
            }


            if (next){
                // store side into the triangle
                triangle.setTriangleSide(tmp, i);
                i++;
            }
        } // end of while

        input.close(); // prevent memory leak
        
        System.out.printf("%s\n", triangle.triangleCheck());
    } // end TriangleChecker_3827A main

} // end of class TriangleChecker_3827A