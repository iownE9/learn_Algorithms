import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.InsertionX;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 InsertionXTest.java
 *  Execution:    java-algs4 InsertionXTest < tiny.txt
 *  *
 *  Expectation:
 *  *
 *  Description: 右移而非交换; 先把最小的放最左边,没有交换的话，直接返回
 *
 ******************************************************************************/

public class InsertionXTest {
    public static void main(String[] args) {
        String[] a = In.readStrings();
        InsertionX.sort(a);

        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}
