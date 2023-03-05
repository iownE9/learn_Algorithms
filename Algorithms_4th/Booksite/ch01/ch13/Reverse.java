import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * *
 * Compilation: ctr + B
 * Execution: ctr + E
 * Arguments:
 * *
 * Compilation: javac-algs4 Reverse.java
 * Execution: java-algs4 Reverse < Stats.txt
 */

public class Reverse {

    public static void main(String[] args) {
        Stack<Integer> stack;
        stack = new Stack<Integer>();
        while (!StdIn.isEmpty())
            stack.push(StdIn.readInt());
        
        for (int i : stack)
            StdOut.println(i);
    }
}
