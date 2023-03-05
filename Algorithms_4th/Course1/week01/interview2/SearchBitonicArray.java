import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 SearchBitonicArray.java
 *  Execution:    java-algs4 SearchBitonicArray  678 SearchBitonicArray.txt
 *  *
 *  Expectation: true
 *  *
 *  Description:       Search in a bitonic array.
 *          An array is bitonic if it is comprised of an increasing sequence of
 *      integers followed immediately by a decreasing sequence of integers.
 *      Write a program that, given a bitonic array of n distinct integer values,
 *      determines whether a given integer is in the array.
 *
 *      Standard version: Use ∼ 3lgn compares in the worst case.
 *
 *      Signing bonus: Use ∼ 2lgn compares in the worst case (and prove that no
 *      algorithm can guarantee to perform fewer than ∼ 2lgn compares in the worst case).
 *  *
 *  Proof:
 *          In the worst case, the first call is always false. Every time the range is halved,
 *      so, no algorithm can guarantee to perform fewer than ∼ 2lgn compares in the worst case.
 ******************************************************************************/

public class SearchBitonicArray {
    public static boolean isIn(int key, int[] a) {
        int left = 0;
        int right = a.length - 1;

        return helper(key, a, left, right);
    }

    public static boolean helper(int key, int[] a, int left, int right) {
        if (left > right)
            return false;

        int mid = left + (right - left) / 2;
        if (key == a[mid])
            return true;
        else
            return helper(key, a, left, mid - 1) || helper(key, a, mid + 1, right);
    }

    public static void main(String[] args) {
        int key = Integer.parseInt(args[0]);
        int[] a = In.readInts(args[1]);
        StdOut.println(isIn(key, a));
    }
}
