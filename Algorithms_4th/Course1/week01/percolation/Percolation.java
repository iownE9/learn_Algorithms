/* *****************************************************************************
 *  Name:              Han Lei
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int openCount;
    private boolean isPercolation;
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
        isPercolation = false;
    }

    private void validate(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            throw new IllegalArgumentException("row or col is not between 1 and " + n);
    }

    private boolean inRange(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            return false;
        return true;
    }

    private void unionToAdjoin(int row1, int col1, int row2, int col2) {

        if (!inRange(row2, col2))
            return;

        if (isOpen(row2, col2)) {
            uf.union(translate(row1, col1), translate(row2, col2));

            if (!isFull(row1, col1) && isFull(row2, col2)) {
                isfull[translate(row1, col1)] = true;
            }
        }
    }

    // 尾递归优化
    private void fullToAdjoin(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            return;

        if (isFull(row, col)) {
            return;
        }
        if (isOpen(row, col)) {
            isfull[translate(row, col)] = true;
            if (row == n && !isPercolation) {
                isPercolation = true;
            }
            fullToAdjoin(row - 1, col);
            fullToAdjoin(row + 1, col);
            fullToAdjoin(row, col + 1);
            fullToAdjoin(row, col - 1);
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

        block[translate(row, col)] = true;
        openCount++;

        unionToAdjoin(row, col, row - 1, col);
        unionToAdjoin(row, col, row + 1, col);
        unionToAdjoin(row, col, row, col + 1);
        unionToAdjoin(row, col, row, col - 1);

        if (isFull(row, col)) {
            if (row == n && !isPercolation) {
                isPercolation = true;
            }
            fullToAdjoin(row - 1, col);
            fullToAdjoin(row + 1, col);
            fullToAdjoin(row, col + 1);
            fullToAdjoin(row, col - 1);
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

        return isPercolation;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
