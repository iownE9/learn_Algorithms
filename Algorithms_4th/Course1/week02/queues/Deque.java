/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: implement elementary data structures using arrays and linked lists,
 *               and to introduce you to generics and iterators.
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> oldFirst = first;
        first = new Node<Item>();
        if (oldFirst != null)
            oldFirst.previous = first;

        first.item = item;
        first.next = oldFirst;
        first.previous = null;

        if (last == null) last = first;
        n++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node<Item> oldLast = last;
        last = new Node<Item>();
        if (oldLast != null)
            oldLast.next = last;

        last.item = item;
        last.next = null;
        last.previous = oldLast;

        if (first == null) first = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        if (last == first)
            last = null;

        Item item = first.item;
        first = first.next;
        // first.previous = null;  // 01
        if (first != null) first.previous = null; // 02
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (first == last)
            first = null;

        Item item = last.item;
        last = last.previous;
        // last.next = null; // 01
        if (last != null) last.next = null; // 02
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(6);
        deque.addFirst(2);
        deque.addFirst(2);
        deque.addLast(6);

        for (int item : deque)
            StdOut.println(item);
        StdOut.println("=======");

        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println("=======");

        for (int item : deque)
            StdOut.println(item);

        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());
    }
}
