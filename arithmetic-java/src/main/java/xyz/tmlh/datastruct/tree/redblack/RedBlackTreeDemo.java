package xyz.tmlh.datastruct.tree.redblack;
import xyz.tmlh.airthmetic.util.ArraysUtil;
import xyz.tmlh.datastruct.tree.print.BinaryTrees;

public class RedBlackTreeDemo {

    public static void main(String[] args) {
//        int[] arr = ArraysUtil.genArray0and100(50);
        
        int[] arr = {81, 71, 39, 7, 48, 37, 23, 60, 5};
        
        RedBlackTree tree = new RedBlackTree();
        for (int i = 0; i < arr.length; i++) {
            System.out.println("\n=======================\n");
            tree.add(arr[i]);
            System.out.println("\n-----------------调整后-----------------");
            BinaryTrees.print(tree.getRoot());
            System.out.println("\n=======================\n");
        }
        
    }

}
