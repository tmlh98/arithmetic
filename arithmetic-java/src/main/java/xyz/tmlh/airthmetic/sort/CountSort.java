package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/**
 * <p>
 * 计数排序
 * </p>
 * <p>
 * 数组里有20个整数,取值范围为0-9,希望从小到大排序,时间复杂度O(n)
 * </p>
 *
 * @author TianXin
 * @since 2019年4月29日下午2:22:59
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray0and10(20);
        ArraysUtil.printArray(arr);
        show(arr);
        ArraysUtil.printArray(arr);
    }

    private static void show(int[] arr) {
        int[] index = new int[10];
        for (int i = 0; i < arr.length; i++) {
            index[arr[i]] ++;
        }
        
        int arrInd = 0;
        for (int i = 0; i < index.length; i++) {
            for (int j = 0; j < index[i]; j++) {
                arr[arrInd ++] = i;
            }
        }
    }

}
