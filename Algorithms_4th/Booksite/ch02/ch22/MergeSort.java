import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 MergeSort.java
 *  Execution:    java-algs4 MergeSort < MergeSort.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    自顶向下的归并排序
 *      1. private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
 *          前提   a[lo...mid] is sorted ;  a[mid+1...hi] is sorted
 *          结果   a[lo...hi] sorted
 *      2. private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
 *          递归 使 前半部分、后本部分都 成一个元素的数组
 *          然后调用 merge()
 *          整体递归回来 a[lo...hi] is sorted
 *      3. public static void sort(Comparable[] a)
 *          到处的方法
 *         复制一份到 aux
 *         调用 sort()
 *
 ******************************************************************************/

public class MergeSort {
    public static void main(String[] args) {
        String[] data = StdIn.readAllStrings();

        Merge.sort(data);

        for (String item : data)
            StdOut.println(item);
    }
}
