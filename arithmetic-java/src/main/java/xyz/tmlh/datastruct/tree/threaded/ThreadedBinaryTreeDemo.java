package xyz.tmlh.datastruct.tree.threaded;

import xyz.tmlh.datastruct.stack.ArrayStack;
import xyz.tmlh.datastruct.stack.Stack;
import xyz.tmlh.datastruct.tree.BinaryTree;
import xyz.tmlh.datastruct.tree.TreeNode;

/**
 *  线索二叉树
 */
/*
 * n个结点的二叉链表中含有n+1 (2n-(n-1)=n+1)个空指针域。
 * 利用二叉链表中的空指针域，存放指向结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）。
 */
/*
 *      1
 *    2    3
 *  4  5  6  7
 *  
 */
public class ThreadedBinaryTreeDemo{
    
    public static void main(String[] args) {
        TreeNode root = new ThreadedTreeNode(1);
        TreeNode node2 = new ThreadedTreeNode(2);
        TreeNode node3 = new ThreadedTreeNode(3);
        TreeNode node4 = new ThreadedTreeNode(4);
        TreeNode node5 = new ThreadedTreeNode(5);
        TreeNode node6 = new ThreadedTreeNode(6);
        TreeNode node7 = new ThreadedTreeNode(7);
        root.setLeft(node2);
        root.setRight(node3);
        
        node2.setLeft(node4);
        node2.setRight(node5);
        
        node3.setLeft(node6);
        node3.setRight(node7);
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree(root);
        new BinaryTree(root).postOrder();
        System.out.println();
//        binaryTree.preThreaded();
//        binaryTree.preOrder();
//        binaryTree.infixThreaded();
//        binaryTree.infixOrder();
        binaryTree.postThreaded();
        binaryTree.postOrder();
//        System.out.println(node5.getLeft());
//        System.out.println(node5.getRight());
        
    }
}


class ThreadedBinaryTree extends BinaryTree{
    
    //当前节点的前一个节点
    private ThreadedTreeNode preNode;
    
    public ThreadedBinaryTree(TreeNode root) {
        super(root);
    }
    
    public void preThreaded() {
        preThreaded(root);
    }
    public void infixThreaded() {
        infixThreaded(root);
    }
    public void postThreaded() {
        postThreaded(root);
    }

    //前序线索化二叉树【1245367】
    public void preThreaded(TreeNode node) {
        ThreadedTreeNode thrNode = (ThreadedTreeNode)node;
        if(thrNode == null) {
            return;
        }
        
        //线索化当前节点
        //如果当前节点的左孩子为空、则当前节点的左孩子指向前驱节点
        boolean sign = true;//防止进入死循环
        if(thrNode.getLeft() == null ) {
            thrNode.setLeft(preNode);
            thrNode.setLeftType(1);
            sign = false;
        }
        
        //如果当前节点的前驱节点为空、则当当前节点的前驱节点为空的右孩子指向当前节点
        if(preNode!= null && preNode.getRight() == null) {
            preNode.setRight(thrNode);
            preNode.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        preNode = thrNode;
        
        //先线索化左子树、右子树
        preThreaded(sign?thrNode.getLeft():null);
        preThreaded(thrNode.getRight());
    }
    //后序线索化二叉树【4526731】
    public void postThreaded(TreeNode node) {
        ThreadedTreeNode thrNode = (ThreadedTreeNode)node;
        if(thrNode == null) {
            return;
        }
        //先线索化左子树、右子树
        postThreaded(thrNode.getLeft());
        postThreaded(thrNode.getRight());
        
        //线索化当前节点
        //如果当前节点的左孩子为空、则当前节点的左孩子指向前驱节点
        if(thrNode.getLeft() == null ) {
            thrNode.setLeft(preNode);
            thrNode.setLeftType(1);
        }
        
        //如果当前节点的前驱节点为空、则当当前节点的前驱节点为空的右孩子指向当前节点
        if(preNode!= null && preNode.getRight() == null) {
            preNode.setRight(thrNode);
            preNode.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        preNode = thrNode;
        
    }
    //中序线索化二叉树【4251367】
    public void infixThreaded(TreeNode node) {
        ThreadedTreeNode thrNode = (ThreadedTreeNode)node;
        if(thrNode == null) {
            return;
        }
        //先线索化左子树
        infixThreaded(thrNode.getLeft());
        
        //线索化当前节点
        //如果当前节点的左孩子为空、则当前节点的左孩子指向前驱节点
        if(thrNode.getLeft() == null ) {
            thrNode.setLeft(preNode);
            thrNode.setLeftType(1);
        }
        
        //如果当前节点的前驱节点为空、则当当前节点的前驱节点为空的右孩子指向当前节点
        if(preNode!= null && preNode.getRight() == null) {
            preNode.setRight(thrNode);
            preNode.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        preNode = thrNode;
        
        //再线索化左子树
        infixThreaded(thrNode.getRight());
    }

    @Override//1245367
    public void preOrder() {
        ThreadedTreeNode node = (ThreadedTreeNode)root;//从跟节点开始遍历
        while (node != null) {
            
            while( node.getLeftType() == 0) {
                System.out.println(node.getNo());
                node = (ThreadedTreeNode)node.getLeft();//向下查找左孩子
            }
            
            while( node.getRightType() == 1) {
                System.out.println(node.getNo());
                node = (ThreadedTreeNode)node.getRight();//向下查找后继
            }
            
            if(node.getLeftType() == 1 && node.getRightType() == 0) {
                System.out.println(node.getNo());
                node = (ThreadedTreeNode)node.getRight();//向下查找后继
            }
            
        }
        
    }

    @Override
    public void infixOrder() {
        
        ThreadedTreeNode node = (ThreadedTreeNode)root;//从跟节点开始遍历
        while(node != null) {
            //循环的找到leftType == 1的结点，第一个找到就是4结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while(node.getLeftType() == 0) {
                node = (ThreadedTreeNode)node.getLeft();
            }
            
            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while(node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = (ThreadedTreeNode)node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = (ThreadedTreeNode)node.getRight();
            
        }
    }

    /*
     *  a.先线索化左右孩子节点
         b.根节点：若左孩子节点空，左孩子节点指向前驱节点（前一次访问的节点Pre_Node），标记该孩子为                                                       线索模式（Thread）
          若前一次访问的节点不为空并且它的右孩子为空，则该右孩子指向当前节点（后继节点），                                                         标记该孩子为线索模式（Thread）
         c.保存当前节点，作为下一次的Pre_Node (记录历史的意思）
     */
    @Override//4526731 方法方向遍历入栈
    public void postOrder() {
        ThreadedTreeNode node = (ThreadedTreeNode)root;//从跟节点开始遍历
        
        Stack<Integer> noStack = new ArrayStack<Integer>();
        while (node != null) {
            while( node.getRightType() == 0) {
                noStack.push(node.getNo());
                node = (ThreadedTreeNode)node.getRight();//向下查找左孩子
            }
            
            while(node != null && node.getLeftType() == 1) {
                noStack.push(node.getNo());
                node = (ThreadedTreeNode)node.getLeft();// 查找前驱
            }
        }
        
        while(!noStack.empty()) {
            System.out.println(noStack.pop());
        }
    }

    
    
    
    
    
}

class ThreadedTreeNode extends TreeNode{
    
    //0表示指向左子树 、 1表示指向前驱结点
    private int leftType;
    private int rightType;

    public ThreadedTreeNode(int no) {
        super(no);
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedTreeNode [no="+ getNo() +", leftType=" + leftType + ", rightType=" + rightType + "]";
    }
    
}

