# Percolation

<https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php>

解题思路：空间换时间

## No.1 75分

暴力求解，过分依赖 uf

三个暴力 for 循环
[INFO] Percolation.java:81: Using a loop in this method might be a performance bug. [Performance]
[INFO] Percolation.java:97: Using a loop in this method might be a performance bug. [Performance]
[INFO] Percolation.java:99: Using a loop in this method might be a performance bug. [Performance]

OperationCountLimitExceededException
    Number of calls to methods in WeightedQuickUnionUF exceeds limit: 100000000

一个内存浪费 阻塞标识
[WARN] Percolation.java:12:13: Can you use the type 'boolean[]' instead of 'int[]'? [Design]

计算方差取巧于书本73页 1.2.18 Accumulator

书写规范
常量使用不规范 应 private static final double FACTOR = 1.96;

## No.2 82分

### 改进 空间换时间

将 tops bottoms 顶层和最底层的 Root 抽离出来并存储

依托 是否与顶层tops同 Root 进行
1 isFull()

2 percolates()

### 问题

依旧存在 for 循环 性能不足 超时

## No.3 75分

### 改进

摆脱对 是否同 Root 的束缚:
private boolean[] block;
private boolean[] isfull;

借助 union 相邻传导 真值

### 问题 栈溢出

栈严重溢出
java.lang.StackOverflowError
Percolation.fullToAdjoin(Percolation.java:61)

## No.4 92分

优化递归调用 和 设置 isPercolation 标识

栈溢出
java.lang.StackOverflowError
Percolation.fullToAdjoin(Percolation.java:61)

uf.union()调用次数太多


## No.5 尾递归优化

## 书写时遇到的问题

都在各阶段代码注释里
