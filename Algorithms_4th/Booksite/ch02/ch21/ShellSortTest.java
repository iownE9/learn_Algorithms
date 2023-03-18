import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: javac-algs4 ShellSortTest.java
 * Execution:   java-algs4 ShellSortTest < tiny.txt
 * *
 */


public class ShellSortTest {

    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        Shell.sort(a);

        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}
