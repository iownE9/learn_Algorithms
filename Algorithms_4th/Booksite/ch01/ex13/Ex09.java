import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Ex09.java
 *  Execution:    java-algs4 Ex09
 *  Input:        1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 *  ((1 + 2) * ((3 - 4) * (5 - 6)))
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Ex09 {

    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();

        while (!StdIn.isEmpty()) {
            // Read token, push if operator.
            String s = StdIn.readString();
            if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);

            else if (s.equals(")")) {
                String op = ops.pop();
                String v = vals.pop();
                if (op.equals("+")) vals.push("(" + vals.pop() + " + " + v + ")");
                else if (op.equals("-")) vals.push("(" + vals.pop() + " - " + v + ")");
                else if (op.equals("*")) vals.push("(" + vals.pop() + " * " + v + ")");
                else if (op.equals("/")) vals.push("(" + vals.pop() + " / " + v + ")");

            } // Token not operator or paren: push value.
            else vals.push(s);
        }
        StdOut.println(vals.pop());
    }
}
