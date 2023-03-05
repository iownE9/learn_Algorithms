import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments: 1Kints.txt (2Kints.txt, 4Kints.txt, and 8Kints.txt)
 * Input:
 * *
 * Compilation: javac-algs4 ThreeSum.java
 * Execution: java-algs4 ThreeSum 1Kints.txt
 */

public class ThreeSum {
    public static int count(int[] a) {
        // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;

        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;

        return cnt;
    }

    public static void main(String[] args) {
        // int[] a = In.readInts(args[0]);
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
