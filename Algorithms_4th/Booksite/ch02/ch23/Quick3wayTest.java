import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Quick3wayTest.java
 *  Execution:    java-algs4 Quick3wayTest < Quick3wayTest.txt
 *  Execution:    java-algs4 Quick3wayTest < Quick3wayTest2.txt
 *  Execution:    java-algs4 Quick3wayTest < shakespeare.txt > Quick3wayTestResult.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    reduces running time from linearithmic to linear
 *              in broad class of applications
 *
 ******************************************************************************/

public class Quick3wayTest {
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();

        Quick3way.sort(a);  // 像荷兰旗问题则不需要后面的递归

        for (String item : a)
            StdOut.println(item);
    }
}
