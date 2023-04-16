import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 QuickSelectKth.java
 *  Execution:    java-algs4 QuickSelectKth < QuickTest.txt
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class QuickSelectKth {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int k = StdRandom.uniformInt(a.length);

        StdOut.println(k + "th= " + Quick.select(a, k));

        for (int i = 0; i < a.length; i++)
            StdOut.println(i + " " + a[i]);
    }
}
