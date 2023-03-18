import edu.princeton.cs.algs4.Stack;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 StackWithMax.java
 *  Execution:    java-algs4 StackWithMax xxx
 *  *
 *  Expectation:
 *  *
 *  Description:    Stack with max.  30题
 *      Create a data structure that efficiently supports the stack operations
 *      (push and pop) and also a return-the-maximum operation.
 *      Assume the elements are real numbers so that you can compare them.
 *
 ******************************************************************************/

public class StackWithMax<Item> {
    private Stack<Item> stack;
    private Stack<Item> maximun;

    public StackWithMax() {
        stack = new Stack<Item>();
        maximun = new Stack<Item>();
    }

    public void push(Item item) {
        stack.push(item);
        // if (maximun.isEmpty() || (double) maximun.peek() < (double) item)
        //     maximun.push(item);
        if (maximun.isEmpty() || (double) maximun.peek() < (double) item)
            maximun.push(item);
        else
            maximun.push(maximun.peek());
    }

    public Item pop() {
        // if (stack.peek() == maximun.peek())
        //     maximun.pop();
        maximun.pop();
        return stack.pop();
    }

    public Item getMax() {
        return maximun.peek();
    }

    // 其余方法省略

    public static void main(String[] args) {

    }
}
