import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 MergeXTest.java
 *  Execution:    java-algs4 MergeXTest < shakespeare.txt > MergeXTestResult.txt
 *  *
 *  Expectation:
 *  *
 *  Description:  1. cutoff to insertion sort
 *                2. Stop if already sorted.
 *
 *      Eliminate the copy to the auxiliary array. Save time (but not space)
 * by switching the role of the input and auxiliary array in each recursive call.
 *
 ******************************************************************************/

public class MergeXTest {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();

        MergeX.sort(a);

        for (String item : a)
            StdOut.println(item);
    }
}
