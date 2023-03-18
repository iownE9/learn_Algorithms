# Note

<https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php>

## 01-80

两处空指针异常
java.lang.NullPointerException
    Deque.removeFirst(Deque.java:86)
    first.previous = null;  // 01

java.lang.NullPointerException
    Deque.removeLast(Deque.java:99)
    last.next = null; // 01

## 02-100

## 待解决

内存限制：
check that maximum size of any or Deque or RandomizedQueue object created is equal to k
