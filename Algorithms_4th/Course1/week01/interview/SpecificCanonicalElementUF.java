import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 SpecificCanonicalElementUF.java
 *  Execution:    java-algs4 SpecificCanonicalElementUF < tinyUF.txt
 *  *
 *  Expectation:  {3, 4, 8, 9} find() method should return 9
 *                {0, 1, 2, 5, 6, 7} find() method should return 7
 *  *
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *  *
 *  Description:
 *              Union-find with specific canonical element. Add a method
 *      find() to the union-find data type so that
 *      find(i) returns the largest element in the connected component containing i.
 *      The operations, union(), connected(), and find() should all take logarithmic
 *      time or better.
 *
 *      For example, if one of the connected components is {1, 2, 6, 9}, then the
 *      find() method should return 9 for each of the four elements in the connected components.
 *
 ******************************************************************************/

public class SpecificCanonicalElementUF {
    private int[] parent;
    private int count;

    public SpecificCanonicalElementUF(int N) {
        parent = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = parent.length;
        if (0 > p || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot > qRoot) parent[qRoot] = pRoot;
        else parent[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SpecificCanonicalElementUF uf = new SpecificCanonicalElementUF(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) continue;

            uf.union(p, q);
        }

        StdOut.println("{3, 4, 8, 9} find() method should return " + uf.find(3));
        StdOut.println("{0, 1, 2, 5, 6, 7} find() method should return " + uf.find(7));
    }
}
