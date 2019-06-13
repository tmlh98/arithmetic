package xyz.tmlh.airthmetic.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * 桶排序
 */
/*
 * 桶排序（Bucket sort）或所谓的箱排序，是一个排序算法，工作的原理是将数组分到有限数量的桶里。
 * 每个桶再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序），
 * 最后依次把各个桶中的记录列出来记得到有序序列。
 * 桶排序是鸽巢排序的一种归纳结果。当要被排序的数组内的数值是均匀分配的时候，桶排序使用线性时间（Θ(n)）。 
 * 但桶排序并不是比较排序，他不受到O(n log n)下限的影响。
 */
/*
 * 平均时间复杂度：O(n + k) 最佳时间复杂度：O(n + k) 最差时间复杂度：O(n ^ 2) 空间复杂度：O(n * k) 稳定性：稳定
 */
public class BucketSort {

    public static void main(String[] args) {
        double[] arr = ArraysUtil.genDoubleArray(1000 * 1000);
        long startTime = System.currentTimeMillis();
        bucketSort(arr, 10);
        long endTime = System.currentTimeMillis();
        
        System.out.println("耗时:" + (endTime - startTime) + "ms");
    }

    /**
     * @param arr 排序的数组
     * @param number 桶的数量(在内存足够的情况下,理论上桶越大速度越快[??????????????])
     */
    public static void bucketSort(double[] arr, int number) {
        // 1.得到数列的最大值和最小值，并算出差值、区间跨度
        double max = arr[0];
        double min = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        
        double d = max - min;
        // 区间跨度 = （最大值-最小值）/（桶的数量 - 1）
        double spacing = d / (number - 1);
        
        // 2.初始化桶
        List<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(number);
        for (int i = 0; i < arr.length; i++) {
            bucketList.add(new LinkedList<Double>());
        }
        
        // 3.遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (int)((arr[i] - min) / spacing);//计算桶的位置
            bucketList.get(index).add(arr[i]);
        }
        
        // 4.对每个桶内部进行排序
        for (LinkedList<Double> linkedList : bucketList) {
            Collections.sort(linkedList);
        }
        
        // 5.输出全部元素
        int ind = 0;
        for (LinkedList<Double> linkedList : bucketList) {
            for (Double num : linkedList) {
                arr[ind++] = num;
            }
        }
    }

}
