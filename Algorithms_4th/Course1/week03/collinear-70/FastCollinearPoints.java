/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private static final int INIT_CAPTITY = 1;
    private LineSegment[] lineSegments;
    private int n;
    private Slope[] slopes;
    private Slope[] aux;

    private class Slope {
        private Point point;
        private double slop;

        public Slope(Point point, double slop) {
            this.point = point;
            this.slop = slop;
        }

        public String toString() {
            return slop + " " + point;
        }
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            except();
        // 初始化 线段
        lineSegments = new LineSegment[INIT_CAPTITY];
        n = 0;
        // 初始化 斜率 数组
        int N = points.length;
        slopes = new Slope[N];
        aux = new Slope[N];

        // 挨个求斜率 找相同
        calSlope(points);
    }

    // 求斜率
    private void calSlope(Point[] points) {
        int N = aux.length;
        // 共线点数 >= 4  -> N-3
        // N * (N + NlogN + N)
        for (int i = 0; i < N; i++) {
            exch(points, i, 0);
            Point p = points[0];
            // N
            for (int j = 1; j < N; j++) {
                slopes[j] = new Slope(points[j], p.slopeTo(points[j]));
                // slopes[1..N).slope 的值 是相对 points[0] 的斜率
            }
            // 根据斜率排序 NlogN
            sortSlope(); // [1, N-1]
            // for (int k = 1; k < N; k++) {
            //     StdOut.print(" " + k + ": " + slopes[k].slop);
            // }
            // StdOut.println();

            // [i+1, N) 中相同斜率 >=3  -> 有 >=3 个点与 points[0] 共线
            // N
            equalSlope(p);
        }
    }

    // 交换
    private void exch(Point[] points, int i, int j) {
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    // 求斜率 归并排序 start
    private void sortSlope() {
        int N = slopes.length;
        sortSlope(1, N - 1);
    }

    private void sortSlope(int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sortSlope(lo, mid);
        sortSlope(mid + 1, hi);
        merge(lo, mid, hi);
    }

    private void merge(int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = slopes[k];

        for (int k = lo; k <= hi; k++)
            if (i > mid) slopes[k] = aux[j++];
            else if (j > hi) slopes[k] = aux[i++];
            else if (less(aux[i], aux[j])) slopes[k] = aux[i++];
            else slopes[k] = aux[j++];
    }

    private boolean less(Slope s1, Slope s2) {
        return Double.compare(s1.slop, s2.slop) == -1;
    }
    // 求斜率 归并排序 end


    // 求斜率相同的点 start
    private void equalSlope(Point p) {
        double s1 = slopes[1].slop;
        int N = slopes.length;
        for (int k = 1, lo = 1; k < N; k++) {
            // 相同斜率数 n >=3 则取出该段 sort() 求两端
            if (s1 != slopes[k].slop) {
                if (k - lo >= 3) {
                    sort(lo, k, p); // [lo, k)
                    // StdOut.println("[" + lo + ", " + k + ")");
                }
                lo = k;
                s1 = slopes[lo].slop;
            }
            if (k == N - 1) {
                if (k - lo >= 2) {
                    sort(lo, k + 1, p); // [lo, k]
                    // StdOut.println("[" + lo + ", " + (k + 1) + ")");
                }
                lo = k;
                s1 = slopes[lo].slop;
            }
        }
    }

    // 共线 求两端  ---
    private void sort(int lo, int hi, Point p) {
        int N = hi - lo;
        Point[] ps = new Point[N + 1];

        for (int i = 0; i < N; i++) {
            ps[i] = slopes[lo + i].point;
            // StdOut.print(ps[i] + "  ");
        }
        ps[N] = p;
        // StdOut.print(ps[N] + "  ");
        // StdOut.println(" 原 ");

        for (int i = 0; i <= N; i++) {
            int j = i;
            Point item = ps[j];
            for (; j > 0 && item.compareTo(ps[j - 1]) < 0; j--)
                ps[j] = ps[j - 1];
            ps[j] = item;
        }
        // for (int i = 0; i <= N; i++) {
        //     StdOut.print(ps[i] + "  ");
        // }
        // StdOut.println("点排序");

        LineSegment new_ = new LineSegment(ps[0], ps[N]);
        add(new_);

    }
    // 求斜率相同的点 end

    // 添加线段并去重 start
    private void add(LineSegment new_) {
        // 去重
        // int n = lineSegments.length;  // Error
        for (int i = 0; i < n; i++) {
            // StdOut.print(i + " ");
            if (new_.toString().equals(lineSegments[i].toString())) {
                // StdOut.print("重复");
                return;
            }
        }
        // StdOut.println("add");
        lineSegments[n++] = new_;
        if (n == lineSegments.length)
            resize();
    }

    private void resize() {
        LineSegment[] lineSegmentsNew = new LineSegment[n * 2];
        for (int i = 0; i < n; i++) {
            lineSegmentsNew[i] = lineSegments[i];
        }
        lineSegments = lineSegmentsNew;
    }
    // 添加线段并去重 end

    private void except() {
        throw new IllegalArgumentException("");
    }

    // the number of line segments
    public int numberOfSegments() {
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ls = new LineSegment[n];
        for (int i = 0; i < n; i++) {
            ls[i] = lineSegments[i];
        }
        return ls;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
