import edu.princeton.cs.algs4.QuickX;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 SelectionTwoSortedArrays.java
 *  Execution:    java-algs4 SelectionTwoSortedArrays xxx
 *  *
 *  Expectation:    https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1
 *  *
 *  Description:    Selection in two sorted arrays.
 *      Given two sorted arrays a[] and b[], of lengths n1 and n2 and an integer
 * 0 ≤ k < n1+n2, design an algorithm to find a key of rank k. The order of growth
 * of the worst case running time of your algorithm should be logn, where n=n1+n2.
 *      Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
 *      Version 2: k=n/2 (median).
 *      Version 3: no restrictions.
 *
 ******************************************************************************/


public class SelectionTwoSortedArrays {

    //  a[alo,ahi]  b[blo,bhi] alen <= blen
    private static int selectKth(Integer[] a, int alo, int ahi, Integer[] b, int blo, int bhi,
                                 int k) {

        StdOut.println(alo + " - " + ahi + " - " + blo + " - " + bhi + " - " + k);
        //  特殊情况
        if (alo > ahi) return b[blo + k - 1];
        //  一方最小值大于等于一方最大值
        int alen = ahi - alo;
        int blen = bhi - blo;
        if (a[alo] >= b[Math.min(blo + k, bhi)]) {
            if (k <= blen) return b[blo + k];
            else return a[alo + k - blen - 1];
        }
        if (b[blo] >= a[Math.min(alo + k, ahi)]) {
            if (k <= alen) return a[alo + k];
            else return b[blo + k - alen - 1];
        }

        //  base case
        if (k == 0) return Math.min(a[alo], b[blo]);
        if (k == 1) return Math.max(a[alo], b[blo]);
        //  递归
        int mid = k / 2;
        int amid, bmid;
        if (mid <= alen) {
            amid = alo + mid;
            bmid = blo + mid;
        }
        else {
            amid = ahi;
            bmid = blo + 2 * mid - alen;
        }
        int x = a[amid];
        int y = b[bmid];
        if (x < y)
            return selectKth(a, amid, ahi, b, blo, bmid, k - (amid - alo));
        else if (x > y)
            return selectKth(a, alo, amid, b, bmid, bhi, k - (bmid - blo));
        else return x;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (int i = 0; i < 1; i++) {
            test(false);
        }
    }

    private static void test(boolean tt) {
        Integer[] a, b;
        int n1, n2, k, res;
        if (tt) {
            n1 = StdRandom.uniformInt(30);
            n2 = StdRandom.uniformInt(30);
            a = new Integer[n1];
            b = new Integer[n2];
            init(a);
            init(b);
            tostring(a);
            tostring(b);
            k = StdRandom.uniformInt(n1 + n2);
            StdOut.println("++++++++++" + k);
            // 0 ≤ k < n1+n2
            if (n1 == 0 && n2 == 0) throw new NoSuchElementException("n1 == 0 && n2 == 0");
            if (n1 == 0) res = b[k];
            else if (n2 == 0) res = a[k];
            else if (n1 <= n2) res = selectKth(a, 0, a.length - 1, b, 0, b.length - 1, k);
            else res = selectKth(b, 0, b.length - 1, a, 0, a.length - 1, k);
        }
        else {

            a = new Integer[] {
                    1, 1, 2, 5, 6, 6, 7, 8, 14, 15
            };
            b = new Integer[] {
                    4, 4, 5, 6, 9, 16, 17, 17, 19, 27, 30, 30, 31, 32, 32, 32, 32, 35, 35
            };
            k = 15;
            res = selectKth(a, 0, a.length - 1, b, 0, b.length - 1, k);
        }
        Integer[] validate = sort(a, b);

        if (res == validate[k])
            StdOut.println("success!");
        else {
            StdOut.println("falure! " + res + "  " + validate[k] + " " + k);

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
        int alen = a.length;
        int blen = b.length;
        Integer[] c = new Integer[alen + blen];

        System.arraycopy(a, 0, c, 0, alen);
        System.arraycopy(b, 0, c, alen, blen);

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
