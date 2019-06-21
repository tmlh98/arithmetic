package xyz.tmlh.airthmetic.example.recursion;

import java.util.Arrays;

/*
 * 八皇后问题(回溯)
 */
/*
 * 1)从棋盘的第一行开始，从第一个位置开始，依次判断当前位置是否能够放置皇后，
 * 判断的依据为：同该行之前的所有行中皇后的所在位置进行比较，如果在同一列，
 * 或者在同一条斜线上（斜线有两条，为正方形的两个对角线），都不符合要求，继续检验后序的位置。
 * 2)如果该行所有位置都不符合要求，则回溯到前一行，改变皇后的位置，继续试探。
 * 3)如果试探到最后一行，所有皇后摆放完毕，则直接打印出 8*8 的棋盘(可以用一维数组表示)。
 * 最后将棋盘恢复原样，避免影响下一次摆放。
 */
public class Queen8 {

    private static final int QUEEN_SIZE = 8;
    
    private int[] arr = new int[QUEEN_SIZE] ;
    
    private int count = 0;
    
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.findQueen(0);
        System.out.printf("共有%d种摆法!" , queen8.count);
    }

    /*
     * n表示第几个皇后
     */
    private void findQueen(int n) {
        if(n == QUEEN_SIZE) {
            count ++;
            printChess();
            return;
        }
        
        //依次放入皇后
        for (int i = 0; i < arr.length; i++) {
            arr[n] = i; //将第n个皇后放到第i个位置
            if(!judge(n)) {
               //如果不冲突希望找到第n+1个皇后的正确位置
               findQueen(n + 1); 
            }
            //如果冲突，当前皇后右移
        }
        
    }

    /*
     * 判断是否冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //纵坐标和斜对是否冲突
            if(arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n] - arr[i])) {
                return true;
            }
        }
        return false;
    }
    
    private void printQueen() {
        System.out.println(Arrays.toString(arr));
    }
    
    //打印二维数组
    private void printChess(){
        System.out.printf("第    %d 种摆法\n" , count);
        
        for (int i = 0; i < QUEEN_SIZE; i++) {
            for (int j = 0; j < QUEEN_SIZE; j++) {
                if(arr[j] == i) {
                    System.out.print("o ");
                }else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        
    }
    
}

