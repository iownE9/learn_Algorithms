import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 EggDrop.java
 *  Execution:    java-algs4 EggDrop xxx
 *  *
 *  Expectation:
 *  *
 *  Description:    Egg drop
 *          Suppose that you have an n-story building (with floors 1 through n)
 *      and plenty of eggs. An egg breaks if it is dropped from floor
 *      T or higher and does not break otherwise. Your goal is to devise
 *      a strategy to determine the value of T given the following limitations
 *      on the number of eggs and tosses:
 *
 *      Version 0: 1 egg, ≤T tosses.
 *      Version 1: ∼1lgn eggs and ∼1lgn tosses.
 *      Version 2: ∼lgT eggs and ∼2lgT tosses.
 *      Version 3:  2 eggs and ~2*sqrt(T) tosses.
 *      Version 4:  2 eggs and ≤c*sqrt(T) tosses for some fixed constant c.
 *
 ******************************************************************************/

public class EggDrop {
    // Version 0: 1 egg, ≤T tosses.
    public static void version0(int n, int T) {
        int tosses = 0;
        int eggs = 0;

        for (int i = 1; i <= n; i++) {
            tosses++;
            if (i == T) {
                eggs++;
                break; // T - 1 没碎，T 碎了
            }
        }

        StdOut.println("eggs: " + eggs + " tosses: " + tosses);
    }

    // Version 1: ∼1lgn eggs and ∼1lgn tosses.
    public static void version1(int n, int T) {
        int tosses = 0;
        int eggs = 0;
        int lo = 1;
        int hi = n;

        while (lo <= hi) {
            tosses++;
            int mid = lo + (hi - lo) / 2;

            if (mid < T) lo = mid + 1;// 没碎
            else { // mid >= T 碎了
                eggs++;
                hi = mid - 1;
            }
        }
        StdOut.println("eggs: " + eggs + " tosses: " + tosses);
    }

    // Version 2: ∼lgT eggs and ∼2lgT tosses.
    public static void version2(int n, int T) {
        int tosses = 0;
        int eggs = 0;

        tosses++;
        if (1 < T) {
            int hi = 2;
            int lo = hi;
            for (; hi <= n; ) {
                lo = hi;
                hi = hi * hi;

                tosses++;
                if (hi >= T) {
                    eggs++;
                    break;
                }
            }
            // lo * lo > n >= T   lo < T
            // lo*lo >= T  lo < T 故 hi-lo < T*T  -> 2lgT
            while (lo <= hi) {
                tosses++;
                int mid = lo + (hi - lo) / 2;

                if (mid < T) lo = mid + 1;// 没碎
                else { // mid >= T 碎了
                    eggs++;
                    hi = mid - 1;
                }
            }
        }

        else { // 特殊情况
            eggs = 1;
        }
        StdOut.println("eggs: " + eggs + " tosses: " + tosses);
    }

    // Version 3:  2 eggs and ~2*sqrt(T) tosses.
    public static void version3(int n, int T) {
        int tosses = 0;
        int eggs = 0;

        int base = 0;
        while (T != 0) {
            int i = 1;
            for (; i * i <= n; i++) {
                tosses++;
                if (i * i >= T) {
                    eggs++;
                    break;
                }
            } // 一个 sqrt(T)
            i--;
            // i * i + 2i + 1 > n >= T  > i * i
            // 故缩小到 [1, n - i*i]

            // i * i + 2i + 1 >= T  > i * i
            // 故缩小到 [1, 2i + 1]  worse case
            base = base + i * i;
            T = T - i * i;
            n = n - i * i;
        }
        // 鸡蛋碎的个数超过了2  Error
        StdOut.println("eggs: " + eggs + " tosses: " + tosses);
    }

    // Version 4:  2 eggs and ≤c*sqrt(T) tosses for some fixed constant c.
    public static void version4(int n, int T) {
        int tosses = 0;
        int eggs = 0;

        StdOut.println("eggs: " + eggs + " tosses: " + tosses);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);


        StdOut.println("Version 0: 1 egg, ≤T tosses. ");
        version0(n, T);
        StdOut.println();

        StdOut.println("Version 1: ∼1lgn eggs and ∼1lgn tosses.");
        version1(n, T);
        StdOut.println();

        StdOut.println("Version 2: ∼lgT eggs and ∼2lgT tosses.");
        version2(n, T);
        StdOut.println();

        StdOut.println("Version 3:  2 eggs and ~2*sqrt(T) tosses.");
        version3(n, T);
        StdOut.println();

        StdOut.println(
                "Version 4:  2 eggs and ≤c*sqrt(T) tosses for some fixed constant c.");
        version4(n, T);


    }
}
