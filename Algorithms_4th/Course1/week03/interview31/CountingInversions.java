import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 CountingInversions.java
 *  Execution:    java-algs4 CountingInversions CountingInversions.txt
 *  *
 *  Expectation: 494
 *  *
 *  Description:    Counting inversions.
 *      An inversion in an array a[] is a pair of entries a[i] and
 * a[j] such that i < j but a[i] > a[j]. Given an array, design a linearithmic
 * algorithm to count the number of inversions.
 *
 * https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
 ******************************************************************************/

public class CountingInversions {
    private static int exch;

    public static int count(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        int[] auxIndex = new int[n];

        exch = 0;
        sort(a, index, auxIndex, 0, n - 1);

        return exch;
    }

    private static void sort(Comparable[] a, int[] index, int[] auxIndex, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;
        sort(a, index, auxIndex, lo, mid);
        sort(a, index, auxIndex, mid + 1, hi);
        merge(a, index, auxIndex, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int[] index, int[] auxIndex, int lo, int mid,
                              int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            auxIndex[k] = index[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) index[k] = auxIndex[j++];
            else if (j > hi) index[k] = auxIndex[i++];
            else if (!less(a[auxIndex[j]], a[auxIndex[i]])) index[k] = auxIndex[i++];
            else {
                index[k] = auxIndex[j++];
                exch = exch + mid + 1 - i;
            }

        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++)
            b[i] = a[i];

        StdOut.println(count(b));
    }
}
