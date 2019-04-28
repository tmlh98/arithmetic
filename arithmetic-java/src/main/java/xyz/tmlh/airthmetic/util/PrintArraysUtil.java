package xyz.tmlh.airthmetic.util;

/**  
 * Created by TianXin on 2019年4月28日. 
 */
public class PrintArraysUtil {

    public static void printArrays(int [] arr) {
        System.out.println("--------------------print start----------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("\n--------------------print end----------------");
    }
    
    public static void printArrays(int [][] arr) {
        System.out.println("--------------------print start----------------");
        for (int i = 0; i < arr.length; i++) {
             for (int j = 0; j < arr.length; j++) {
                System.out.println(arr[i][j]+"\t" );
            }
            System.out.println();
        }
        System.out.println("\n--------------------print end----------------");
    }
    
    
}
