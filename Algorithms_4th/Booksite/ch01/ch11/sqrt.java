import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * *
 * Compilation: javac-algs4 sqrt.java
 * Execution: java-algs4 sqrt n
 */

public class sqrt {

    public static double sqrt(double c) {
        if (c < 0) {
            return Double.NaN;
        }

        double err = 1e-15;
        double t = c;
        while (Math.abs(t - c / t) > err * t) {
            t = (c / t + t) / 2.0;
        }
        return t;

    }

    public static void main(String[] args) {
        
        double c = Double.parseDouble(args[0]);
        StdOut.println(sqrt(c));
    }
}
