package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.PrintArraysUtil;

/**
 * 冒泡
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println("source arr");
        PrintArraysUtil.printArrays(arr);
        sort2(arr);
        System.out.println("new arr");
        PrintArraysUtil.printArrays(arr);

        System.out.println("-----------------------");
        int[] arr3 = new int[] {3, 4, 2, 1, 5, 6, 7, 8};
        sort3(arr3);
        PrintArraysUtil.printArrays(arr3);
    }

    /*
     * 当第六轮数组已经有序，我们的排序算法仍然继续执行第七轮、第八轮
     */
    private static void sortArr(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /*
     * 优化排序算法
     */
    private static void sort2(int arr[]) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {

            // 有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }
    /*
     * 优化排序算法
     */
    private static void sort3(int arr[]) {
        int temp = 0;
        int lastIndex = 0;
       //无序数列的边界，每次比较只需要比到这里为止
        int jborder = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            // 有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < jborder; j++) {
                PrintArraysUtil.printArrays(arr);
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //记录有变化元素的位置
                    lastIndex = j;
                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            jborder = lastIndex;
            if (isSorted) {
                break;
            }
        }
    }

}
