package xyz.tmlh.datastruct.tree.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;
import xyz.tmlh.datastruct.tree.print.BinaryTrees;

/*
 * 二叉排序树Demo
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] genArray = ArraysUtil.genArray0and10(20);
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
         
        for(int i = 0; i< genArray.length; i++) {
            binarySortTree.add(new SortNode(genArray[i]));
        }
        System.out.println("打印:");
        binarySortTree.infixOrder();
        
        System.out.println();
        BinaryTrees.print(binarySortTree.getRoot());
        binarySortTree.delNode(1);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        System.out.println();
        System.out.println("~修改后");
        BinaryTrees.print(binarySortTree.getRoot());
        
    }
    
}

