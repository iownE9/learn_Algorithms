/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private static final int INIT_CAPTITY = 1;
    private LineSegment[] lineSegments;
    private int n;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            except();

        lineSegments = new LineSegment[INIT_CAPTITY];
        n = 0;

        int N = points.length;
        for (int i = 0; i < N; i++) {
            Point p = points[i];
            for (int j = i + 1; j < N; j++) {
                Point q = points[j];
                for (int k = j + 1; k < N; k++) {
                    Point r = points[k];
                    if (p.slopeOrder().compare(q, r) == 0)
                        for (int l = k + 1; l < N; l++) {
                            Point s = points[l];
                            if (p.slopeOrder().compare(q, s) == 0) {
                                // 四点排序 取两端
                                Point[] line = new Point[] {
                                        p, q, r, s
                                };
                                sort(line);
                                add(new LineSegment(line[0], line[3]));
                               
                            }
                        }
                }
            }
        }
    }

    private void sort(Point[] line) {
        for (int i = 1; i < 4; i++) {
            Point item = line[i];
            int j = i;
            for (; j > 0 && item.compareTo(line[j - 1]) < 0; j--)
                line[j] = line[j - 1];
            line[j] = item;
        }
    }

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

    private void except() {
        throw new IllegalArgumentException("");
    }

    // the number of line segments
    public int numberOfSegments() {
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments1 = new LineSegment[n];
        for (int i = 0; i < n; i++) {
            lineSegments1[i] = lineSegments[i];
        }
        return lineSegments1;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
