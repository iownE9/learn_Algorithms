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
    private static final int INIT_CAPTITY = 1;  // 线段数组 初始容量
    private static final int CUTOFF = 7;        // cutoff to insertion sort
    private LineSegment[] lineSegments;         // 线段数组
    private int[] lineIndex;    // 线段两端索引数组，方便去重
    private int numLine;        // 线段个数
    private Point[] copyPoints; // immutable 方便根据索引寻找具体点

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // points 是否为 null
        if (points == null)
            throw new IllegalArgumentException();

        int len = points.length;
        copyPoints = new Point[len];
        //  是否有 null 点; 完成copy
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
        numLine = 0;            // 初始线段个数

        int[] copyIndex = new int[len];     // 复制索引
        int[] auxIndex = new int[len];      // 复制lines用于排序
        // 轮番 求角度
        // for (int i = 0; i < len - 3; i++) {
        // 为了通过最后三个点相互有重复的测试
        for (int i = 0; i < len - 1; i++) {
            initIndex(copyIndex, i);                        // 重置copyIndex
            Comparator<Point> p0 = points[i].slopeOrder();
            sort(copyIndex, auxIndex, i + 1, len - 1, p0);  // 以p为基准点按斜率归并排序
            isDuplicate(copyIndex, i);                      // 是否有重复的点
            equalSlope(copyIndex, i, p0);                   // 根据排序找共线点
        }
    }

    // 每轮初始化 copyIndex  每次sortSlope排序后 index内容打乱
    private void initIndex(int[] copyIndex, int indexP) {
        int len = copyPoints.length;
        for (int i = indexP; i < len; i++) // notice
            copyIndex[i] = i;
    }

    // 归并排序 start
    private void sort(int[] a, int[] aux, int lo, int hi, Comparator<Point> p0) {
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, p0); // 插入排序
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, p0);
        sort(a, aux, mid + 1, hi, p0);
        if (compare(a[mid], a[mid + 1], p0))
            return;
        merge(a, aux, lo, mid, hi, p0);
    }

    private void merge(int[] a, int[] aux, int lo, int mid, int hi, Comparator<Point> p0) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (compare(aux[i], aux[j], p0)) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private void insertion(int[] a, int lo, int hi, Comparator<Point> p0) {
        for (int i = lo; i <= hi; i++) {
            int j = i;
            int item = a[j];
            for (; j > lo && compare(item, a[j - 1], p0); j--)
                a[j] = a[j - 1];
            a[j] = item;
        }
    }

    private boolean compare(int iIndex, int jIndex, Comparator<Point> p) {
        // 按坐标排
        if (p == null)
            return copyPoints[iIndex].compareTo(copyPoints[jIndex]) < 0;
        // 按基准点的斜率排
        return p.compare(copyPoints[iIndex], copyPoints[jIndex]) < 0;
    }
    // 归并排序 end

    // 是否有重复的点
    private void isDuplicate(int[] copyIndex, int indexP) {
        // 基准点 p0
        Point p0 = copyPoints[copyIndex[indexP]];
        // 相对基准点 斜率最小的点一定排在 copyIndex[indexP + 1] 处
        Point pMinSlode = copyPoints[copyIndex[indexP + 1]];

        // 斜率为 负无穷 则两点重复
        if (p0.slopeTo(pMinSlode) == Double.NEGATIVE_INFINITY)
            throw new IllegalArgumentException();
    }

    // 求斜率相同的点 start
    private void equalSlope(int[] copyIndex, int indexP, Comparator<Point> p0) {
        int len = copyPoints.length;
        int k = indexP + 2, n = 2;
        for (; k < len; k++) {
            // 相同斜率数 n >=3 则取出该段 sort() 求两端
            // 已经从小到大排序 k-1 <= k ; compare false -> ==
            if (!compare(copyIndex[k - 1], copyIndex[k], p0))
                n++;
            else {
                linePoint(n, k, copyIndex, indexP);
                n = 2;
            }
        }
        // 临界情况 k = len-1 ; k-1 和 k 斜率相等; k++ 后直接退出
        linePoint(n, k, copyIndex, indexP);
    }

    // 生成共线点 索引数组
    private void linePoint(int n, int k, int[] copyIndex, int indexP) {
        // 是否 >= 4
        if (n < 4) {
            return;
        }
        // 共线点的索引
        int[] lines = new int[n];
        int[] aux = new int[n];
        lines[0] = copyIndex[indexP];
        for (int i = 1; i < n; i++)
            lines[i] = copyIndex[k - i];
        // for (int i = 0; i < n; i++)
        //     StdOut.print(lines[i]);
        // StdOut.println("-");
        // 归并排序 斜率相等的点 按坐标
        sort(lines, aux, 0, n - 1, null);
        // 更新或加入端点索引集合 lineIndex
        updateLine(lines[0], lines[n - 1]);

    }

    // 端点索引加入集合并去重 lineIndex start
    private void updateLine(int start, int end) {
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
                //        有公共点 相交关系                       没有公共点 包含关系
                if ((start == pIndex || end == sIndex) || (slope == p.slopeTo(q))) {
                    // p q r s 四点共线 坐标排序，取两端
                    int[] lines = { start, end, pIndex, sIndex };
                    int hi = lines.length - 1;
                    // 四个点直接按坐标插入排序
                    insertion(lines, 0, hi, null);
                    // 重置 start end
                    start = lines[0];
                    end = lines[hi];
                    // 更新端点
                    if (lineIndex[i] != start || lineIndex[i + 1] != end) {
                        lineIndex[i] = start;
                        lineIndex[i + 1] = end;
                        lineSegments[i / 2] = new LineSegment(copyPoints[start], copyPoints[end]);
                    }
                    // 新线段 与以前的存在公共部分
                    return;
                }
            } // else 为斜率不想等 继续迭代lineIndex
        }
        // 没有与之前共线的
        add(start, end);
    }

    private void add(int start, int end) {
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
