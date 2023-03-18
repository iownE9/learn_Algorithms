import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *
 *  Page:  21ElementarySorts.pdf-5
 *  Compilation:  javac-algs4 Experiment.java
 *  Execution:    java-algs4 Experiment 10
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Experiment {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Double[] a = new Double[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();

        Insertion.sort(a);
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
}
