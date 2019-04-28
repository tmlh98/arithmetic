package xyz.tmlh.airthmetic.util;

/**  
 * Created by TianXin on 2019年4月28日. 
 */
public class PrintArraysUtil {

    public static void printArrays(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
    
    public static void printArrays(int [][] arr) {
        for (int i = 0; i < arr.length; i++) {
             for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+"\t" );
            }
            System.out.println();
        }
    }
    
    
}
