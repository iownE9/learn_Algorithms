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
    // shuffle1 start
    public static void shuffle1(Object[] a) {
        int n = a.length;
        double[] aux = new double[n];
        for (int i = 0; i < n; i++) {
            aux[i] = StdRandom.uniformDouble();
        }

        int h = 1;
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int j = h;
                double item = aux[j];
                Object itemObject = a[j];
                for (; j >= h && item < aux[j - h]; j -= h) {
                    aux[j] = aux[j - h];
                    a[j] = a[j - h];
                }
                aux[j] = item;
                a[j] = itemObject;
            }
            h /= 3;
        }
    }
    // shuffle1 end


    // shuffle2 start
    public static void shuffle2(Object[] a) {
    }

    private static int compareTo(Object that) {
        double r = Math.random();
        if (r < 0.5) return -1;
        if (r > 0.5) return +1;
        return 0;
    }
    // shuffle2 end


    // shuffle3 start
    public static void shuffle3(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i + 1);
            Object item = a[i];
            a[i] = a[r];
            a[r] = item;
        }
    }
    // shuffle3 end

    public static void main(String[] args) {
        // Read strings from standard input, sort them, and print.
        String[] a = In.readStrings();
        StdRandom.shuffle(a);
    }
}
