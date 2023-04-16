import edu.princeton.cs.algs4.QuickX;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Ljx.java
 *  Execution:    java-algs4 Ljx xxx
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Ljx {

    private static Integer find(Integer[] nums1, int i, Integer[] nums2, int j, int k) {
        if ((nums1.length - i) > (nums2.length - j))
            return find(nums2, j, nums1, i, k);

        if (k == 1) {
            if (nums1.length == i) return nums2[j];
            else return Math.min(nums1[i], nums2[j]);
        }

        if (nums1.length == i) return nums2[j + k - 1];

        int si = Math.min(nums1.length, i + k / 2);
        int sj = j + k / 2;

        if (nums1[si - 1] > nums2[sj - 1]) return find(nums1, i, nums2, sj, k - (sj - j));
        else return find(nums1, si, nums2, j, k - (si - i));
    }

    public static void main(String[] args) {
        // for (int i = 0; i < 30; i++) {
        //     StdOut.print(i + " ");
        // }
        // StdOut.println();

        for (int i = 0; i < 80; i++) {
            tes();
        }
    }

    private static void tes() {
        int n1 = StdRandom.uniformInt(32);
        int n2 = StdRandom.uniformInt(30);
        Integer[] a = new Integer[n1];
        Integer[] b = new Integer[n2];
        init(a);
        init(b);

        // 0 ≤ k < n1+n2
        int k = StdRandom.uniformInt(n1 + n2);
        int res;
        if (n1 == 0 && n2 == 0) throw new NoSuchElementException("n1 == 0 && n2 == 0");
        if (n1 == 0) res = b[k];
        else if (n2 == 0) res = a[k];
        else res = find(a, 0, b, 0, k + 1);

        Integer[] validate = sort(a, b);

        if (res != validate[k]) {
            //     StdOut.println("success!");
            // else {
            StdOut.println("falure! " + res + " -- " + validate[k]);
            tostring(a);
            tostring(b);
            tostring(validate);
            StdOut.println("___________!");
        }

    }

    private static void init(Integer[] a) {
        int n = a.length;
        int seed = 2 * n;
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(seed);
        }
        QuickX.sort(a);
    }

    private static Integer[] sort(Integer[] a, Integer[] b) {
        Integer[] c = new Integer[a.length + b.length];

        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);

        QuickX.sort(c);
        return c;
    }

    private static void tostring(Integer[] a) {
        for (int i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }
}
