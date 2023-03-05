import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 SumThreeQuadraticTime.java
 *  Execution:    java-algs4 SumThreeQuadraticTime 1Kints.txt
 *  *
 *  Expectation: 70
 *  *
 *  Description: 3-SUM in quadratic time
 *
 *          Design an algorithm for the 3-SUM problem that takes time proportional to n * n
 *   in the worst case. You may assume that you can sort the n integers in time proportional to
 *   n * n or better.
 *
 ******************************************************************************/

public class SumThreeQuadraticTime {
    public static int count(int[] a) {
        int num = 0;
        Arrays.sort(a);
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (binary(-a[i] - a[j], a) > j)
                    num++;
        return num;
    }

    public static int binary(int n, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            // 防溢出
            int mid = lo + (hi - lo) / 2;

            if (n < a[mid]) hi = mid - 1;
            else if (n > a[mid]) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
