package xyz.tmlh.datastruct.graph;

import java.util.Stack;

/*
 * 有一个迷宫地图，有一些可达的位置，也有一些不可达的位置（障碍、墙壁、边界）。
 * 从一个位置到下一个位置只能通过向上（或者向右、或者向下、或者向左）走一步来实现，
 * 从起点出发，如何找到一条到达终点的通路。本文将用两种不同的解决思路，四种具体实现来求解迷宫问题。
 * 用二维矩阵来模拟迷宫地图，1代表该位置不可达，0代表该位置可达。
 * 每走过一个位置就将地图的对应位置标记，以免重复。
 * 找到通路后打印每一步的坐标，最终到达终点位置。
 */
public abstract class Maze {
    
    protected int[][] map = {                           //迷宫地图,1代表墙壁，0代表通路
        {1,1,1,1,1,1,1,1,1,1},
        {1,0,0,1,0,0,0,1,0,1},
        {1,0,0,1,0,0,0,1,0,1},
        {1,0,0,0,0,1,1,0,0,1},
        {1,0,1,1,1,0,0,0,0,1},
        {1,0,0,0,1,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,0,1},
        {1,0,1,1,1,0,1,1,0,1},
        {1,1,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1}
    };
    
    protected int mapX = map.length - 1;                //地图x边界
    protected int mapY = map[0].length - 1;             //地图y边界
    protected int startX = 1;                           //起点
    protected int startY = 1;
    protected int endX = mapX - 1;                      //终点
    protected int endY = mapY - 1;
    
    /**
     * 打印迷宫
     */
    public void printMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    } 
 
    /**
     * 打印栈
      *
      * @param @param stack    参数
      * @return void    返回类型
      * @throws
     */
    public void printDeepStack(Stack<Dot> stack){
        int count = 1;
        while(!stack.empty()){
            Dot b = stack.pop();
            System.out.print("(" + b.getX() + "," + b.getY() + ") ");
            if(count++ % 10 == 0){
                System.out.println("");
            }
        }
    }
    
    public abstract void firstSearch();
}
