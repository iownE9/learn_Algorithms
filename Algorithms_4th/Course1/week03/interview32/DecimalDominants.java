import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 DecimalDominants.java
 *  Execution:    java-algs4 DecimalDominants xxx
 *  *
 *  Expectation:
 *  *
 *  Description:    Decimal dominants.
 *      Given an array with n keys, design an algorithm to find all values that
 *  occur more than n/10 times. The expected running time of your algorithm should be linear.
 *
 ******************************************************************************/

public class DecimalDominants {

    private static void quick3Way(int[] a, int lo, int hi, int num) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        int v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i] - v;
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }

        if ((gt - lt + 1) > num) StdOut.println(v);

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        quick3Way(a, lo, lt - 1, num);
        quick3Way(a, gt + 1, hi, num);

    }

    public static void main(String[] args) {
        int n = 1 << 13;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(10);
        }

        int num = n / 10;
        quick3Way(a, 0, a.length - 1, num);

        StdOut.println("++++验证+++++");
        int i = 1, k = 1;
        for (; i < n; i++) {
            if (a[i] == a[i - 1]) {
                k++;
            }
            else {
                if (k > num) StdOut.println(a[i - 1]);
                k = 1;
            }
        }
        if (k > num) StdOut.println(a[i - 1]);
    }


    private static void exch(int[] a, int i, int j) {
        int item = a[i];
        a[i] = a[j];
        a[j] = item;
    }

}
