import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 WeightedQuickUnionUFTest.java
 *  Execution:    java-algs4 WeightedQuickUnionUFTest < tinyUF.txt
 *  Description:
 *
 ******************************************************************************/

public class WeightedQuickUnionUFTest {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.find(p) == uf.find(q)) continue;

            uf.union(p, q);
            StdOut.println(p + " " + q);
        }

        StdOut.println(uf.count() + " components");
    }
}
