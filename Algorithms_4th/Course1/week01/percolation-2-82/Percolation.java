/* *****************************************************************************
 *  Name:              Han Lei
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int openCount;
    private boolean[] block;
    private int[] tops;
    private int[] bottoms;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0!");

        uf = new WeightedQuickUnionUF(n * n);
        block = new boolean[n * n];
        tops = new int[n];
        bottoms = new int[n];
        openCount = 0;
        this.n = n;
    }

    private void validate(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            throw new IllegalArgumentException("row or col is not between 1 and " + n);
    }

    private void union(int index, int adjoin) {
        if (0 <= adjoin && adjoin < n * n && block[adjoin])
            uf.union(index, adjoin);
    }

    private int translate(int row, int col) {
        return n * (row - 1) + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        int index = translate(row, col);

        if (block[index]) {
            return;
        }

        block[index] = true;
        openCount++;

        union(index, index + n);
        union(index, index - n);
        if (col == 1) {
            union(index, index + 1);
        }
        else if (col == n) {
            union(index, index - 1);
        }
        else {
            union(index, index - 1);
            union(index, index + 1);
        }

        int bottom = n * (n - 1);

        for (int i = 0; i < n; i++) {
            if (block[i])
                tops[i] = uf.find(i);

            if (block[bottom + i])
                bottoms[i] = uf.find(bottom + i);

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        int index = translate(row, col);

        return block[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);

        int index = translate(row, col);
        if (!block[index]) {
            return false;
        }
        // 没阻塞且在第一行
        if (row == 1)
            return true;

        int iRoot = uf.find(index);

        for (int i = 0; i < n; i++) {
            if (iRoot == tops[i]) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        int bottom = n * (n - 1);
        for (int i = 0; i < n; i++) {
            if (block[i])
                for (int j = 0; j < n; j++)
                    if (block[bottom + j] && tops[i] == bottoms[j])
                        return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
