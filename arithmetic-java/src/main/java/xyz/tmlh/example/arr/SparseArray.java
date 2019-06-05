package xyz.tmlh.example.arr;

/*
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。  
 * 
 * 稀疏数组的处理方法是: 记录数组一共有几行几列，有多少个不同的值（列固定为三方，行可变化）
 * 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 */
/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][2] = 2;
        chessArr[4][5] = 2;
        System.out.println("原始的二维数组~~");
        print(chessArr);
        System.out.println("稀疏数组~~~~");
        print(sourceArrToSparseArr(chessArr));
        System.out.println("原始数组数组~~~~");
        print(sparseArrToDoubleArr(sourceArrToSparseArr(chessArr)));
    }
    
    /**
     * 二维数组转稀疏数组
     */
    public static int[][] sourceArrToSparseArr(int[][] sourceArr) {
        int sum = 0;// 记录二维数组的有效元素
        for (int i = 0; i < sourceArr.length; i++) {
            for (int j = 0; j < sourceArr[i].length; j++) {
                if (sourceArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = sourceArr.length;
        sparseArr[0][1] = sourceArr[0].length;
        sparseArr[0][2] = sum;

        int index = 1;// 位置
        for (int i = 0; i < sourceArr.length; i++) {
            for (int j = 0; j < sourceArr[i].length; j++) {
                if (sourceArr[i][j] != 0) {
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = sourceArr[i][j];
                    index++;
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转二维数组
     */
    public static int[][] sparseArrToDoubleArr(int[][] sparseArr) {
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }

    private static void print(int[][] chessArr) {
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
