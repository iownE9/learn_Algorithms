import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 QueueWithTwoStacks.java
 *  Execution:    java-algs4 QueueWithTwoStacks < tobe.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    Queue with two stacks. 09题
 *
 *      Implement a queue with two stacks so that each queue operations
 *      takes a constant amortized number of stack operations.
 *
 ******************************************************************************/

public class QueueWithTwoStacks<Item> {
    private Stack<Item> in;
    private Stack<Item> out;

    public QueueWithTwoStacks() {
        in = new Stack<Item>();
        out = new Stack<Item>();
    }

    public void enqueue(Item item) {
        in.push(item);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());

        return out.pop();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    public int size() {
        return in.size() + out.size();
    }

    public static void main(String[] args) {
        // Create a queue and enqueue/dequeue strings.
        QueueWithTwoStacks<String> q = new QueueWithTwoStacks<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }

        StdOut.println("(" + q.size() + " left on queue)");
    }
}
