import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments:
 * *
 * Compilation: javac-algs4 Whitelist.java
 * Execution: java-algs4 Whitelist tinyW.txt < tinyT.txt
 */

public class Whitelist {
    public static void main(String[] args) {
        int[] w = In.readInts(args[0]);
        StaticSETofInts set = new StaticSETofInts(w);

        while (!StdIn.isEmpty()) {
            // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (!set.contains(key))
                StdOut.println(key);
        }
    }
}
