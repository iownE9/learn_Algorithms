# colliner

根据要求 -> 选用排序方法

思路清晰，过程繁琐
每个特定功能划分成单独函数，模块化，方便隔离调bug

建立git仓库，方便回溯 分阶段保存 模块化测试

**做题三大宝：
多举例子找规律
分类讨论
临界分析**


## 70

### checkstyle

local variable 'N' must start with a lowercase letter and use camelCase
Do not use the letter 'l' as a local variable name. It is hard to distinguish from the number '1'

## Design

FastCollinearPoints.java:11:1: Defining a nested class in this program suggests poor design. [Design]
设置一个内部类 -> bad idea

## Point类

未按题意要求实现 BIG ERROR!!!
题意都没读清楚

## 去重时 用toString

if (new_.toString().equals(lineSegments[i].toString())) {

Test 7: check for fragile dependence on return value of toString()

It is bad style to write code that depends on the particular format of
the output from the toString() method, especially if your reason for
doing so is to circumvent the public API (which intentionally does not
provide access to the x- and y-coordinates).

## 题意要求

any entry in array is null && duplicate

Test 13: throws an exception if either the constructor argument is null
 or any entry in array is null

Test 14: check that the constructor throws an exception if duplicate points

## 临界情况考虑不全面

Test 6: points from a file with fewer than 4 points

## FastCollinearPoints 修改了 points

Test 14: check that data type does not mutate the constructor argument

## 82

改为通过点在points[]数组的索引来排序，定位具体点

private Point[] copyPoints; // 方便根据索引寻找具体点
copyPoints = points;        // 保持引用

使用p.slopeOder()来进行点大小判定

理解了Double.NEGATIVE_INFINITY的作用

保存线段端点索引，进而进行去重

## 85

解决了 82分的这个问题
check that data type is immutable by testing whether each method
returns the same value, regardless of any intervening operations

说实话，现在还没明白为啥？

copyPoints 由复制引用，改为深度copy
这样依旧不行
还要：
每求得一条线段，动态添加进LineSegment[]
最后 API 写出这样
public LineSegment[] segments() {
    LineSegment[] ls = new LineSegment[numLine];
    for (int i = 0; i < numLine; i++) {
        ls[i] = lineSegments[i];
    }
    return ls;
}

之前的 最后根据索引构造还不行
// the line segments
public LineSegment[] segments() {
    LineSegment[] lineSegments = new LineSegment[numLine];
    for (int i = 0, j = 0; i < numLine; i++, j += 2) {
        lineSegments[i] = new LineSegment(copyPoints[lineIndex[j]],
                                          copyPoints[lineIndex[j + 1]]);
    }
    return lineSegments;
}

## 90

Test 17: check that the constructor throws an exception if duplicate points
// 轮番 求角度
// for (int i = 0; i < len - 3; i++) {
// 为了通过最后三个点相互有重复的测试
for (int i = 0; i < len - 1; i++) {

其实 len-3 比 len-1 节省的性能小到忽略不计
临界分析不全面


解决性能问题
n^2 次求slope -> 1/2 * n^2
代价是：去重逻辑变繁杂
端点相同舍弃 -> 平行、包含、相交分类讨论



剩余性能问题：
compareTo()  调用次数太多
// 共线 求两端
private void sort(int[] lines) 使用 compareTo()
推测：共线的点太多，远远大于4个，插排性能低


slopeTo() + 2*compare() 调用次数太多
sortSlope() 这个归并排序 使用 p0.slopeOrder()
继续优化归并


solution:
实现通用的插排、归并排序，一套代码既能按角度排，又能按坐标排

Point类既实现 Comparable 用compareTo()完成坐标排序
又用内部类实现 Comparator 用compare(Point o1, Point o2)完成以该点为基准点的斜率排序

但这样，参数类型不一样，接口回调还用不上
1）多态 两套代码
2）抽象出不同部分,函数当参数



## 95

最开始嫌传参数麻烦，设置了很多私有属性，让私有方法共享变量
现在为了让sort()“通用”，变得又全传参数了

但真正的通用应该是标准库的
sort(Comparable[] src, Comparable[] dst, int lo, int hi)
sort(Object[] src, Object[] dst, int lo, int hi, Comparator comparator)
但我直接绑定了Point类，Comparator<Point>;又用了int[]而不是Comparable[]

a[] 与 aux[] 相互交替角色的未采用 -> 性能判断只有内循环的调用次数有关

减少slopeTo()的调用 -> 减少slopeOrder().compare()的调用 -> 空间换时间
-> 保存角度的值 -> 打破了Comparator的抽象实现 -> 先95分


## 95-2

(1) 利用归并排序的stable性质

1. 先按坐标排序
2. copy一份，轮流按基准点的斜率排序

斜率相同的部分，坐标也依次增大

if (!less(a[mid + 1], a[mid], p0)) -> 满足 stable
if (less(a[mid], a[mid + 1], p0)) -> 不满足 stable


(2) indexSort
我之前绕来绕去，这不就是标准库里的indexSort()吗
艹

性能竟然比上一个还弱点，gg