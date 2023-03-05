import edu.princeton.cs.algs4.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page: 88
 *  Compilation:  javac-algs4 ResizingArrayQueueTest.java
 *  Execution:    java-algs4 ResizingArrayQueueTest < tobe.txt
 *
 *  % java ResizingArrayQueue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

public class ResizingArrayQueueTest {
    public static void main(String[] args) {
        ResizingArrayQueue<String> q;
        q = new ResizingArrayQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }

        StdOut.println("(" + q.size() + " left on queue)");
    }
}
