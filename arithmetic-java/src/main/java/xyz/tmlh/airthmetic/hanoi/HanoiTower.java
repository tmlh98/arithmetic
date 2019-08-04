package xyz.tmlh.airthmetic.hanoi;

public class HanoiTower {
    
    private static int count = 0;
    
    public static void main(String[] args) {
        hanoi( 3 , 'X' , 'Y' , 'Z');
        System.out.println("-----------");
    }

    /**
     * 
     * @param num 盘子的数量
     * @param x   x柱子
     * @param y   y柱子
     * @param z   z柱子  
     */
    private static void hanoi(int num, char x, char y, char z) {
         if(num == 1) {
             //只有一个盘子将盘子从x直接移到z
             move(num , x , z);
             return;
         }
         
         //将num -1 个盘子以z为辅助从x移动到y
         hanoi(num - 1, x, z, y);
         //移动n号盘从x到z柱子上
         move(num , x, z);
         //将num -1 个盘子以x为辅助从y移动到z
         hanoi(num -1, y, x, z);
    }

    private static void move(int no ,char x, char z) {
        System.out.println( ++count + ":\t第 "+ no+ " 个 盘子从 "+ x +" ==> " + z);
    }
    
}
