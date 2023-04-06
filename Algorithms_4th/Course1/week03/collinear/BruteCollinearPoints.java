/* *****************************************************************************
 *  Name:   HanLei
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private static final int INIT_CAPTITY = 2; // 线段两端索引数组 初始容量 一条线两端点->两个索引
    private static final int LEN = 4; // points 最小限制 成线时lines数组大小
    private LineSegment[] lineSegments;    // 线段数组  add
    private int[] lineIndex;    // 线段两端索引数组
    private int numLine;        // 线段数量
    private int start;          // 共线求得的两端索引
    private int end;            // 共线求得的两端索引
    private int[] lines;        // 4点共线时 4点的索引
    private int[] auxLines;     // 复制lines用于排序
    private Point[] copyPoints; // immutable 方便根据索引寻找具体点


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // points 是否为 null
        if (points == null)
            throw new IllegalArgumentException();

        int len = points.length;
        copyPoints = new Point[len];    // 复制一份
        //  是否有 null 点
        for (int i = 0; i < len; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            copyPoints[i] = points[i];
        }

        // 是否有重复点
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();

        // 总点数<4 直接返回
        if (len < LEN)
            return;

        // 初始化
        lineSegments = new LineSegment[INIT_CAPTITY / 2]; // add
        lineIndex = new int[INIT_CAPTITY]; // 线段两端索引数组
        numLine = 0;            // 初始线段个数
        lines = new int[LEN];
        auxLines = new int[LEN];

        int p, q, r, s;
        for (p = 0; p < len - 3; p++)
            for (q = p + 1, lines[0] = p; q < len - 2; q++)
                for (r = q + 1, lines[1] = q; r < len - 1; r++)
                    for (s = r + 1, lines[2] = r; s < len; s++) {
                        lines[3] = s;
                        // 是否 p q r s 四点共线 line[]
                        if (equalSlope()) {
                            sort(); // q r s p 点坐标排序，取两端端点索引 endsIndex
                            add();  // 端点索引加入集合并去重 lineIndex
                        }
                    }

    }

    // 是否 p q r s 四点共线 line[]
    private boolean equalSlope() {
        Point p = copyPoints[lines[0]];
        double slope = p.slopeTo(copyPoints[lines[1]]);
        for (int i = 2; i < LEN; i++) {
            if (slope != p.slopeTo(copyPoints[lines[i]]))
                return false;
        }
        return true;
    }

    // q r s p 点坐标排序，取两端端点索引 endsIndex
    private void sort() {
        for (int i = 0; i < LEN; i++) {
            auxLines[i] = lines[i];
        }
        for (int i = 0; i < LEN; i++) {
            int j = i;
            int item = auxLines[j];
            for (; j > 0 && copyPoints[item].compareTo(copyPoints[auxLines[j - 1]]) < 0; j--)
                auxLines[j] = auxLines[j - 1];
            auxLines[j] = item;
        }
        // 线段的两端点索引
        start = auxLines[0];
        end = auxLines[LEN - 1];
    }

    // 端点索引加入集合并去重 lineIndex
    private void add() {
        // lineIndex[] 动态的  有效长度是线段个数*2
        int lenLineEnds = numLine * 2;
        for (int i = 0; i < lenLineEnds; i += 2)
            if (start == lineIndex[i] && end == lineIndex[i + 1])
                return;

        // 端点索引存入集合
        lineIndex[lenLineEnds] = start;
        lineIndex[lenLineEnds + 1] = end;
        lineSegments[numLine] = new LineSegment(copyPoints[start], copyPoints[end]); // add
        numLine++;

        // 扩容
        if ((lenLineEnds + 2) == lineIndex.length)
            resize();
    }

    // lineIndex[] 扩容
    private void resize() {
        int lenLineSeg = lineIndex.length;
        int[] lineIndexNew = new int[lenLineSeg * 2];
        for (int i = 0; i < lenLineSeg; i++) {
            lineIndexNew[i] = lineIndex[i];
        }
        lineIndex = lineIndexNew;

        // add
        LineSegment[] lineSegmentsNew = new LineSegment[numLine * 2];
        for (int i = 0; i < numLine; i++) {
            lineSegmentsNew[i] = lineSegments[i];
        }
        lineSegments = lineSegmentsNew;
        // add
    }

    // the number of line segments
    public int numberOfSegments() {
        return numLine;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ls = new LineSegment[numLine];
        for (int i = 0; i < numLine; i++) {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
