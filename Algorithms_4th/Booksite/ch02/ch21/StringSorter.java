import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:  21ElementarySorts.pdf-6
 *  Compilation:  javac-algs4 StringSorter.java
 *  Execution:    java-algs4 StringSorter < words3.txt
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class StringSorter {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();

        Insertion.sort(a);
        
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}
