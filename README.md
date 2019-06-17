 

> 回忆一下以前撂下的知识吧!



#### 十大排序算法

- [x] 冒泡排序

- [x] 选择排序

- [x] 插入排序

- [x] 希尔排序

- [x] 快速排序

- [x] 归并排序

- [x] 计数排序

- [x] 桶排序

- [x] 基数排序

- [x] 堆排序

      ​


#### 排序算法时空复杂度比较

 **各种排序算法比较** 

| 排序方法   | 最好时间复杂度    | 最坏时间复杂度    | 平均时间复杂度    | 空间复杂度         | 稳定性  |
| ------ | ---------- | ---------- | ---------- | ------------- | ---- |
| 直接插入排序 | O(N)       | O(N2)      | O(N²)      | O(1)          | 稳定   |
| 希尔排序   | O(N*logn)  | O(N*log²n) | O(N*log²n) | O(1)          | 不稳定  |
| 直接选择排序 | O(N)       | O(N²)      | O(N²)      | O(1)          | 不稳定  |
| 堆排序    | O(N*log²N) | O(N*log²N) | O(N*log²N) | O(1)          | 不稳定  |
| 冒泡排序   | O(N)       | O(N²)      | O(N²)      | O(1)          | 稳定   |
| 快速排序   | O(N*log²N) | O(N*log²N) | O(N²)      | O(log²n)~O(n) | 不稳定  |
| 归并排序   | O(N*log²N) | O(N*log²N) | O(N*log²N) | O(n)          | 稳定   |
| 计数排序   | O(n + k)   | O(n+ k)    | O(n+ k)    | O(k)          | 稳定   |
| 桶排序    | O(n+ k)    | O(n+ k)    | O(n²)      | O(n+k)        | 稳定   |
| 基数排序   | O(n*k)     | O(n*k)     | O(n*k)     | O(n+k)        | 稳定   |

>  n : 数据规模、k ：桶的个数

注：

1、归并排序每次递归都要用到一个辅助表，长度与待排序的表长度相同，虽然递归次数是O(log2n)，但每次递归都会释放掉所占的辅助空间，

2、快速排序空间复杂度只是在通常情况下才为O(log2n)，如果是最坏情况的话，很显然就要O(n)的空间了。当然，可以通过随机化选择pivot来将空间复杂度降低到O(log2n)。

相关概念：

1、时间复杂度

​     时间复杂度可以认为是对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。

​     常见的时间复杂度有：常数阶O(1),对数阶O(log2n),线性阶O(n), 线性对数阶O(nlog2n),平方阶O(n2)

​     时间复杂度O(1)：算法中语句执行次数为一个常数，则时间复杂度为O(1),

2、空间复杂度

​    空间复杂度是指算法在计算机内执行时所需存储空间的度量，它也是问题规模n的函数

​    空间复杂度O(1)：当一个算法的空间复杂度为一个常量，即不随被处理数据量n的大小而改变时，可表示为O(1)

​    空间复杂度O(log2N)：当一个算法的空间复杂度与以2为底的n的对数成正比时，可表示为O(log2n) ax=N，则x=logaN，

​    空间复杂度O(n)：当一个算法的空间复杂度与n成线性比例关系时，可表示为0(n).

 

 
