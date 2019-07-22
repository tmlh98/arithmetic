package xyz.tmlh.datastruct.tree.sort;

import xyz.tmlh.datastruct.tree.TreeNode;

public class SortNode extends TreeNode {

    public SortNode(int no) {
        super(no);
    }

    /**
     * 查找要删除的结点
     * 
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public SortNode search(int value) {
        if(this.getNo() == value) {
            return this;
        }
        
        //如果要查找节点的值小于当前节点 ， 并且左子节点的的值不为null
        if(value < getNo() && getLeft()!= null) {
            SortNode leftChild = getLeft();
            return leftChild.search(value); //向左子树递归查找
        }else if (value >= getNo() && getRight() != null) {
            SortNode rightChild = getRight();
            return rightChild.search(value); //向右子树递归查找
        } else {
            return null; // 没有找到
        }
    }
    
    
    /**
     * 查找要删除结点的父结点
     * 
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public SortNode searchParent(int value) {
        if((getLeft() != null && getLeft().getNo() == value )||
                (getRight() != null && getRight().getNo() == value)){
            return this;
        }else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if(value < getNo() && getLeft() != null) {
                SortNode leftChild = getLeft();
                return leftChild.searchParent(value); //向左子树递归查找
            } else if (value >= getNo() && getRight() != null) {
                SortNode rightChild = getRight();
                return rightChild.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }
    }
    
    /**
     * 添加节点
     * @param @param node    待添加的节点
     * @return void    返回类型
     * @throws
     */
    public void add(SortNode node) {
        if(node == null) {
            return;
        }
        
        //如果添加的节点的值小于当前节点
        if(node.getNo() < getNo()) {
            if(this.getLeft() == null) {
                this.setLeft(node);
            }else {
                //递归添加
                SortNode left = getLeft();
                left.add(node);
            }
        }else {
            if(this.getRight() == null) {
                this.setRight(node);
            }else {
                SortNode right = getRight();
                right.add(node);
            }
        }
    }

    
    @Override
    public SortNode getLeft() {
         return (SortNode)super.getLeft();
    }
    @Override
    public SortNode getRight() {
        return (SortNode)super.getRight();
    }
}