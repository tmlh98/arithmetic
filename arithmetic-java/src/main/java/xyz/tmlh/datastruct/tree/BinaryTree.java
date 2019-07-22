package xyz.tmlh.datastruct.tree;

public class BinaryTree {
    
    /**
     * 根节点
     */
    protected TreeNode root;
    
    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if(root != null) {
            root.preOrder();
        }
    }
    /**
     * 中序遍历
     */
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        }
    }
    /**
     * 后序遍历
     */
    public void postOrder() {
        if(root != null) {
            root.postOrder();
        }
    }


}















