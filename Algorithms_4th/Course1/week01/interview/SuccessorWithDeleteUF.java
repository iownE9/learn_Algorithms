import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 SuccessorWithDeleteUF.java
 *  Execution:    java-algs4 SuccessorWithDeleteUF < SuccessorWithDelete.txt
 *  *
 *  Expectation:  {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} delete 4 should return 5
 *  *
 *  Description:
 *              Successor with delete.
 *      Given a set of n integers S={0,1,...,n−1} and a sequence of
 *      requests of the following form:
 *          Remove x from S
 *          Find the successor of x: the smallest y in S such that y ≥ x.
 *
 *      design a data type so that all operations (except construction)
 *      take logarithmic time or better in the worst case.
 *
 * > more SuccessorWithDelete.txt
 * 10
 * 4
 * 4 3
 * 3 8
 * 6 5
 * 9 4
 * 2 1
 * 8 9
 * 5 0
 * 7 2
 * 6 1
 * 1 0
 * 6 7
 * 2 3
 *
 ******************************************************************************/

public class SuccessorWithDeleteUF {
    private int[] parent;
    private int count;

    public SuccessorWithDeleteUF(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    private void validate(int p) {
        int n = parent.length;
        if (0 > p || p >= n)
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
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

        if (pRoot > qRoot) parent[pRoot] = qRoot;
        else parent[qRoot] = pRoot;
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SuccessorWithDeleteUF uf = new SuccessorWithDeleteUF(n);
        int x = StdIn.readInt();
        int y = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (p < x || q < x || uf.connected(p, q)) continue;

            uf.union(p, q);
            y = uf.find(p);
        }
        StdOut.println("{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} delete 4 should return " + y);

    }
}
