package xyz.tmlh.datastruct.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 先从根节点出发，将当前节点的所有可达子节点依次访问，在依次访问子节点的子节点，一直下去直到所有节点被遍历。
 * 当发现某一层上有终点节点时，遍历结束，此时找到的也一定是最短路径，它和二叉树的层序遍历有异曲同工之妙。
 */
public class BFSMaze extends Maze{

    @Override
    public void firstSearch() {
        bfs();
    }

    private void bfs() {
        WideBlock wideBlock = new WideBlock(startX, startY, null);
        map[startX][startY] = 1;
        Queue<WideBlock> q = new LinkedList<WideBlock>();
        q.offer(wideBlock);
        while(!q.isEmpty()) {
            WideBlock block = q.poll();
            int x = block.getX();
            int y = block.getY();
            if(x == endX && y == endY) {
                printDeepStack(printWideBlock(block));
                return;
            }
            
            if(x - 1 >= 0 && map[x-1][y] == 0) {
                q.offer(new WideBlock(x -1, y, block));
                map[x-1][y] = 1;
            }
            if(y + 1 <= mapY && map[x][y + 1] == 0) {
                q.offer(new WideBlock(x , y + 1, block));
                map[x][y + 1] = 1;
            }
            if(x + 1 <= mapX && map[x + 1][y] == 0) {
                q.offer(new WideBlock(x + 1, y, block));
                map[x+1][y] = 1;
            }
            if(y - 1 >= 0 && map[x][y - 1] == 0) {
                q.offer(new WideBlock(x , y - 1, block));
                map[x][y - 1] = 1;
            }
        }
    }

    private Stack<Dot> printWideBlock(WideBlock wideBlock) {
        Stack<Dot> stack = new Stack<Dot>();
        
        WideBlock block = wideBlock;
        
        while(block != null) {
            stack.push(block);
            block = block.getParent();
        }
        return stack;
    }
    
}
