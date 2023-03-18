import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 DutchNationalFlag.java
 *  Execution:    java-algs4 DutchNationalFlag xxx
 *  *
 *  Expectation:
 *  *
 *  Description:    Dutch national flag.
 *      Given an array of n buckets, each containing a red, white, or blue pebble,
 *      sort them by color. The allowed operations are:
 *          swap(i,j):  swap the pebble in bucket i with the pebble in bucket j.
 *          color(i): determine the color of the pebble in bucket i.
 *
 *      The performance requirements are as follows:
 *          At most n calls to color().
 *          At most n calls to swap().
 *          Constant extra space.
 *
 ******************************************************************************/

public class DutchNationalFlag {
    private final String red = "red";       // 02
    private final String white = "white";   // 03
    private final String blue = "blue";     // 01

    private Pebble[] buckets;
    private int loRed;
    private int hiRed;

    public DutchNationalFlag(int N) {
        buckets = new Pebble[N];
    }

    public void sortRWB() { // blue red white
        int lo = 0, hi = buckets.length - 1;
        String color1 = color(lo);
        String color2 = color(hi);

        while (!Objects.equals(color1, blue)) {
            lo++;
            color1 = color(lo);
        }

        while (!Objects.equals(color2, white)) {
            hi--;
            color2 = color(hi);
        }

        while (lo < hi) {

        }


    }

    public void swap(int i, int j) {
        Pebble temp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = temp;
    }

    public String color(int i) {
        return buckets[i].getColor();
    }


    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // array length

        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag(n);
        for (int i = 0; i < n; i++)
            if (!in.isEmpty())
                dutchNationalFlag.buckets[i] = new Pebble(in.readString());

        dutchNationalFlag.sortRWB();

        for (Pebble item : dutchNationalFlag.buckets)
            StdOut.println(item);
    }
}
