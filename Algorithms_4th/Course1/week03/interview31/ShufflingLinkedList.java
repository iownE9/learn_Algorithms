import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 ShufflingLinkedList.java
 *  Execution:    java-algs4 ShufflingLinkedList ShufflingLinkedList.txt
 *  *
 *  Expectation:
 *  *
 *  Description:    Shuffling a linked list. 2.2.18
 *      Given a singly-linked list containing n items, rearrange the items
 *  uniformly at random. Your algorithm should consume a logarithmic
 *  (or constant) amount of extra memory and run in time proportional to
 *  nlogn in the worst case.
 *
 ******************************************************************************/

public class ShufflingLinkedList {

    public static void shuffle(Node<Integer> nodes, int n) {
        Node<Integer> start = nodes; // 保存起始点
        for (int sz = 1; sz < n; sz = sz + sz) {
            nodes = start;          // 回到原点
            for (int lo = 0; lo < n - sz; lo = lo + sz + sz) {
                int hi = Math.min(lo + sz + sz, n);
                merge(nodes, sz, hi - lo);
                // 在上一次基础上移动
                nodes = move(nodes, hi - lo);
            }
        }
    }

    // [0, sz) [sz, hi)
    private static void merge(Node<Integer> nodes, int mid, int hi) {
        Node<Integer> auxLeft = null;       // reverse 左半部分
        Node<Integer> auxRight = nodes;     // nodes 右半部分
        for (int i = 0; i < mid; i++) {
            Integer item = auxRight.getItem();
            auxLeft = new Node<>(auxLeft, item);
            auxRight = auxRight.getNext();
        }

        int i = 0, j = mid;
        for (int k = 0; k < hi; k++) {
            if (i == mid) break;    // 左半结束
            else if (j == hi) {     // 右半结束
                nodes.setItem(auxLeft.getItem());
                auxLeft = auxLeft.getNext();
                i++;
            }   // 随机选择
            else if (StdRandom.uniformDouble() < 0.5) {
                nodes.setItem(auxLeft.getItem());
                auxLeft = auxLeft.getNext();
                i++;
            }
            else {
                nodes.setItem(auxRight.getItem());
                auxRight = auxRight.getNext();
                j++;
            }
            nodes = nodes.getNext();
        }
    }

    private static Node<Integer> move(Node<Integer> nodes, int size) {
        for (int i = 0; i < size; i++) {
            nodes = nodes.getNext();
        }
        return nodes;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Node<Integer> nodes = null;

        for (int i : a) {
            nodes = new Node<>(nodes, i);
        }

        StdOut.println(nodes);
        shuffle(nodes, a.length);
        StdOut.println(nodes);

    }
}
