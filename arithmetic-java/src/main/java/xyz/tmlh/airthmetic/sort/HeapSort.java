package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * 堆排序
 */
/*
 * 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，
 * 它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(1000 * 1000 * 10);
        long startTime  = System.currentTimeMillis();
        heapSort(arr);
//        ArraysUtil.printArray(arr);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void heapSort(int[] arr) {
        //1.把无序数组构建成大顶堆  
        for (int i = arr.length / 2 -1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            downAdjust(arr, i, arr.length);
        }
        
        int temp;
        //2. 循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
        for (int i = arr.length -1; i >=  0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //把剩余数组构建成大顶堆
            downAdjust(arr, 0 , i);
        }
    }

    // 1. 把无序数组构建成大顶堆 
    // 2. 循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
    /**
     * 下沉调整
     * @param arr 待调整的堆
     * @param index 要下沉的父节点
     * @param len 堆的有效大小
     */
    private static void downAdjust(int[] arr, int index, int len) {
        int temp = arr[index];
        int maxIndex = 2 * index + 1;// 假设当前结点的左孩子结点的值最大
        while (maxIndex < len) {
            if (maxIndex + 1 < len && arr[maxIndex] < arr[maxIndex + 1]) {
                maxIndex++;// 让指针指向右孩子节点
            }
            // 如果孩子结点小于父节点,没有必要进行交换
            if (arr[maxIndex] < temp) {
                break;
            }

            // 让父结点的值最大
            arr[index] = arr[maxIndex];
            // 此时原先父结点的指针指向孩子结点
            index = maxIndex;
            // 把左孩子当作父节点进行操作
            maxIndex = 2 * maxIndex + 1;
        }
        // 改变孩子结点的值
        arr[index] = temp;
    }
    
}
