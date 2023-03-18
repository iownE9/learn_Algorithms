import edu.princeton.cs.algs4.Stack;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Ex12.java
 *  Execution:    java-algs4 Ex12 xxx
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Ex12 {

    public static Stack<String> copy(Stack<String> stringStack) {
        Stack<String> res = new Stack<String>();
        Stack<String> temp = new Stack<String>();

        for (String item : stringStack)
            temp.push(item);

        for (String item : temp)
            res.push(item);

        return res;
    }

    public static void main(String[] args) {

    }
}
