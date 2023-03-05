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
    private boolean[] isfull;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0!");

        uf = new WeightedQuickUnionUF(n * n);
        block = new boolean[n * n];
        isfull = new boolean[n * n];
        openCount = 0;
        this.n = n;
    }

    private void validate(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            throw new IllegalArgumentException("row or col is not between 1 and " + n);
    }

    private void unionToAdjoin(int index, int row, int col) {

        if (row > n || col > n || row <= 0 || col <= 0)
            return;

        if (isOpen(row, col)) {
            int adjoin = translate(row, col);
            uf.union(index, adjoin);

            if (isfull[index] || isfull[adjoin]) {
                isfull[index] = true;
                // if (index == 588) {
                //     StdOut.println("isfull[" + index + "]= " + isfull[index]);
                // }
                isfull[adjoin] = true;
                fullToAdjoin(adjoin, row, col);
            }
            // if (index == 588) {
            //     StdOut.println("12, 39 ok");
            // }
        }
    }

    private void fullToAdjoin(int index, int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            return;

        int adjoin = translate(row, col);
        // if (adjoin == 538) {
        //     StdOut.println("shang " + isfull[adjoin]);
        // }
        if (index != adjoin && isfull[adjoin]) {
            return;
        }
        if (isOpen(row, col)) {
            isfull[adjoin] = true;
            fullToAdjoin(adjoin, row - 1, col);
            fullToAdjoin(adjoin, row + 1, col);
            fullToAdjoin(adjoin, row, col + 1);
            fullToAdjoin(adjoin, row, col - 1);
        }

    }

    private int translate(int row, int col) {
        return n * (row - 1) + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        if (row == 1) {
            isfull[col - 1] = true;
        }

        int index = translate(row, col);
        block[index] = true;
        openCount++;

        unionToAdjoin(index, row - 1, col);
        unionToAdjoin(index, row + 1, col);
        unionToAdjoin(index, row, col + 1);
        unionToAdjoin(index, row, col - 1);

        // 以下 修补 input50-bug.png [12, 39] 和 [11, 39] isFull 均为 false
        if (isfull[index]) {
            fullToAdjoin(index, row, col);
        }
        // unionToAdjoin(index, row - 1, col);
        // unionToAdjoin(index, row + 1, col);
        // unionToAdjoin(index, row, col + 1);
        // unionToAdjoin(index, row, col - 1);

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

        // if (!isOpen(row, col)) {
        //     return false;
        // }

        int index = translate(row, col);

        if (isfull[index])
            return true;

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {

        for (int i = 0; i < n; i++) {
            if (isfull[translate(n, i)]) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
