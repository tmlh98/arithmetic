package xyz.tmlh.airthmetic.stack;

/**
 *  栈的链式存储结构实现：
 * @param <E>
 */
public class LinkStack<E> implements Stack<E>{

    //链栈的节点
    private class Node<E>{
        E e;
        Node next;

        public Node() {
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }


    private int size ; //当前栈大小
    private Node<E> top;   //栈顶元素

    public int length(){
        return this.size;
    }

    //设置新栈顶元素当前元素指向栈顶元素
    public void push(E item) {
        top = new Node<E>(item , this.top);
        size++;
    }

    public E pop() {
        if(empty()){
            throw  new RuntimeException("栈为空");
        }
        E result = top.e;
        this.top = top.next;
        size --;
        return result;
    }

    public boolean empty() {
        return this.size == 0;
    }

    public E peek() {
        if(empty()){
            throw  new RuntimeException("栈为空");
        }
        return top.e;
    }


}
