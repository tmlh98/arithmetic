package xyz.tmlh.datastruct.tree.redblack;

import xyz.tmlh.datastruct.tree.sort.SortNode;

/*
 * 红黑树有两大操作:recolor (重新标记黑色或红色)rotation (旋转，这是树达到平衡的关键)
 */
// 假设我们插入的新节点为 X
// 1. 将新插入的节点标记为红色
// 2. 如果 X 是根结点(root)，则标记为黑色
// 3. 如果 X 的 parent 不是黑色，同时 X 也不是 root:
// 3.1 如果 X 的 uncle (叔叔) 是红色
// 3.1.1 将 parent 和 uncle 标记为黑色
// 3.1.2 将 grand parent (祖父) 标记为红色
// 3.1.3 让 X 节点的颜色与 X 祖父的颜色相同，然后重复步骤 2、3
// 3.2 如果 X 的 uncle (叔叔) 是黑色，我们要分四种情况处理
// 3.2.1 左左 (P 是 G 的左孩子，并且 X 是 P 的左孩子)
// 3.2.2 左右 (P 是 G 的左孩子，并且 X 是 P 的右孩子)
// 3.2.3 右右 (和 3.2.1 镜像过来，恰好相反)
// 3.2.4 右左 (和 3.2.2 镜像过来，恰好相反)
public class RedBlackNode extends SortNode {
    
    //true：红色 , false:黑色
    private boolean color ;
    
    public RedBlackNode(int no) {
        super(no);
        color = true;
    }
    
    public RedBlackNode(int no, boolean color) {
        super(no);
        this.color = color;
    }
    
    @Override
    public RedBlackNode getLeft() {
        return (RedBlackNode)super.getLeft();
    }
    
    @Override
    public RedBlackNode getRight() {
        return (RedBlackNode)super.getRight();
    }
    
    /**
     * true：红色 , false:黑色
     */
    public boolean isColor() {
        return color;
    }
    
    public void setColor(boolean color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return (color?".":"") + getNo() + (color?".":"");
    } 
    
}
