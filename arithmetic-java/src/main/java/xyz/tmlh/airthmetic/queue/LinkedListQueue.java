package xyz.tmlh.airthmetic.queue;

/**
 * 基于LinkedList实现队列结构
 * 使用java.util.Queue接口,其底层关联到一个LinkedList（双端队列）实例.
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E>{
    public boolean empty() {
        return false;
    }

    public boolean add(E e) {
        return false;
    }

    public E peek() {
        return null;
    }

    public E poll() {
        return null;
    }

    public int length() {
        return 0;
    }
}
