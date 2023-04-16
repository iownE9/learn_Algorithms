import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 NutsAndBolts.java
 *  Execution:    java-algs4 NutsAndBolts
 *  *
 *  Expectation:
 *  *
 *  Description:    Nuts and bolts.
 *      A disorganized carpenter has a mixed pile of n nuts and n bolts.
 *  The goal is to find the corresponding pairs of nuts and bolts. Each
 *  nut fits exactly one bolt and each bolt fits exactly one nut.
 *  By fitting a nut and a bolt together, the carpenter can see which
 *  one is bigger (but the carpenter cannot compare two nuts or two bolts
 *  directly). Design an algorithm for the problem that uses at most
 *  proportional to nlogn compares (probabilistically).
 *
 ******************************************************************************/

public class NutsAndBolts {
    private static void quickSort(int[] a, int[] b, int lo, int hi) {
        if (hi <= lo) return;

        // a分为 [lo,i-1] [i+1, hi]
        int i = partition(a, b[lo], lo, hi);
        int j = partition(b, a[i], lo, hi);

        // i == j
        if (a[i] != b[j] && i != j) throw new RuntimeException("数据异常" + i + " " + j);

        quickSort(a, b, lo, i - 1);
        quickSort(a, b, i + 1, hi);
    }

    // 切分成两半，返回切点索引对应值是 item
    private static int partition(int[] a, int item, int lo, int hi) {
        int i = lo, j = hi + 1;

        for (; true; ) {
            while (a[++i] <= item) {
                if (i == hi) break; // item 为最大值
                if (a[i] == item) {
                    exch(a, lo, i);
                    i--;
                }
            }

            while (a[--j] >= item) {
                if (j == lo) break; // item 为最小值
                if (a[j] == item) {
                    exch(a, lo, j);
                    j++;
                }
            }

            // a[i] > item  a[j] < item
            if (i >= j) break;
            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }


    public static void main(String[] args) {
        int n = 1 << 12;
        int[] nuts = new int[n];
        int[] bolts = new int[n];
        init(nuts, bolts, n);

        // 没有重复元素
        quickSort(nuts, bolts, 0, nuts.length - 1);

        // 验证是否相等
        for (int i = 0; i < n; i++) {
            if (nuts[i] != bolts[i]) {
                StdOut.println("失败！" + nuts[i] + "  " + bolts[i]);
                break;
            }
        }
        StdOut.println("success！");
    }


    public static void exch(int[] a, int i, int j) {
        int item = a[i];
        a[i] = a[j];
        a[j] = item;
    }

    public static void init(int[] a, int[] b, int n) {
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = i;
        }
        StdRandom.shuffle(a);
        StdRandom.shuffle(b);

        int i = StdRandom.uniformInt(n);
        exch(a, 0, i);
        exch(b, 0, i);
    }

}
