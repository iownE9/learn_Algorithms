/* *****************************************************************************
 *  Name:              Han Lei
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n = 0;          // number of data values
    private double sum = 0.0;   // sample variance * (n-1)
    private double mu = 0.0;    // sample mean

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n or trials must be greater than 0!");

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
            }

            double x = percolation.numberOfOpenSites() * 1.0 / (n * n);
            addDataValue(x);
        }
    }

    private void addDataValue(double x) {
        n++;
        double delta = x - mu;
        mu += delta / n;
        sum += (double) (n - 1) / n * delta * delta;
    }

    private double var() {
        if (n <= 1) return Double.NaN;
        return sum / (n - 1);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mu;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return Math.sqrt(this.var());
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(n));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(n));
    }

    // test client (see below)
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                               + percolationStats.confidenceHi() + "]");
    }
}
