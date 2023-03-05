import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page: 95
 *  Compilation:  javac-algs4 QueueTest.java
 *  Execution:    java-algs4 QueueTest < tobe.txt
 *
 ******************************************************************************/

public class QueueTest {
    public static void main(String[] args) {
        // Create a queue and enqueue/dequeue strings.
        Queue<String> q = new Queue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }

        StdOut.println("(" + q.size() + " left on queue)");
    }
}
