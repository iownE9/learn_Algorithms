/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Node.java
 *  Execution:    java-algs4 Node xxx
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Node<Item> {
    private Item item;
    private Node<Item> next;

    public Node(Node<Item> next, Item item) {
        this.item = item;
        this.next = next;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Node<Item> getNext() {
        return next;
    }

    public Item getItem() {
        return item;
    }

    public String toString() {
        Node<Item> that = this;
        StringBuilder buf = new StringBuilder();
        while (that != null) {
            buf.append(that.item);
            that = that.next;
        }
        return buf.toString();
    }

    public static void main(String[] args) {

    }
}
