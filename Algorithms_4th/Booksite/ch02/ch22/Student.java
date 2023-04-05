import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Student.java
 *  Execution:    java-algs4 Student Student.txt
 *  *
 *  Expectation:
 *  *
 *  Description: /week03/22Mergesort.pdf-46
 *
 ******************************************************************************/

public class Student {
    public static final Comparator<Student> BY_NAME = new ByName();
    public static final Comparator<Student> BY_SECTION = new BySection();
    private final String name;
    private final int section;

    public Student(String name, int section) {
        this.name = name;
        this.section = section;
    }

    private static class ByName implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.name.compareTo(w.name);
        }
    }

    private static class BySection implements Comparator<Student> {
        public int compare(Student v, Student w) {
            // this technique works here since no danger of overflow
            return v.section - w.section;
        }
    }

    public String toString() {
        return name + " " + section;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();
        Student[] a = new Student[n];
        for (int i = 0; !in.isEmpty() && i < n; i++) {
            a[i] = new Student(in.readString(), in.readInt());
        }
        for (Student item : a)
            StdOut.println(item);
        StdOut.println("======");

        Arrays.sort(a, Student.BY_NAME);
        // MergeX.sort(a, Student.BY_NAME);

        for (Student item : a)
            StdOut.println(item);
        StdOut.println("======");

        Arrays.sort(a, Student.BY_SECTION);
        // MergeX.sort(a, Student.BY_SECTION);

        for (Student item : a)
            StdOut.println(item);
    }
}
