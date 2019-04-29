package xyz.tmlh.airthmetic.util;

import java.util.Arrays;

/**  
 * Created by TianXin on 2019年4月28日. 
 */
public class ArraysUtil {

    public static void printArray(int [] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    public static void printArray(int [][] arr) {
        System.out.println(arr);
        /*for (int i = 0; i < arr.length; i++) {
             for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+"\t" );
            }
            System.out.println();
        }*/
    }
    public static int[] genArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        return arr;
    }
    
    /**
     * 0-10
     */
    public static int[] genArray0and10(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 10);
        }
        return arr;
    }
    
    
    
    
}
