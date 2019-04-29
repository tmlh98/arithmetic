package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/**
 * <p>
 *   选择排序
 * </p>
 *
 * @author TianXin
 * @since 2019年4月29日下午5:04:39
 */
public class SelectionSort {
    /*
     *  选择排序（Selection sort）是一种简单直观的排序算法。
     *  它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，
     *  直到全部待排序的数据元素排完。 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，
     *  导致第一个5挪动到第二个5后面）。 选择排序只是比冒泡排序优化了一点点，比较的次数没有变，但是减少了交换的次数
     */
    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(20);
        sort(arr);
        ArraysUtil.printArray(arr);
    }

    private static void sort(int[] arr) {
        int index;//记录最新元素的位置
        for (int i = 0; i < arr.length; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[index] > arr[j]) {
                    index = j;
                }
            }
            ArraysUtil.swap(arr, index, i);
        }
        
    }
    

}
