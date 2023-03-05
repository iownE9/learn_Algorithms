import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * *
 * Compilation: javac-algs4 gcd.java
 * Execution: java-algs4 gcd p q
 */

public class gcd {

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args) {
        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);

        StdOut.println(gcd(p, q));
    }
}
