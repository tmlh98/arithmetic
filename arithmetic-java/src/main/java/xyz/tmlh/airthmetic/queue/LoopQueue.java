package xyz.tmlh.airthmetic.queue;

import java.util.Arrays;

/**
 * 循环队列的顺序存储结构实现(环形数组)
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class LoopQueue<E> implements Queue<E> {
    
    private Object[] datas;
    private int capacity = 1 << 4;//队列容量
    
    private int length ; //队列当前长度
    private int head;  //队列头，允许删除
    private int rear;   //队列尾，允许插入
    
    public LoopQueue() {
        datas = new Object[capacity];
    }
    
    public LoopQueue(int size) {
        this.capacity = size;
        datas = new Object[capacity];
    }

    public boolean empty() {
        return length == 0;
    }

    public void add(E e) {
        if(length == capacity) {
            throw new RuntimeException("队满");
        }
        
        datas[rear]  = e;
        length ++;
        rear = (rear + 1) % capacity;
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
        head = (head+1) % capacity;  //队首指针加1
        length --;
        return value;
    }

    public int length() {
        return length;
    }
    
    /**
     * 清空循环队列
     */
    public void clear(){
        Arrays.fill(datas, null);
        capacity = 0;
        head = 0;
        rear = 0;
    }
    
}
