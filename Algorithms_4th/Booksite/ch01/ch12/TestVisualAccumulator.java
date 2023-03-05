import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Steve
 * @date 2022/6/24 11:44
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments: 2000
 * *
 * Compilation: javac-algs4 TestVisualAccumulator.java
 * Execution: java-algs4 TestVisualAccumulator 2000
 */

public class TestVisualAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        VisualAccumulator a = new VisualAccumulator(T, 1.0);
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.uniform());

        StdOut.println(a);
    }


}
