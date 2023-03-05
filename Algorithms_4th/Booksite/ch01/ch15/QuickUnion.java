import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 QuickUnion.java
 *  Execution:    java-algs4 QuickUnion < tinyUF.txt
 *  Description:
 *
 ******************************************************************************/

public class QuickUnion {
    private int[] id;       // access to component id (site indexed)
    private int count;      // number of components

    public QuickUnion(int N) {
        // Initialize component id array.
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        validate(p);

        // Find component name.
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        // Give p and q the same root.
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;
        count--;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.

        int N = StdIn.readInt();                // Read number of sites.

        QuickUnion uf = new QuickUnion(N);        // Initialize N components.

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
