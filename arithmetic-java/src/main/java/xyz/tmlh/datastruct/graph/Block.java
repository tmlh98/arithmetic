package xyz.tmlh.datastruct.graph;

/**
 *  内部类，封装走过的每一个点,自带方向
 */
public class Block extends Dot {

    private int dir = 1;// 方向,1向上，2向右，3向下，4向左

    public Block(int x, int y) {
        super(x, y);
    }

    public int getDir() {
        return dir;
    }

    public void changeDir() {
        dir++;
    }
}