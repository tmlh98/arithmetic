package xyz.tmlh.datastruct.tree.rb;

import xyz.tmlh.datastruct.tree.print.BinaryTreeInfo;

/**
 * 红黑树实现: 性质: 1.节点要么红，要么黑; 2.根是黑色; 3.所有叶子都是黑色;(叶子为null节点) 4.每个红色节点的两个子节点都是黑色(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 5.从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点
 */
public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root; // tree root
    private int size; // tree elements' count
    private Node<T> NIL = new Node<T>(null, null, null, null, Color.BLACK); // 标志叶子节点

    /**
     * 节点类
     */
    private static class Node<E> implements BinaryTreeInfo{
        E value;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        Color color;

        public Node(E value, Node<E> parent, Node<E> left, Node<E> right, Color color) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public Object root() {
             return this;
        }

        public Object left(Object node) {
            Node<E> n = (Node<E>)node;
            return n.left;
        }

        public Object right(Object node) {
            Node<E> n = (Node<E>)node;
            return n.right;
        }

        public Object string(Object node) {
            Node<E> n = (Node<E>)node;
            return n;
        }

        @Override
        public String toString() {
            return (color == Color.RED ? "R":"") + (value == null?"":value.toString()) ;
        }
        
    }

    /**
     * 节点颜色
     */
    private static enum Color {
        RED, BLACK
    }

    /**
     * 获取叔叔节点
     * 
     * @param n 当前节点
     * @return 其叔节点
     */
    private Node<T> uncle(Node<T> n) {
        Node<T> gp = grandParent(n);
        if (gp == null)
            return null;
        if (n.parent == gp.left) { // 若其父节点在其祖父节点左边
            return gp.right;
        } else {
            return gp.left;
        }
    }

    /**
     * 获取祖父节点
     * 
     * @param n 当前节点
     * @return 其祖父节点
     */
    private Node<T> grandParent(Node<T> n) {
        if (n.parent == null)
            return null;
        return n.parent.parent;
    }

    /**
     * 返回最小元素
     * 
     * @return 获取某节点为根的树的最小元素
     */
    public T min(Node<T> n) {
        Node<T> min = minN(n);
        return min == NIL ? null : min.value;
    }

    /**
     * 返回最小节点
     * 
     * @param n 树根节点
     * @return 最小节点
     */
    private Node<T> minN(Node<T> n) {
        Node<T> min = n;
        while (min.left != NIL) {
            min = min.left;
        }
        return min == NIL ? null : min;
    }

    /**
     * 获取某节点为根的树的最大元素
     * 
     * @return 最大元素, 没有返回null
     */
    public T max(Node<T> n) {
        Node<T> max = maxN(n);
        return max == NIL ? null : max.value;
    }

    /**
     * 获取某节点为根的树的最大节点
     * 
     * @return 最大节点, 没有返回null
     */
    public Node<T> maxN(Node<T> n) {
        Node<T> max = n;
        while (max.right != NIL) {
            max = max.right;
        }
        return max == NIL ? null : max;
    }

    /**
     * 左旋以n节点为根的子树： 1.将rightChild的左子树作为n的右子树 2.将rightChild作为根 3.将n节点作为rightChild的左孩子
     */
    private void leftRotate(Node<T> n) {
        Node<T> rightChild = n.right;
        // 1.
        // 将rightChild的左子树接到n的右边
        n.right = rightChild.left;
        if (rightChild.left != NIL)
            rightChild.left.parent = n;

        // 2.
        rightChild.parent = n.parent;
        if (n.parent == null) { // 若n为树根
            root = rightChild;
        } else if (n.parent.left == n) { // 若n为父亲的左孩子
            n.parent.left = rightChild;
        } else { // 若n为父亲的右孩子
            n.parent.right = rightChild;
        }

        // 3.
        rightChild.left = n;
        n.parent = rightChild;
    }

    /**
     * 右旋以n节点为根的子树： 1.将leftChild的右子树作为n的左子树 2.将leftChild作为根 3.将n节点作为leftChild的右孩子
     */
    private void rightRotate(Node<T> n) {
        Node<T> leftChild = n.left;

        // 1.
        n.left = leftChild.right;
        if (leftChild.right != NIL)
            leftChild.right.parent = n;

        // 2.
        leftChild.parent = n.parent;
        if (n.parent == null) { // n为树根
            root = leftChild;
        } else if (n == n.parent.left) { // n为父节点点左孩子
            n.parent.left = leftChild;
        } else { // n为父节点右孩子
            n.parent.right = leftChild;
        }

        // 3.
        leftChild.right = n;
        n.parent = leftChild;
    }

    /**
     * 插入元素
     */
    public boolean insert(T t) {
        // 插入新节点时，以红色着色
        Node<T> n = new Node<T>(t, null, NIL, NIL, Color.RED);
        Node<T> pointer = root;
        boolean inserted = false;
        // 遍历插入
        while (!inserted) {
            if (root == null) { // 空树
                root = n;
                inserted = true;
            } else if (n.value.compareTo(pointer.value) > 0) { // 向右子树找
                if (pointer.right == NIL) { // 插入右边
                    n.parent = pointer;
                    pointer.right = n;
                    inserted = true;
                } else {
                    pointer = pointer.right;
                }
            } else if (n.value.compareTo(pointer.value) < 0) { // 向左子树找
                if (pointer.left == NIL) { // 插入左边
                    n.parent = pointer;
                    pointer.left = n;
                    inserted = true;
                } else {
                    pointer = pointer.left;
                }
            } else { // 相等了
                return false;
            }
        }
        size++;
        insertFixup(n); // 调整树
        return inserted;
    }

    /**
     * 调整树以满足红黑树性质
     * 
     * @param n 新添加的节点
     */
    private void insertFixup(Node<T> n) {
        // 若是树根
        if (n.parent == null) {
            n.color = Color.BLACK;
            return;
        }

        // 父节点为黑色，无须调整
        if (n.parent.color == Color.BLACK) {
            return;
        }

        Node<T> u = uncle(n);
        Node<T> g = grandParent(n);
        // 1.父节点及叔节点都为红色
        if (u != null && u.color == Color.RED) {
            // 将parent和uncle颜色置BLACK
            n.parent.color = Color.BLACK;
            u.color = Color.BLACK;
            // 将grand parent置RED
            g.color = Color.RED;
            // 递归调整 grand parent, 这时可想像grand parent为新添加的红色节点
            insertFixup(g);
        } else { // 父节点P是红色而叔节点是黑色或缺少
            if (n == n.parent.right && n.parent == g.left) { // n为父节点右孩子,且父节点为祖父节点的左孩子
                // 以父左旋
                leftRotate(n.parent);
                n = n.left;
            } else if (n == n.parent.left && n.parent == g.right) { // n为父节点左孩子,且父节点为祖父节点右孩子
                // 以父右旋
                rightRotate(n.parent);
                n = n.right;
            }
            n.parent.color = Color.BLACK; // parent颜色置为黑色
            g.color = Color.RED;
            if (n == n.parent.left && n.parent == g.left) { // n节点为父节点的左孩子，且父节点为祖父节点的左孩子
                // 以祖父右旋
                rightRotate(g);
            } else { // n节点为父节点的右孩子，且父节点为祖父节点的右孩子
                // 以祖父左旋
                leftRotate(g);
            }
        }
    }

    public Node<T> getRoot() {
        return root;
    }

}