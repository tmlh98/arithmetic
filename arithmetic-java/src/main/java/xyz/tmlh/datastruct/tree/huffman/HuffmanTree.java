package xyz.tmlh.datastruct.tree.huffman;

import java.util.Collections;
import java.util.LinkedList;

import xyz.tmlh.datastruct.tree.TreeNode;
import xyz.tmlh.datastruct.tree.print.BinaryTrees;

/*
 * 当用 n 个结点（都做叶子结点且都有各自的权值）试图构建一棵树时，如果构建的这棵树的带权路径长度最小，
 * 称这棵树为“最优二叉树”，有时也叫“赫夫曼树”或者“哈夫曼树”。
 * 
 * 结点的带权路径长度为从该结点到树根之间的路径长度与结点上权的乘积。
 * 树的带权路径长度为树中所有叶子结点的带权路径长度之和，通常记作WPL。
 */
public class HuffmanTree {
    /*
     * 对于给定的有各自权值的 n 个结点，构建哈夫曼树有一个行之有效的办法：
     * 1)在 n 个权值中选出两个最小的权值，对应的两个结点组成一个新的二叉树，且新二叉树的根结点的权值为左右孩子权值的和；
     * 2)在原有的 n 个权值中删除那两个最小的权值，同时将新的权值加入到 n–2 个权值的行列中，以此类推；
     * 3)重复 1 和 2 ，直到所以的结点构建成了一棵二叉树为止，这棵树就是哈夫曼树。
     */
    
    public static void main(String[] args) {
        int[] arr = {7 ,5 ,2,4 ,2 };
        TreeNode root = createHuffmanTree(arr);
        System.out.println("前序遍历");
        root.preOrder();
        System.out.println();
        System.out.println("打印二叉树");
        BinaryTrees.print(root);
    }

    //创建一颗哈夫曼树
    private static TreeNode createHuffmanTree(int[] arr) {
        LinkedList<Node> list = new LinkedList<Node>();
        for (int no : arr) {
            list.add(new Node(no));
        }
        //选举出两个权值最小的结点，组成一颗新的二叉树
        while(list.size() > 1) {
            Collections.sort(list);
            Node firstNode = list.poll();
            Node secondNode = list.poll();
            Node rootNode = new Node(firstNode.getNo() + secondNode.getNo());
            rootNode.setLeft(firstNode);
            rootNode.setRight(secondNode);
            list.add(rootNode);
        }
        return list.peek();
    }
    
}
//构建树节点从小到大排序
class Node extends TreeNode implements Comparable<Node> {

    public Node(int no) {
        super(no);
    }

    public int compareTo(Node o) {
        return this.getNo() - o.getNo();
    }

    @Override
    public String toString() {
        return " " + getNo();
    }

}