import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page: 94
 *  Compilation:  javac-algs4 StackTest.java
 *  Execution:    java-algs4 StackTest < tobe.txt
 *
 ******************************************************************************/

public class StackTest {
    public static void main(String[] args) {
        // Create a stack and push/pop strings as directed on StdIn.
        Stack<String> s = new Stack<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
