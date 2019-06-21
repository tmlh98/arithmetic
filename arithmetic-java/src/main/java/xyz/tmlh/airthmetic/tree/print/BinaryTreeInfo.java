package xyz.tmlh.airthmetic.tree.print;

/**
 * 原github:https://github.com/CoderMJLee/BinaryTrees
 * 继承此接口实现打印功能
 */
public interface BinaryTreeInfo {
    /**
     * who is the root node
     */
    Object root();

    /**
     * how to get the left child of the node
     */
    Object left(Object node);

    /**
     * how to get the right child of the node
     */
    Object right(Object node);

    /**
     * how to print the node
     */
    Object string(Object node);
}