package xyz.tmlh.airthmetic.util;

import java.util.Arrays;

/**
 * <p>
 *   ArraysUtil
 * </p>
 *
 * @author TianXin
 * @since 2019年4月29日下午3:23:46
 */
public class ArraysUtil {

    public static void printArray(int [] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    public static void printArray(int [][] arr) {
        System.out.println(arr);
    }
    
    /**
     * 随机生成一个数组[0-100)
     */
    public static int[] genArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        return arr;
    }
    
    /**
     *  随机生成一个数组[0-10)
     */
    public static int[] genArray0and10(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 10);
        }
        return arr;
    }
    
    /**
     * 交换两个数组的位置
     */
    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
    
}