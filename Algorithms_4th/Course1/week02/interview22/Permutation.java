import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Permutation.java
 *  Execution:    java-algs4 Permutation Permutation.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    Permutation.
 *      Given two integer arrays of size n, design a subquadratic algorithm to
 *      determine whether one is a permutation of the other. That is, do they
 *      contain exactly the same entries but, possibly, in a different order.
 *
 ******************************************************************************/

public class Permutation {

    private static void shellSort(Comparable[] a) {
        int h = 1;
        int N = a.length / 3;
        while (h < N) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                int j = i;
                Comparable item = a[j];
                for (; j >= h && item.compareTo(a[j - h]) < 0; j -= h)
                    a[j] = a[j - h];

                a[j] = item;
            }
            h /= 3;
        }

    }

    public static boolean isPermutation(Integer[] a, Integer[] b) {
        shellSort(a);
        shellSort(b);

        for (int i = 0; i < a.length; i++)
            // (a == b) || (a != null && a.equals(b));
            if (!Objects.equals(a[i], b[i]))
                return false;

        return true;
    }

    private static void construction(Integer[] a, In in) {
        for (int i = 0; i < a.length; i++)
            if (!in.isEmpty())
                a[i] = in.readInt();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // array length

        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];
        construction(a, in);
        construction(b, in);


        StdOut.println(isPermutation(a, b));
    }
}
