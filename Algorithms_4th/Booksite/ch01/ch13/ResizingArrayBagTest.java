import edu.princeton.cs.algs4.ResizingArrayBag;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 ResizingArrayBagTest.java
 *  Execution:    java-algs4 ResizingArrayBagTest
 *  Description: 只进不出
 *
 ******************************************************************************/

public class ResizingArrayBagTest {
    public static void main(String[] args) {
        ResizingArrayBag<String> bag;
        bag = new ResizingArrayBag<String>();

        bag.add("Hello");
        bag.add("World");
        bag.add("how");
        bag.add("are");
        bag.add("you");

        for (String s : bag)
            StdOut.println(s);
    }
}
