import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 IntersectionTwoSets.java
 *  Execution:    java-algs4 IntersectionTwoSets IntersectionTwoSets.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    Intersection of two sets.
 *          Given two arrays a[] and b[], each containing n distinct 2D points in the plane,
 *          design a subQuadratic algorithm to count the number of points that
 *          are contained both in array a[] and array b[].
 *
 ******************************************************************************/

public class IntersectionTwoSets {


    // subQuadratic algorithm
    public static int intersection(Point2D[] a, Point2D[] b) {
        int N = a.length + b.length;
        Point2D[] point2D = new Point2D[N];
        for (int i = 0; i < a.length; i++)
            point2D[i] = a[i];
        for (int i = a.length, j = 0; j < b.length; i++, j++)
            point2D[i] = b[j];

        // for (Point2D item : point2D)
        //     StdOut.println(item);
        // StdOut.println("======");

        // Shell.sort(point2D);
        shell(point2D);

        // for (Point2D item : point2D)
        //     StdOut.println(item);
        // StdOut.println("======");

        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            if (point2D[i].compareTo(point2D[i + 1]) == 0)
                count++;
        }
        return count;
    }


    private static void shell(Point2D[] a) {
        int h = 1;
        int N = a.length / 3;
        while (h < N) h = 3 * h + 1;


        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                int j = i;
                Point2D item = a[j];
                // // Error 性能bug 和 j 最终迭代到 <h, 执行a[j] = item; -> bug
                // for (; j >= h; j -= h)
                //     if (item.compareTo(a[j - h]) < 0)
                //         a[j] = a[j - h];
                // a[j] = item;

                for (; j >= h && item.compareTo(a[j - h]) < 0; j -= h)
                    a[j] = a[j - h];
                a[j] = item;
            }
            h /= 3;
        }
    }

    // quadratic algorithm
    public static int intersection2(Point2D[] a, Point2D[] b) {
        int count = 0;
        for (Point2D aItem : a)
            for (Point2D bItem : b)
                if (aItem.compareTo(bItem) == 0)
                    count++;
        return count;
    }


    private static void constructionPoint2D(Point2D[] a, In in) {
        for (int i = 0; i < a.length; i++) {
            if (!in.isEmpty()) {
                double x = in.readDouble();
                double y = in.readDouble();
                a[i] = new Point2D(x, y);
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // array length

        Point2D[] a = new Point2D[n];
        Point2D[] b = new Point2D[n];

        constructionPoint2D(a, in);
        constructionPoint2D(b, in);

        StdOut.println(intersection(a, b));

        // 这是迭代，只能读，不能写入
        // for (Point2D aItem : a) {
        //     if (!in.isEmpty()) {
        //         double x = in.readDouble();
        //         double y = in.readDouble();
        //         aItem = new Point2D(x, y);
        //     }
        //     // StdOut.println(aItem);
        // }
        // StdOut.println("==========");

    }
}

