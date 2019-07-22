package xyz.tmlh.datastruct.tree.avl;

import xyz.tmlh.airthmetic.util.ArraysUtil;
import xyz.tmlh.datastruct.tree.print.BinaryTrees;

public class AVLTreeDemo {

    /*
     *      ┌─ 4 ─┐
            │     │
            3  ┌─ 6 ─┐
               │     │
               5     7 ─┐
                        │
                        8
     */
    public static void main(String[] args) {
      int[] arr = ArraysUtil.genArray0and100(50);
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new AVLNode(arr[i]));
        }
//        System.out.println(show());
        System.out.println();
        BinaryTrees.print(avlTree.getRoot());
        
    }
    
    
    public static String show() {
        String tree =
                "       ┌─ 10 ─┐         \n" + 
                "       │      │         \n" + 
                "    ┌─ 7 ─┐   11        \n" + 
                "    │     │             \n" + 
                "    6     8 ─┐          \n" + 
                "             │          \n" + 
                "             9          \n";
        return tree;
    }
    
    
    
    
    
}
