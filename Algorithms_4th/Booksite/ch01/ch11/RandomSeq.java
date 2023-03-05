import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * *
 * Compilation: javac-algs4 RandomSeq.java
 * Execution: java-algs4 RandomSeq 5 100.0 200.0
 * Execution: java-algs4 RandomSeq 5 100.0 200.0 > data.txt
 */


public class RandomSeq {
    public static void main(String[] args) {
        // Print N random values in [lo, hi).
        int N = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);

        for (int i = 0; i < N; i++) {
            double x = StdRandom.uniform(lo, hi);
            StdOut.printf("%.2f\n", x);
        }
    }
}
