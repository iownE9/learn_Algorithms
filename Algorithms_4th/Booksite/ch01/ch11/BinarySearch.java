/**
 * Compilation: javac-algs4 BinarySearch.java
 * Execution: java-algs4 BinarySearch  largeW.txt < largeT.txt
 * *
 * % java-algs4 BinarySearch tinyW.txt < tinyT.txt
 * 50
 * 99
 * 13
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;


public class BinarySearch {

    public static int rank(int key, int[] a) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid]) {
                hi = mid - 1;
            }
            else if (key > a[mid]) {
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            // Read key, print if not in whitelist.
            if (rank(key, whitelist) == -1) {
                StdOut.println(key);
            }
        }
    }
}
