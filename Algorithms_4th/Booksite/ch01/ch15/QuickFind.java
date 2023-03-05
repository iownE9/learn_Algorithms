import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page: 140
 *  Compilation:  javac-algs4 QuickFind.java
 *  Execution:    java-algs4 QuickFind < tinyUF.txt
 *  *
 *  Description:  find()快 union()慢
 *
 ******************************************************************************/

public class QuickFind {
    private int[] id;       // access to component id (site indexed)
    private int count;      // number of components

    public QuickFind(int N) {
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

        return id[p];
    }

    public void union(int p, int q) {
        // Put p and q into the same component.
        int pID = find(p);
        int qID = find(q);

        // Nothing to do if p and q are already
        // in the same component.
        if (pID == qID) return;

        // Change values from id[p] to id[q].
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;

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

        QuickFind uf = new QuickFind(N);        // Initialize N components.

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
