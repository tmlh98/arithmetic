package xyz.tmlh.airthmetic.queue;

/**
 * 队列的顺序存储结构实现(循环队列),
 * 每次出队需要移动元素位置,时间复杂度O(n)
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class ArrayQueue<E> implements Queue<E>{
    
    private Object[] datas;
    private int size = 1 << 4; //队列容量
    private int head;  //队列头，允许删除
    private int rear;   //队列尾，允许插入
    
    public ArrayQueue() {
        datas = new Object[size];
    }
    
    public ArrayQueue(int size) {
        this.size = size;
        datas = new Object[size];
    }

    public boolean empty() {
        return head == rear;
    }

    public void add(E e) {
        if(rear - head == size) {
            size <<= 1;
            Object [] newDatas = new Object[size];
            System.arraycopy(datas, 0, newDatas, 0, datas.length);
            datas = newDatas;
        }
        datas[rear ++ ] = e;
    }

    public E peek() {
        if(empty()) {
            throw new RuntimeException("队空!");
        }
        return (E)datas[head];
    }

    public E poll() {
        if(empty()) {
            throw new RuntimeException("队空!");
        }
        E value = (E)datas[head];
        for (int i = head; i < rear - 1; i++) {
            datas[i] = datas[i + 1];
        }
        rear --;
        return value;
    }

    public int length() {
        return rear - head;
    }
    
}
