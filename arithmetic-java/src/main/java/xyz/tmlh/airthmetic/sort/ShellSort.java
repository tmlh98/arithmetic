package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * （1）希尔排序又称为缩小增量排序，是1959年D·L·Shell提出来的。该方法的基本思想是：
 *      设待排序元素序列有n个元素，首先取一个整数increment（小于n）作为间隔将全部元素分为increment个子序列，
 * 所有距离为increment的元素放在同一个子序列中，在每一个子序列中分别实行直接插入排序。然后缩小间隔increment，
 * 重复上述子序列划分和排序工作。直到最后取increment=1，将所有元素放在同一个子序列中排序为止。
 * （2）由于开始时，increment的取值较大，每个子序列中的元素较少，排序速度较快，到排序后期increment取值逐渐变小，
 * 子序列中元素个数逐渐增多，但由于前面工作的基础，大多数元素已经基本有序，所以排序速度仍然很快。
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(1000 * 1000 * 10);
        long startTime  = System.currentTimeMillis();
        shellSort(arr);
        System.out.println("shellSort executes time : " + (System.currentTimeMillis() -startTime));
    }

    private static void shellSort(int[] arr) {
         for (int increment = arr.length / 2 ; increment > 0 ; increment = increment /2) {//计算增量
             
             for (int i = increment; i < arr.length; i++) {//分组
                //使用插入排序
                int j = i;
                int temp = arr [j];
                if(arr[j] < arr[j - increment]) {
                    while (j - increment >= 0 && temp < arr[j - increment]) {
                        //移动
                        arr[j] = arr[j - increment];
                        j -= increment;
                    }
                    arr[j] = temp;
                }
                
             }
             
         }
         
    }
    
    
}
