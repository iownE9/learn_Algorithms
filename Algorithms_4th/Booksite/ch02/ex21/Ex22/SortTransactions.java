package Ex22;

/******************************************************************************
 *  Compilation:  javac Ex22/SortTransactions.java
 *  Execution:    java-algs4 Ex22/SortTransactions < Ex22/tinyBatch.txt
 *  Dependencies: StdOut.java
 *  Data file:    https://algs4.cs.princeton.edu/21elementary/tinyBatch.txt
 *
 ******************************************************************************/

import Ex21.Transaction;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SortTransactions {
    public static Transaction[] readTransactions() {
        Queue<Transaction> queue = new Queue<Transaction>();
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);
            queue.enqueue(transaction);
        }

        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++)
            transactions[i] = queue.dequeue();

        return transactions;
    }

    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        // Arrays.sort(transactions);
        Shell.sort(transactions);
        for (int i = 0; i < transactions.length; i++)
            StdOut.println(transactions[i]);
    }
}
