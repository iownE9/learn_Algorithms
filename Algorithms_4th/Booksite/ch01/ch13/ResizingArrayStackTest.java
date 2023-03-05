import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments: tobe.txt
 * *
 * Compilation: javac-algs4 ResizingArrayStack.java
 * Execution: java-algs4 ResizingArrayStack < tobe.txt
 * *
 */

public class ResizingArrayStackTest {

    public static void main(String[] args) {
        ResizingArrayStack<String> s;
        s = new ResizingArrayStack<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }

        StdOut.println("(" + s.size() + " left on stack)");
    }
}
