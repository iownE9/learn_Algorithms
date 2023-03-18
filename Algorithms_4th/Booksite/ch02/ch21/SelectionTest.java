import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: javac-algs4 SelectionTest.java
 * Execution:   java-algs4 SelectionTest < tiny.txt
 * *
 */


public class SelectionTest {

    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        Selection.sort(a);

        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
        
    }
}
