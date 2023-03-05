import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 TwoSumFast.java
 *  Execution:    java-algs4 TwoSumFast 1Kints.txt
 *  Description:
 *
 ******************************************************************************/

public class TwoSumFast {
    public static int count(int[] a) {
        // Count pairs that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            if (BinarySearch.rank(-a[i], a) > i)
                cnt++;
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
