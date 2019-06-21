package xyz.tmlh.airthmetic.tree;

import xyz.tmlh.airthmetic.tree.print.BinaryTreeInfo;

/**
 * 树的节点
 */
public class TreeNode implements BinaryTreeInfo{

    /**
     * 编号
     */
    private int no;
    /**
     * 左孩子
     */
    private TreeNode left;
    /**
     * 右孩子
     */
    private TreeNode right;

    public TreeNode(int no) {
        this.no = no;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 先输出当前节点，再输出左孩子、再输出右孩子
        System.out.print(this.no + "\t");
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }

    }
    
    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 先输出左孩子，再输出当前节点、再输出右孩子
        if (left != null) {
            left.infixOrder();
        }
        System.out.print(this.no + "\t");
        if (right != null) {
            right.infixOrder();
        }
        
    }
    
    /**
     * 后序遍历
     */
    public void postOrder() {
        // 先输出左孩子、再输出右孩子、再输出当前节点 
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.print(this.no + "\t");
        
    }
    

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return no + " ";
    }

    public Object root() {
        return this;
    }

    public Object left(Object node) {
        TreeNode treeNode =  (TreeNode)node;
        return treeNode.getLeft();
    }

    public Object right(Object node) {
        TreeNode treeNode =  (TreeNode)node;
        return treeNode.getRight();
    }

    public Object string(Object node) {
        TreeNode treeNode =  (TreeNode)node;
        return treeNode;
    }

}