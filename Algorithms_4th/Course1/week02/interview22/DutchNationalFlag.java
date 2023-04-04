import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 DutchNationalFlag.java
 *  Execution:    java-algs4 DutchNationalFlag DutchNationalFlag.txt
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
    private final String RED = "red";

    private Pebble[] buckets;

    public DutchNationalFlag(int N) {
        buckets = new Pebble[N];
    }

    public void sortBRW() { // Dijkstra
        int b = 0, r = 0, w = buckets.length - 1;
        if (w < 2) return;

        while (r <= w) {
            // while (color(w).compareTo(RED) > 0) w--;
            // while (color(b).compareTo(RED) < 0) {
            //     b++;
            //     r++;
            // }

            int cmp = color(r).compareTo(RED);

            if (cmp > 0) swap(r, w--);
            else if (cmp < 0) swap(b++, r++);
            else r++;
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

        dutchNationalFlag.sortBRW();

        for (Pebble item : dutchNationalFlag.buckets)
            StdOut.println(item);
    }
}
