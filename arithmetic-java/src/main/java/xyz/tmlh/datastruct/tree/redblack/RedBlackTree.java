package xyz.tmlh.datastruct.tree.redblack;

import xyz.tmlh.datastruct.tree.print.BinaryTrees;
import xyz.tmlh.datastruct.tree.sort.BinarySortTree;

/*
 * 红黑树，Red-Black Tree 「RBT」是一个自平衡(不是绝对的平衡)的二叉查找树(BST)，树上的每个节点都遵循下面的规则:
 */
/*
 * 红黑树的查找、插入和删除时间复杂度都为O(log2N)，额外的开销是每个节点的存储空间都稍微增加了一点， 
 * 因为一个存储红黑树节点的颜色变量。插入和删除的时间要增加一个常数因子，因为要进行旋转，
 * 平均一次插入大约需要一次旋转，因此插入的时间复杂度还是O(log2N),(时间复杂度的计算要省略常数)，但实际上比普通的二叉树是要慢的。
 */
//（1）每个节点或者是黑色，或者是红色。
//（2）根节点是黑色。
//（3）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
//（4）如果一个节点是红色的，则它的子节点必须是黑色的。
//（5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
/*<插入/>
 * 因为要满足红黑树的这五条性质，
 * 如果我们插入的是黑色节点，那就违反了性质五，需要进行大规模调整，
 * 如果我们插入的是红色节点，那就只有在要插入节点的父节点也是红色的时候违反性质四
 * 或者是当插入的节点是根节点时，违反性质二，所以，我们应该把要插入的节点的颜色变成红色。
 */
public class RedBlackTree extends BinarySortTree {

    public static final boolean RED = true;

    public static final boolean BLACK = false;
    
    /**
     * 添加元素
     */
    public void add(int no) {
        
        setRoot(add(getRoot(), no));
        getRoot().setColor(BLACK);//最终根结点为黑色结点
    }
    
    /**
     * 判断结点node的颜色
     *
     * @param node
     * @return
     */
    private boolean isRed(RedBlackNode node) {
        if (node == null) {
            return BLACK;
        }
        return node.isColor();
    }
    
    /**
     * 向以node为根的红黑树中插入元素，递归算法
     * 返回插入新结点后红黑树的根
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private RedBlackNode add(RedBlackNode node, int no) {
        if (node == null) {
            return new RedBlackNode(no); //默认插入红色结点
        }

        if (no < node.getNo()) {
            node.setLeft(add(node.getLeft(), no));
        } else if (no > node.getNo()) {
            node.setRight(add(node.getRight(), no));
        } else {
            node.setNo(no);
        }

        System.out.println("\n-----------------插入" + no + "后-----------------");
        BinaryTrees.print(getRoot());
        
        //右孩子是红色，左孩子是黑色
        if(isRed(node.getRight()) && !isRed(node.getLeft())) {
            node = leftRotate(node);
        }
        
        //左孩子是红色 ， 左孩子的左孩子是红色
        if(isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rightRotate(node);
        }
        
        //左右孩子都是红色
        if(isRed(node.getLeft()) && isRed(node.getRight())) {
            reverseColors(node);
        }
        
        return node;
    }


    /**
     * 左旋转
     * //      node                                        x
     * //     /    \              左旋转                                     /     \
     * //    T1     x           --------->            node     T3
     * //         /   \                              /    \
     * //       T2     T3                           T1    T2
     * 1.将rightChild的左子树作为n的右子树   
     * 2.将rightChild作为根
     * 3.将n节点作为rightChild的左孩子
     */
    private RedBlackNode leftRotate(RedBlackNode node) {
        RedBlackNode x = node.getRight();

        //左旋转
        node.setRight(x.getLeft());
        
        x.setLeft(node);
        
        x.setColor(node.isColor());
        node.setColor(RED);
        return x;
    }
    
    /**
     * 右旋转
     * //      node                                       x
     * //     /    \              右旋转                                     /    \
     * //    x     T2           --------->             y     node
     * //  /  \                                             /    \
     * // y   T1                                           T1    T2
     *
     *  1.将leftChild的右子树作为n的左子树
     *  2.将leftChild作为根
     *  3.将n节点作为leftChild的右孩子
     */
    private RedBlackNode rightRotate(RedBlackNode node) {

        RedBlackNode x = node.getLeft();

        //右旋转
        node.setLeft(x.getRight());
        x.setRight(node);
        
        x.setColor(node.isColor());
        node.setColor(RED);
        return x;
    }

    
    /**
     * 颜色翻转
     *
     * @param node
     */
    private void reverseColors(RedBlackNode node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }
    
    @Override
    public RedBlackNode getRoot() {
         return (RedBlackNode)super.getRoot();
    }
}