package xyz.tmlh.datastruct.graph;

import java.util.Stack;


/*
 * 能向某个方向走下去就一直向那个方向走， 不能走就切换方向， 所有方向都不能走了就回到上一层位置。
 */
public class DFSMaze extends Maze {
    
    private Stack<Dot> deepStack = new Stack<Dot>();  //存储栈
    private Stack<Dot> dStack = new Stack<Dot>();      //辅助栈

    private void dfs(int x , int y) {
        dStack.push(new Dot(x, y));
        map[x][y] = 1; // 标记已访问
        if(x == endX && y == endY) {//找到了终点
            while (!dStack.empty()) {
                deepStack.push(dStack.pop());
            }
            printDeepStack(deepStack);
            System.out.println();
            return;
        }
        
        for (int i = 1; i <= 4; i++) {//遍历四个方向
            if(i == 1) {//上
                if (x - 1 >= 0 && map[x - 1][y] == 0) 
                dfs(x -1, y);
            }else if ( i == 2 ) {//右
                if (y + 1 <= mapY && map[x][y + 1] == 0) 
                dfs(x , y + 1);
            }else if ( i == 3 ) {//下
                if (x + 1 <= mapX && map[x + 1][y] == 0) 
                dfs(x + 1, y);
            }else if ( i == 4 ) {//左
                if (y - 1 >= 0 && map[x][y - 1] == 0) 
                dfs(x, y -1);
            }
        }
        if(!dStack.empty()) {
            dStack.pop(); // 四个方向都已尝试过，并且没成功，退栈
        }
    }

    @Override
    public void firstSearch() {
        dfs(startX, startY);
    }
    
}
