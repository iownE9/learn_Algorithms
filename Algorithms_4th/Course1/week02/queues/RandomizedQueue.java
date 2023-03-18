/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: implement elementary data structures using arrays and linked lists,
 *               and to introduce you to generics and iterators.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 1;

    private int n;
    private Item[] a;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (n == a.length) resizeItem(2 * a.length);
        a[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniformInt(n);
        Item item = a[i];

        a[i] = a[n - 1];
        a[n - 1] = null;
        n--;
        if (n > 0 && n == a.length / 4) resizeItem(a.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniformInt(n);
        return a[i];
    }


    private void resizeItem(int capacity) {
        Item[] newA = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            newA[i] = a[i];

        a = newA;
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int num;
        private Item[] copy;

        public RandomizedQueueIterator() {
            num = n;
            copy = (Item[]) new Object[n];
            for (int j = 0; j < n; j++)
                copy[j] = a[j];
        }

        public boolean hasNext() {
            return num > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int j = StdRandom.uniformInt(num);

            Item item = copy[j];
            copy[j] = copy[num - 1];
            copy[num - 1] = null;
            num--;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();


        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        for (Integer i : randomizedQueue)
            StdOut.println(i);
        StdOut.println("========");

        for (Integer i : randomizedQueue)
            StdOut.println(i);
        StdOut.println("========");

        StdOut.println(randomizedQueue.sample());
        StdOut.println("========");

        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println("========");

        StdOut.println(randomizedQueue.isEmpty());
        StdOut.println(randomizedQueue.size());
    }
}
