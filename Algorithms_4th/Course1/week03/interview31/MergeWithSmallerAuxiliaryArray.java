import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 MergeWithSmallerAuxiliaryArray.java
 *  Execution:    java-algs4 MergeWithSmallerAuxiliaryArray MergeWithSmallerAuxiliaryArray.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    Merging with smaller auxiliary array
 *       Suppose that the subarray a[0] to a[n−1] is sorted and the subarray
 *  a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to
 *  a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?
 *
 ******************************************************************************/

public class MergeWithSmallerAuxiliaryArray {
    public static void sort(Comparable[] a) {
        int mid = a.length / 2;
        Comparable[] aux = new Comparable[mid];
        for (int i = 0; i < mid; i++)
            aux[i] = a[i];

        for (int i = 0, j = 0, k = mid; i < a.length; i++) {
            // if (j == mid) a[i] = a[k++];
            if (j == mid) break;
            else if (k == a.length) a[i] = aux[j++];
            else if (aux[j].compareTo(a[k]) < 0) a[i] = aux[j++];
            else a[i] = a[k++];
        }
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++)
            b[i] = a[i];
        
        sort(b);
        for (int i : b)
            StdOut.println(i);

    }
}
