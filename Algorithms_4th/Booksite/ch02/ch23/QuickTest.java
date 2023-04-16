import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 QuickTest.java
 *  Execution:    java-algs4 QuickTest < QuickTest.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    Quick-select takes linear time on average
 *
 ******************************************************************************/

public class QuickTest {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        for (String item : a)
            StdOut.println(item);
    }
}
