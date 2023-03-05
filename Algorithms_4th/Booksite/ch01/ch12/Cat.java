import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments: in1.txt in2.txt out.txt
 * *
 * Compilation: javac-algs4 Cat.java
 * Execution: java-algs4 Cat in1.txt in2.txt out.txt
 */

public class Cat {
    public static void main(String[] args) {
        // Copy input files to out (last argument).
        Out out = new Out(args[args.length - 1]);

        for (int i = 0; i < args.length - 1; i++) {
            // Copy input file named on ith arg to out.
            In in = new In(args[i]);
            String s = in.readAll();
            out.println(s);
            in.close();
        }
        out.close();
    }
}
