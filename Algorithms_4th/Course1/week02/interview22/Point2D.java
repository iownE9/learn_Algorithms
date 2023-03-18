/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Point2D.java
 *  Execution:    java-algs4 Point2D xxx
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Point2D implements Comparable<Point2D> {
    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // public boolean sample(Point2D that) {
    //     return this.x == that.x && this.y == that.y;
    // }

    public int compareTo(Point2D that) {
        // double thatDis = that.x * that.y;
        // double thisDis = this.x * this.y;
        //
        // return Double.compare(thisDis, thatDis);
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    public String toString() {
        return x + " " + y;
    }

    public static void main(String[] args) {

    }
}
