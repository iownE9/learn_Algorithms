import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: javac-algs4 InsertionTest.java
 * Execution:   java-algs4 insertionTest < tiny.txt
 * *
 */


public class InsertionTest {

    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        Insertion.sort(a);
        
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}
