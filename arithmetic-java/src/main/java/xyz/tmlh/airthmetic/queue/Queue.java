package xyz.tmlh.airthmetic.queue;

/**
 * 队列的接口定义(先入先出)
 * @param <E>
 */
public interface Queue<E> {

    /**
     * 判空
     */
    public boolean empty();

    /**
     * 插入
     */
    public void add(E e);

    /**
     * 返回队首元素，但不删除
     */
    public E peek();

    /**
     * 出队
     */
    public E poll();

    /**
     * 队列长度
     */
    public int length();
}
