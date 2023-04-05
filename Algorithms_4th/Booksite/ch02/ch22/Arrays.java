import java.util.Comparator;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Arrays.java
 *  Execution:    java-algs4 Arrays xxx
 *  *
 *  Expectation:
 *  *
 *  Description: /week03/22Mergesort.pdf-44
 *               stable 就是 == 元素的相对顺序不变 没有长距离交换 只有右 < 左时才交换
 *               Equal items never move past each other.
 *
 ******************************************************************************/

public class Arrays {
    // Insertion sort is stable
    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {

    }
}
