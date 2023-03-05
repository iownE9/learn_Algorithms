/* *****************************************************************************
 *  Name:              Han Lei
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int openCount;
    private int[] block;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0!");

        uf = new WeightedQuickUnionUF(n * n);
        block = new int[n * n];
        openCount = 0;
        this.n = n;
    }

    private void validate(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            throw new IllegalArgumentException("row or col is not between 1 and " + n);
    }

    private void union(int index, int adjoin) {
        if (0 <= adjoin && adjoin < n * n && block[adjoin] == 1)
            uf.union(index, adjoin);
    }

    private int translate(int row, int col) {
        return n * (row - 1) + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        int index = translate(row, col);

        if (block[index] == 1) {
            return;
        }

        block[index] = 1;
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
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        int index = translate(row, col);

        return block[index] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = translate(row, col);

        for (int top = 0; top < n; top++) {
            if (block[top] == 1 && uf.find(top) == uf.find(index)) {
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

        for (int top = 0; top < n; top++) {
            if (block[top] == 1)
                for (int bottom = n * (n - 1); bottom < n * n; bottom++) {
                    if (block[bottom] == 1 && uf.find(top) == uf.find(bottom))
                        return true;
                }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
