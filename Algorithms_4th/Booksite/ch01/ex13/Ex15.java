import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Ex15.java
 *  Execution:    java-algs4 Ex15 xxx
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Ex15 {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        k = queue.size() - k;
        for (int i = 0; i < k; i++) {
            queue.dequeue();
        }
        StdOut.println(queue.dequeue());
    }
}
