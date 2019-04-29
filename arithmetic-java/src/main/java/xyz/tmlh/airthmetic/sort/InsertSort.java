package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/**
 * <p>
 * 插入排序
 * </p>
 *
 * @author TianXin
 * @since 2019年4月29日下午5:04:39
 */
public class InsertSort {
    /*
     * 插入排序(Insert Sort)将待排序的数组分为2部分：有序区，无序区。
     * 其核心思想是每次取出无序区中的第一个元素，插入到有序区中。 
     * 有序与无序区划分，就是通过一个变量标记当前数组中，前多少个元素已经是局部有序了。
     * 在排序开始的时候，把数组的第1个元素当成有序区(即有序区只有一个元素)，其余的所有元素当做无序区。
     * 之后在往有序区插入无序去第一个元素值时，有序区中比这个值小(或者大)的元素都要右移一个位置。
     * 右移并不会覆盖的数组中已有的数据项值，因为我们总是取无序区中的第一个元素插入，右移也只是覆盖了我们取出的这个元素的位置而已。
     * 当无序区为空时，排序完成。
     * 插入排序根据具体实现方式又分为：直接插入排序，二分插入排序（又称折半插入排序），链表插入排序，希尔排序（又称缩小增量排序）。
     * 属于稳定排序的一种（通俗地讲，就是两个相等的数不会交换位置） 。
     */
    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(20);
        
        ArraysUtil.printArray(arr);
        sort(arr);
        ArraysUtil.printArray(arr);
    }

    private static void sort(int[] arr) {
        //tmp: 保存待插入的元素 ,index:待插入的位置
        int tmp ; 
        for (int i = 1; i < arr.length; i++) {
            int index = -1;
            tmp = arr[i];
            //寻找可插入位置
            for (int j = 0; j < i; j++) {
                if(tmp < arr[j]) {
                    index = j;//更新位置
                    break;
                }
            }
            
            if(index != -1) {
                //元素后移
                for (int k = i; k > index; k--) {
                    arr[k] = arr[k -1];
                }
                //插入元素
                arr[index] = tmp;
            }
        }
    }

}
