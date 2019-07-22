package xyz.tmlh.datastruct.tree.avl;

import xyz.tmlh.datastruct.tree.sort.BinarySortTree;

/**
 * 二叉平衡树是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
 */
public class AVLTree extends BinarySortTree{
    
    @Override
    public AVLNode getRoot() {
         return (AVLNode)super.getRoot();
    }
    
}