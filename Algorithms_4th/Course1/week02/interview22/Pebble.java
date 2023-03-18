/******************************************************************************
 *
 *  Page:
 *  Compilation:  javac-algs4 Pebble.java
 *  Execution:    java-algs4 Pebble xxx
 *  *
 *  Expectation:
 *  *
 *  Description:
 *
 ******************************************************************************/

public class Pebble {// implements Comparable<Pebble> {
    private String color;

    public Pebble(String color) {
        this.color = color;
    }

    // public int compareTo(Pebble that) {
    //     return this.color.compareTo(that.color);
    // }

    public String getColor() {
        return color;
    }

    public String toString() {
        return color;
    }

    public static void main(String[] args) {

    }
}
