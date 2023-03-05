import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments: 1000
 * *
 * Compilation: javac-algs4 TestAccumulator.java
 * Execution: java-algs4 TestAccumulator 1000
 */

public class TestAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();

        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.uniform());

        StdOut.println(a);
    }

}

