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


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // points 是否为 null
        if (points == null)
            throw new IllegalArgumentException();

        int n = points.length;
        int[] index = new int[n];
        int[] aux = new int[n];     // 辅助索引
        //  是否有 null 点; 完成 index 初始化
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            index[i] = i;
            aux[i] = i;
        }

        // 总点数<4 直接返回
        if (n < 4) {
            // 是否有重复点 为了通过 <4 但需要报 IllegalArgumentException 的测试
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    if (points[i].compareTo(points[j]) == 0)
                        throw new IllegalArgumentException();
            return; // 一定不会共线
        }

        // 初始化
        lineSegments = new LineSegment[INIT_CAPTITY];
        lineIndex = new int[INIT_CAPTITY * 2];      // 线段两端索引数组
        numLine = 0;                                // 初始线段个数

        // 先按坐标排序后的点索引
        sort(points, index, aux, 0, n - 1, null);   // 先按坐标排序

        // 轮流斜率排序
        int[] copyIndex = new int[n];               // 索引
        for (int i = 0; i < n - 1; i++) {
            Comparator<Point> p = points[index[i]].slopeOrder();
            initIndex(index, copyIndex, aux, i);    // 保持每轮索引按坐标排序
            // 以基准点按斜率排序 [i+1, n-1]
            sort(points, copyIndex, aux, i + 1, n - 1, p);
            isDuplicate(points, copyIndex, i);      // 是否有重复的点
            equalSlope(points, copyIndex, i, p);    // 共线点
        }

    }

    //
    private void sort(Point[] a, int[] dst, int[] src, int lo, int hi, Comparator<Point> p) {
        if (hi <= lo + CUTOFF) {
            insertion(a, dst, lo, hi, p); // 插入排序
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, src, dst, lo, mid, p);
        sort(a, src, dst, mid + 1, hi, p);

        if (!less(a[src[mid + 1]], a[src[mid]], p)) {
            for (int i = lo; i <= hi; i++) {
                dst[i] = src[i];
            }
            return;
        }

        merge(a, dst, src, lo, mid, hi, p);
    }

    private void merge(Point[] a, int[] dst, int[] src, int lo, int mid, int hi,
                       Comparator<Point> p) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (less(a[src[j]], a[src[i]], p)) dst[k] = src[j++];
            else dst[k] = src[i++];
        }
    }

    private boolean less(Point a, Point b, Comparator<Point> p) {
        if (p == null) // 按坐标排
            return a.compareTo(b) < 0;
        else // 按基准点的斜率排
            return p.compare(a, b) < 0;
    }

    private void insertion(Point[] a, int[] dst, int lo, int hi, Comparator<Point> p) {
        for (int i = lo; i <= hi; i++) {
            int j = i;
            int item = dst[j];
            for (; j > lo && less(a[item], a[dst[j - 1]], p); j--)
                dst[j] = dst[j - 1];
            dst[j] = item;
        }
    }

    // 每轮初始化 aux  不然每次sortSlope排序后 index内容打乱
    private void initIndex(int[] index, int[] copyIndex, int[] aux, int indexP) {
        int n = copyIndex.length;
        for (int i = indexP; i < n; i++) {
            int t = index[i];
            copyIndex[i] = t;
            aux[i] = t;
        }
    }

    // 是否有重复的点
    private void isDuplicate(Point[] points, int[] copyIndex, int indexP) {
        // 基准点 p0
        Point p0 = points[copyIndex[indexP]];
        // 相对基准点 斜率最小的点一定排在 copyIndex[indexP + 1] 处
        Point pMinSlode = points[copyIndex[indexP + 1]];

        // 斜率为 负无穷 则两点重复
        if (p0.slopeTo(pMinSlode) == Double.NEGATIVE_INFINITY)
            throw new IllegalArgumentException();
    }

    // 求斜率相同的点 start
    private void equalSlope(Point[] points, int[] copyIndex, int indexP, Comparator<Point> p) {
        int n = points.length;
        int k = indexP + 2, num = 2;
        for (; k < n; k++) {
            // 相同斜率数 num >=3 则取出该段 sort() 求两端
            // 已经从小到大排序 k-1 <= k ; less false -> ==
            if (!less(points[copyIndex[k - 1]], points[copyIndex[k]], p))
                num++;
            else {
                linePoint(points, num, k, copyIndex, indexP);
                num = 2;
            }
        }
        // 临界情况 k = n-1 ; k-1 和 k 斜率相等; k++ 后直接退出
        linePoint(points, num, k, copyIndex, indexP);
    }

    // 生成共线点 索引数组
    private void linePoint(Point[] points, int num, int k, int[] copyIndex, int indexP) {
        // 是否 >= 4
        if (num < 4) {
            return;
        }
        // 归并的 stable
        // start 一定是 points[copyIndex[indexP]]
        // end   一定是 points[copyIndex[k-1]]
        // 更新或加入端点索引集合 lineIndex
        updateLine(points, copyIndex[indexP], copyIndex[k - 1]);
    }

    // 端点索引加入集合并去重 lineIndex start
    private void updateLine(Point[] points, int start, int end) {
        Point q = points[start];
        Point r = points[end];
        // lineIndex[] 动态的  有效长度是线段个数*2
        int lenLineEnds = numLine * 2;
        for (int i = 0; i < lenLineEnds; i += 2) {
            // 此情况不可能了
            // if (start == pIndex && end == sIndex)
            //     return; // 端点相同的两条线
            int pIndex = lineIndex[i];
            int sIndex = lineIndex[i + 1];
            Point p = points[pIndex];
            Point s = points[sIndex];
            // p->q->r->s  --> 斜率相同 且不平行  取四点坐标排序后的两端
            double slope = p.slopeTo(s);
            double slopeNew = q.slopeTo(r);

            if (slope == slopeNew) { // 斜率相等
                if (slope == p.slopeTo(q)) {
                    // p->s q->r 四点共线 坐标排序，取两端
                    // 已知: p < s  q < r  p <= q 故最小点 p
                    // 求最大点
                    if (less(s, r, null)) {
                        // 更新端点
                        lineIndex[i + 1] = end;
                        lineSegments[i / 2] = new LineSegment(p, r);
                    }
                    return; // 共线 跳出
                } // else 为平行关系
            } // else 为斜率不相等 继续迭代lineIndex
        }
        // 没有与之前共线的
        add(points, start, end);
    }

    private void add(Point[] points, int start, int end) {
        // 端点索引存入集合
        lineIndex[numLine * 2] = start;
        lineIndex[numLine * 2 + 1] = end;
        // 端点索引存入集合
        lineSegments[numLine] = new LineSegment(points[start], points[end]);
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
        // 实际容量
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
