package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * 基数排序
 */
/*
 * 基数排序(Radix Sort)是桶排序的扩展，它的基本思想是：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 具体做法是：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 */
public class RadixSort {

    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214, 154, 63, 616};
        int[] arr = ArraysUtil.genArray(1000 * 1000 * 10);
        long startTime  = System.currentTimeMillis();
        radixSort(arr);
        System.out.println(System.currentTimeMillis() -startTime);
//        ArraysUtil.printArray(arr);
    }

    public static void radixSort(int[] arr) {
        // 求出数组种最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        
        int len = String.valueOf(max).length();
        
        //初始化10个桶
        int[][] buckets = new int [10][arr.length];
        //用来记录每个桶元素的个数
        int[] bucketIndex = new int[buckets.length];
        
        // 从个位开始 对数组进行排序
        for (int i = 0, exp = 1; i < len; i++, exp *= 10) {
            
            for (int j = 0 ; j < arr.length; j++ ) {
                int index = arr[j] / exp % 10;//求对应位数的值
                buckets[index][bucketIndex[index] ++] = arr[j];//填充桶的数据
            }
            
            //将10个桶的数据顺序copy到原数组
            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                for (int k = 0; k < bucketIndex[j]; k++) {
                    arr[index ++ ] = buckets[j][k];
                }
                //坐标重置
                bucketIndex[j] = 0;
            }
            
        }
        
    }
}
