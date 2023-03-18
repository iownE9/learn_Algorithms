import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *
 *  Page:   21ElementarySorts.pdf-59
 *  Compilation:  javac-algs4 Shuffle.java
 *  Execution:    java-algs4 Shuffle xxx
 *  *
 *  Expectation:
 *  *
 *  Description: 随机洗牌
 *
 ******************************************************************************/

public class Shuffle {
    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        StdRandom.shuffle(a);
    }
}
