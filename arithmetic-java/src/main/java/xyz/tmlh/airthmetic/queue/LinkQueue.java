package xyz.tmlh.airthmetic.queue;

/**
 * 队列的链式存储结构实现
 * 
 * @param <E>
 */
public class LinkQueue<E> implements Queue<E> {
    
    // 链栈的节点
    private class Node<E> {
        E e;
        Node<E> next;

        public Node() {}

        public Node(E e) {
            this.e = e;
        }
        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }
    }
    
    private Node<E> head;// 队列头，允许删除  
    private Node<E> rear;// 队列尾，允许插入  
    private int length; //队列当前长度 


    public void add(E e) {
        if(empty()) {
            rear = head = new Node<E>(e);
        }else {
            Node<E> newNode = new Node<E>(e);
            rear.next = newNode;//当前尾节点指针指向newNode
            rear = newNode;//尾节点变为newNode
        }
        length ++;
    }

    public E peek() {
        return (E)head.e;
    }

    public E poll() {
        if(empty()) {
            throw new RuntimeException("队空！");
        }
        E value = (E)head.e;
        head = head.next;
        length --;
        return value;
    }

    public int length() {
        return length;
    }
    
    public boolean empty() {
        return length == 0;
    }
}
