package xyz.tmlh.datastruct.tree.sort;

import xyz.tmlh.datastruct.tree.TreeNode;

/**
 * 二叉排序树(BST).
 * 任何非叶节点，都需要左子节点的值比当前节点值小，右子节点比当前节点的值大
 */
public class BinarySortTree {
    /**
     * 根节点
     */
    private SortNode root;
    
    /**
     * 查找结点
      *
      * @param @param value
      * @param @return    参数
      * @return Node    返回类型
      * @throws
     */
    public SortNode search(int value) {
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
    public SortNode searchParent(int value) {
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
        SortNode targetNode = root.search(value);
        //如果没有找到要删除的结点
        if(targetNode == null) {
            return;
        }
        
        //父节点
        SortNode parent = searchParent(value);
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
                   root = (SortNode)targetNode.getLeft();
               }
           }else {
               if(parent != null) {
                   if(parent.getRight() != null && parent.getRight().getNo() == value) {
                       parent.setRight( targetNode.getRight() );
                   } else {
                       parent.setLeft(targetNode.getRight());
                   } 
               } else {
                   root = (SortNode)targetNode.getRight();
               }
           }
        }
    }
    
    public void add(SortNode node) {
        if(root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }

    public SortNode getRoot() {
        return root;
    }

    public void infixOrder() {
        root.infixOrder();
    }

}

