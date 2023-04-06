/* *****************************************************************************
 *  Name:   HanLei
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class FastCollinearPoints {
    private static final int INIT_CAPTITY = 1; // 线段数组 初始容量
    private static final int CUTOFF = 14;      // 小数组 -> 插入排序
    private LineSegment[] lineSegments;        // 线段数组
    private int[] lineIndex;    // 线段两端索引数组
    private int numLine;        // 线段个数
    private int start;          // 共线求得的两端索引
    private int end;            // 共线求得的两端索引
    private int[] copyIndex;    // 复制索引
    private int[] auxIndex;     // 复制lines用于排序
    private Point[] copyPoints; // immutable 方便根据索引寻找具体点
    private Comparator<Point> p;        // 每轮的基准点
    private int indexP;                 // 每轮基准点索引

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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
        // 总点数<4 直接返回
        if (len < 4) {
            // 是否有重复点 为了通过 <4 但需要报 IllegalArgumentException 的测试
            for (int i = 0; i < len; i++)
                for (int j = i + 1; j < len; j++)
                    if (points[i].compareTo(points[j]) == 0)
                        throw new IllegalArgumentException();
            return; // 一定不会共线
        }

        // 初始化
        lineSegments = new LineSegment[INIT_CAPTITY];
        lineIndex = new int[INIT_CAPTITY * 2]; // 线段两端索引数组
        copyIndex = new int[len];
        auxIndex = new int[len];
        numLine = 0;            // 初始线段个数

        // 轮番 求角度
        for (int i = 0; i < len - 3; i++) {
            this.indexP = i;        // 每轮基准点索引
            initIndex();            // 重置copyIndex
            this.p = points[indexP].slopeOrder(); // 每轮的基准点
            sortSlope(indexP + 1, len - 1);  // 以p为基准点按斜率排序
            isDuplicate();              // 是否有重复的点
            equalSlope();               // 根据排序找共线点
        }
    }

    // 每轮初始化 copyIndex  每次sortSlope排序后 index内容打乱
    private void initIndex() {
        int len = copyPoints.length;
        for (int i = indexP; i < len; i++) // notice
            copyIndex[i] = i;
    }


    // 按斜率 归并排序 start
    private void sortSlope(int lo, int hi) {
        // if (lo >= hi) return;
        if (hi - lo <= CUTOFF) {
            insertionSort(lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortSlope(lo, mid);
        sortSlope(mid + 1, hi);

        if (compare(copyIndex[mid], copyIndex[mid + 1]))
            return;

        merge(lo, mid, hi);
    }

    private void merge(int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++)
            auxIndex[k] = copyIndex[k];

        for (int k = lo; k <= hi; k++)
            if (i > mid) copyIndex[k] = auxIndex[j++];
            else if (j > hi) copyIndex[k] = auxIndex[i++];
            else if (compare(auxIndex[i], auxIndex[j])) copyIndex[k] = auxIndex[i++];
            else copyIndex[k] = auxIndex[j++];
    }

    private boolean compare(int i, int j) {
        return p.compare(copyPoints[i], copyPoints[j]) < 0;
    }
    // 按照斜率 归并排序 end

    // 按照斜率 插入排序 start
    private void insertionSort(int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int j = i;
            int item = copyIndex[j];
            for (; j > lo && compare(item, copyIndex[j - 1]); j--)
                copyIndex[j] = copyIndex[j - 1];
            copyIndex[j] = item;
        }
    }
    // 按照斜率 插入排序 end

    // 是否有重复的点
    private void isDuplicate() {
        // 基准点 p0
        Point p0 = copyPoints[copyIndex[indexP]]; // notice
        // 相对基准点 斜率最小的点一定排在 copyIndex[1] 处
        Point pMinSlode = copyPoints[copyIndex[indexP + 1]]; // notice

        // 斜率为 负无穷 则两点重复
        if (p0.slopeTo(pMinSlode) == Double.NEGATIVE_INFINITY)
            throw new IllegalArgumentException();
    }

    // 求斜率相同的点 start
    private void equalSlope() {
        int num = copyPoints.length;
        int k = indexP + 2, n = 2; // notice
        for (; k < num; k++) {
            // 相同斜率数 n >=3 则取出该段 sort() 求两端
            // 已经从小到大排序 k-1 <= k ; compare false -> ==
            if (!compare(copyIndex[k - 1], copyIndex[k]))
                n++;
            else {
                linePoint(n, k);
                n = 2;
            }
        }
        linePoint(n, k);
    }

    // 生成共线点 索引数组
    private void linePoint(int n, int k) {
        // 是否 >= 4
        if (n < 4) {
            return;
        }
        // 共线时 点的索引
        int[] lines = new int[n];
        lines[0] = copyIndex[indexP]; // notice
        for (int i = 1; i < n; i++)
            lines[i] = copyIndex[k - i];

        sort(lines);
        // 更新或加入端点索引集合 lineIndex
        updateLine();
    }

    // 共线 求两端
    private void sort(int[] lines) {
        int len = lines.length;
        for (int i = 0; i < len; i++) {
            int j = i;
            int item = lines[j];
            for (; j > 0 && copyPoints[item].compareTo(copyPoints[lines[j - 1]]) < 0; j--)
                lines[j] = lines[j - 1];
            lines[j] = item;
        }
        // 线段的两端点索引
        start = lines[0];
        end = lines[len - 1];
    }
    // 求斜率相同的点 end

    // 端点索引加入集合并去重 lineIndex start
    private void updateLine() {
        Point q = copyPoints[start];
        Point r = copyPoints[end];
        // lineIndex[] 动态的  有效长度是线段个数*2
        int lenLineEnds = numLine * 2;
        for (int i = 0; i < lenLineEnds; i += 2) {
            int pIndex = lineIndex[i];
            int sIndex = lineIndex[i + 1];

            if (start == pIndex && end == sIndex)
                return; // 端点相同的两条线

            Point p = copyPoints[pIndex];
            Point s = copyPoints[sIndex];

            // p->q->r->s  --> 斜率相同 且不平行  取四点坐标排序后的两端
            double slope = p.slopeTo(s);
            double slopeNew = q.slopeTo(r);
            if (slope == slopeNew) { // 斜率相等
                //        有公共点 相交关系                       没有公共点 但包含关系
                if ((start == pIndex || end == sIndex) || (slope == p.slopeTo(q))) {
                    // p q r s 四点共线 坐标排序，取两端
                    int[] lines = { start, end, pIndex, sIndex };
                    sort(lines); // 重置了 start end

                    // 更新端点
                    if (lineIndex[i] != start || lineIndex[i + 1] != end) {
                        lineIndex[i] = start;
                        lineIndex[i + 1] = end;

                        lineSegments[i / 2] = new LineSegment(copyPoints[start], copyPoints[end]);
                    }
                    // 新线段 与以前的存在公共部分
                    return;
                }
            }
        }
        // 没有与之前共线的
        add();
    }

    private void add() {
        // 端点索引存入集合
        lineIndex[numLine * 2] = start;
        lineIndex[numLine * 2 + 1] = end;
        // 端点索引存入集合
        lineSegments[numLine] = new LineSegment(copyPoints[start], copyPoints[end]);
        numLine++;

        // 扩容
        if (numLine == lineSegments.length)
            resize();
    }

    private void resize() {
        int lenLineSeg = lineIndex.length;
        int[] lineIndexNew = new int[lenLineSeg * 2];
        for (int i = 0; i < lenLineSeg; i++) {
            lineIndexNew[i] = lineIndex[i];
        }
        lineIndex = lineIndexNew;

        LineSegment[] lineSegmentsNew = new LineSegment[numLine * 2];
        for (int i = 0; i < numLine; i++) {
            lineSegmentsNew[i] = lineSegments[i];
        }
        lineSegments = lineSegmentsNew;
    }
    // 端点索引加入集合并去重 end

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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        // StdOut.println(collinear.numberOfSegments());
    }
}
