package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * 归并排序
 */
/*
 * 归并排序是建立在归并操作上的一种有效的排序算法。
 * 该算法也是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 算法复杂度为O（N*logN）。
 */
/*
 * 1)拆分数组(递归)
 * 2)合并数组
 * 2.1)申请一块临时空间（temp数组）,先把左右两边的数据按照规则填充到temp数组
 * 直到左右两边的有序序列，有一边处理完毕为止
 * 2.2)将另一半剩余数据填充到temp数组(这时temp数组有序)
 * 2.3)将temp数组数据copy到原数组.
 */
public class MergeSort {
    
    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(1000 * 100);
        int[] temp = new int [arr.length];
        long startTime  = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length -1, temp);
        System.out.println("MergeSort executes time : " + (System.currentTimeMillis() -startTime));
//        ArraysUtil.printArray(arr);
    }
    
    //递归调用
    private static void mergeSort(int[] arr , int left, int right , int[] temp) {
        if(left < right) {
            int middle = (left + right)/2;
            mergeSort(arr, left, middle, temp);
            mergeSort(arr, middle + 1, right, temp);
            
            mergeArr(arr, left, middle, right, temp);
        }
    }
    
    
    /**
     * 合并数组
     * @param arr 待排序数组
     * @param left 左指针
     * @param middle 中间指针
     * @param right 右指针
     * @param temp 临时数组
     */
    private static void mergeArr(int[] arr , int left , int middle , int right , int[] temp) {
        // 两段分的数组可看作[]l -> middle [] middle + 1 -> right
        int l = left;
        int r = middle + 1;
        
        int t = 0;//指向temp 数组
        //填充temp数组，知道有一端完成
        while(l <= middle && r <= right) {
            if(arr[l] <= arr[r]) {
                temp[t] = arr[l ++];
            }else {
                temp[t] = arr[r ++];
            }
            t ++;
        }
        //填充剩余部分
        while(l <= middle) {
            temp[t] = arr[l];
            l ++;
            t ++;
        }
        
        while(r <= right) {
            temp[t] = arr[r];
            r ++;
            t ++;
        }
        //将有序部分copy 到原数组
        for (int i = 0; i < t; i++) {
            arr[left ++] = temp[i];
        }
        
    }

    
}













