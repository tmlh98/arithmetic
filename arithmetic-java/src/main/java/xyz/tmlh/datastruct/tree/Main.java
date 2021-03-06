package xyz.tmlh.datastruct.tree;

import xyz.tmlh.datastruct.tree.print.BinaryTrees;

public class Main {
   
    /*
     *          ┌───1 ──┐
                │       │
             ┌─2 ─┐   ┌─3 ─┐
             │    │   │    │
            4     5  6     7 
     *  
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.setLeft(node2);
        root.setRight(node3);
        
        node2.setLeft(node4);
        node2.setRight(node5);
        
        node3.setLeft(node6);
        node3.setRight(node7);
        BinaryTree binaryTree = new BinaryTree(root);
        System.out.println("\n-------preOrder-------");
        binaryTree.preOrder();//1245367
        System.out.println("\n-------infixOrder-------");
        binaryTree.infixOrder();//4251367
        System.out.println("\n-------postOrder-------");
        binaryTree.postOrder();//4526731
        
        
        System.out.println();
        BinaryTrees.print(root);
    }

    
}
