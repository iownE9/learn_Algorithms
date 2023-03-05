import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 SocialNetworkConnectivity.java
 *  Execution:    java-algs4 SocialNetworkConnectivity < timestamp.txt
 *  Expectation:  earliest: 11:00
 *  *
 *  Description:
 *              Social network connectivity.
 *      Given a social network containing n members and a log file containing m timestamps
 *      at which times pairs of members formed friendships,
 *      design an algorithm to determine the earliest time at which all members are connected
 *      (i.e., every member is a friend of a friend of a friend ... of a friend).
 *      Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
 *      The running time of your algorithm should be
 *      m log n or better and use extra space proportional to n.
 *
 * >more timestamp.txt
 * 10
 * 4 3 00:00
 * 3 8 01:00
 * 6 5 02:00
 * 9 4 03:00
 * 2 1 04:00
 * 8 9 05:00
 * 5 0 06:00
 * 7 2 07:00
 * 6 1 08:00
 * 1 0 09:00
 * 6 7 10:00
 * 3 7 11:00
 * 5 8 12:00
 * 2 3 13:00
 *
 ******************************************************************************/

public class SocialNetworkConnectivity {
    private int[] parent;
    private int[] size;
    private int count;

    public SocialNetworkConnectivity(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
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

        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
        else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SocialNetworkConnectivity uf = new SocialNetworkConnectivity(n);
        String earliest = null;
        while (!StdIn.isEmpty() && uf.count() != 1) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            earliest = StdIn.readString();

            if (uf.find(p) == uf.find(q)) continue;

            uf.union(p, q);
            // StdOut.println(p + " " + q);

        }
        StdOut.println("earliest: " + earliest);
    }
}
