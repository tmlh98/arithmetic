package xyz.tmlh.airthmetic.tree.sort;

import xyz.tmlh.airthmetic.tree.TreeNode;

/**
 * 二叉排序树(BST).
 * 任何非叶节点，都需要左子节点的值比当前节点值小，右子节点比当前节点的值大
 */
public class BinarySortTree {
    /**
     * 根节点
     */
    private Node root;
    
    /**
     * 查找结点
      *
      * @param @param value
      * @param @return    参数
      * @return Node    返回类型
      * @throws
     */
    public Node search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }
    
    /**
     * 查找父结点
      *
      * @param @param value
      * @param @return    参数
      * @return Node    返回类型
      * @throws
     */
    public Node searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    
    /**
     * 返回的 以node 为根结点的二叉排序树的最小结点的值
     * 删除node 为根结点的二叉排序树的最小结点
     * 
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(TreeNode node) {
        TreeNode target = node;
        TreeNode parent = target;
        
        //循环的查找左子节点，就会找到最小值
        while(target.getLeft() != null) {
            parent = target;
            target = target.getLeft();
        }
        //这时 target就指向了最小结点
        //删除最小结点
        parent.setLeft(null);
        return target.getNo();
    }
    
    /**
     * 删除结点
      *
      * @param @param value    参数
      * @return void    返回类型
      * @throws
     */
    public void delNode(int value) {
        if(root == null) {
            return;
        }
        
        if(root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        Node targetNode = root.search(value);
        //如果没有找到要删除的结点
        if(targetNode == null) {
            return;
        }
        
        //父节点
        Node parent = searchParent(value);
        if(targetNode.getLeft() == null && targetNode.getRight() ==null) {
            //case1:找到的结点的左右孩子都为空，那么直接删除此结点即可
            if(parent.getLeft() != null && parent.getLeft().getNo() == value) {
                parent.setLeft(null);
            }else {
                parent.setRight(null);
            }
        }else if(targetNode.getLeft() != null && targetNode.getRight() !=null) {
            //case3:找到的结点的左右孩子都不为空，那么找到这个结点的右子树中的最小结点（此结点的左孩子一定为空），
            //将最小结点的值赋给要删除的结点，然后删除最小结点（因为最小结点属于case1或case2的一种，所以删除实现非常简单）
            int minVal = delRightTreeMin(targetNode.getRight());
            targetNode.setNo(minVal);
        }else {
            //case2:找到的结点的左右孩子有一个为空，那么将不空的那个孩子代替要删的结点即可
           if(targetNode.getLeft() != null) {
               if(parent != null) {
                   if(parent.getLeft() != null && parent.getLeft().getNo() == value) {
                       parent.setLeft( targetNode.getLeft() );
                   } else {
                       parent.setRight(targetNode.getLeft());
                   } 
               } else {
                   root = (Node)targetNode.getLeft();
               }
           }else {
               if(parent != null) {
                   if(parent.getRight() != null && parent.getRight().getNo() == value) {
                       parent.setRight( targetNode.getRight() );
                   } else {
                       parent.setLeft(targetNode.getRight());
                   } 
               } else {
                   root = (Node)targetNode.getRight();
               }
           }
        }
    }
    
    public void add(Node node) {
        if(root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void infixOrder() {
        root.infixOrder();
    }

}

class Node extends TreeNode {

    public Node(int no) {
        super(no);
    }

    /**
     * 查找要删除的结点
     * 
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if(this.getNo() == value) {
            return this;
        }
        
        //如果要查找节点的值小于当前节点 ， 并且左子节点的的值不为null
        if(value < getNo() && getLeft()!= null) {
            Node leftChild = (Node)this.getLeft();
            return leftChild.search(value); //向左子树递归查找
        }else if (value >= this.getNo() && this.getRight() != null) {
            Node rightChild = (Node)this.getRight();
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
    public Node searchParent(int value) {
        if((getLeft() != null && getLeft().getNo() == value )||
                (getRight() != null && getRight().getNo() == value)){
            return this;
        }else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if(value < this.getNo() && this.getLeft() != null) {
                Node leftChild = (Node)this.getLeft();
                return leftChild.searchParent(value); //向左子树递归查找
            } else if (value >= this.getNo() && this.getRight() != null) {
                Node rightChild = (Node)this.getRight();
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
    public void add(Node node) {
        if(node == null) {
            return;
        }
        
        //如果添加的节点的值小于当前节点
        if(node.getNo() < this.getNo()) {
            if(this.getLeft() == null) {
                this.setLeft(node);
            }else {
                //递归添加
                Node left = (Node)this.getLeft();
                left.add(node);
            }
        }else {
            if(this.getRight() == null) {
                this.setRight(node);
            }else {
                Node right = (Node)this.getRight();
                right.add(node);
            }
        }
    }

    @Override
    public String toString() {
        return getNo() + "";
    }
    
}