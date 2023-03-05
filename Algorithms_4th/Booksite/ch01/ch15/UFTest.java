import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 UFTest.java
 *  Execution:    java-algs4 UFTest < tinyUF.txt
 *  Description: 路径压缩优化
 *
 ******************************************************************************/

public class UFTest {
    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.

        int N = StdIn.readInt();                // Read number of sites.

        UF uf = new UF(N);        // Initialize N components.

        while (!StdIn.isEmpty()) {
            // Read pair to connect.
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) continue;   // Ignore if connected.

            uf.union(p, q);                     // Combine components
            StdOut.println(p + " " + q);        // and print connection.
        }
        StdOut.println(uf.count() + " components");
    }
}
