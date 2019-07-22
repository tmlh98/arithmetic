package xyz.tmlh.datastruct.graph;

/**
 * 广度优先遍历用到的数据结构，它需要一个指向父节点的索引
 */
public class WideBlock extends Dot {

    private WideBlock parent;

    public WideBlock(int x, int y, WideBlock p) {
        super(x, y);
        parent = p;
    }

    public WideBlock getParent() {
        return parent;
    }
}