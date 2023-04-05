import edu.princeton.cs.algs4.MergeBU;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 MergeBUSort.java
 *  Execution:    java-algs4 MergeBUSort < MergeBUSort.txt
 *  *
 *  Expectation:
 *  *
 *  Description: 自底向上的归并排序
 *      那个是通过递归划分成单个元素数组，这个是迭代时直接定 merge 范围
 *
 ******************************************************************************/

public class MergeBUSort {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();

        MergeBU.sort(a);

        for (String item : a)
            StdOut.println(item);
    }
}
