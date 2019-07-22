package xyz.tmlh.datastruct.tree.avl;

import xyz.tmlh.datastruct.tree.sort.SortNode;

/*
 * AVL树失去平衡时的情况一定是LL、LR、RL、RR这4种之一
 */
public class AVLNode extends SortNode {

    public AVLNode(int no) {
        super(no);
    }
    
    @Override
    public void add(SortNode node) {
        super.add(node);
        int rightHeight = rightHeight();
        int leftHeight = leftHeight();
        if(rightHeight - leftHeight > 1) {
          //如果它的右子树的左子树的高度大于它的右子树的右子树的高度(RL)
          if(getRight() != null && getRight().leftHeight() > getRight().rightHeight()) {
              //先对右子结点进行右旋转
              getRight().rightRotate();
          }
          //然后在对当前结点进行左旋转 
          leftRotate();
        }else if (leftHeight - rightHeight > 1) {
            //(LR)
            if(getLeft() != null && getLeft().rightHeight() > getLeft().leftHeight()) {
                getLeft().leftRotate();
            }
            rightRotate();
        }
    }
    
    /**
     * 左旋转(RR)
     */
    public void leftRotate() {
        //创建一个新节点，值为当前节点的值
        AVLNode newNode = new AVLNode(getNo());
        //将新节点的左子树设置为当前节点的左子树
        newNode.setLeft(getLeft());
        //新节点的右子树指向当前节点右子树的左子树
        newNode.setRight(getRight().getLeft());
        //把当前节点的值换成右子节点的值(由于父执政会应用这个节点，所以使用值替换的策略)
        setNo(getRight().getNo());
        //把当前结点的右子树设置成当前结点右子树的右子树
        setRight(getRight().getRight());
        //把当前结点的左子树设置成新的结点
        setLeft(newNode);
    }
    
    /**
     * 右旋转(LL)
     */
    public void rightRotate() {
        //创建一个新节点，值为当前节点的值
        AVLNode newNode = new AVLNode(getNo());
        //将新节点的右子树设置为当前节点的右子树
        newNode.setRight(getRight());
        //新节点的左子树指向当前节点左子树的右子树
        newNode.setLeft(getLeft().getRight());
        //把当前节点的值换成左子节点的值
        setNo(getLeft().getNo());
        //把当前结点的左子树设置成当前结点左子树的左子树
        setLeft(getLeft().getLeft());
        //把当前结点的右子树设置成新的结点
        setRight(newNode);
    }
    

    /**
     * 左子树的高度
     */
    public int leftHeight() {
        if(getLeft() == null) {
            return 0;
        }
        return getLeft().height();
    }
    
    /**
     * 右子树的高度
     */
    public int rightHeight() {
        if(getRight() == null) {
            return 0;
        }
        return getRight().height();
    }
    
    /**
     * 返回 以该结点为根结点的树的高度
     */
    public int height() {
        return Math.max(getLeft() != null?getLeft().height():0, getRight() != null?getRight().height():0) + 1;
    }

    @Override
    public AVLNode getLeft() {
        return (AVLNode)super.getLeft();
    }
    
    @Override
    public AVLNode getRight() {
        return (AVLNode)super.getRight();
    }

}
