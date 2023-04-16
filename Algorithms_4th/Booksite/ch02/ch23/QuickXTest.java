import edu.princeton.cs.algs4.QuickX;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 QuickXTest.java
 *  Execution:    java-algs4 QuickXTest < shakespeare.txt > QuickXTestResult.txt
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class QuickXTest {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        QuickX.sort(a);
        for (String item : a)
            StdOut.println(item);
    }
}
