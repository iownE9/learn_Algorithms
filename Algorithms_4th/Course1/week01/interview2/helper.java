import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 helper.java
 *  Execution:    java-algs4 helper > SearchBitonicArray.txt
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class helper {
    public static void main(String[] args) {
        int i = 0;
        for (; i < 999; i += 2) {
            StdOut.println(i);
        }
        for (i = i + 1; 0 < i; i -= 2) {
            StdOut.println(i);
        }
    }
}
