package xyz.tmlh.airthmetic.stack;

import java.util.Arrays;

/**
 * <p>
 *   基于数组实现的顺序栈
 * </p>
 *
 * @author TianXin
 * @since 2019年5月6日上午11:12:20
 */
@SuppressWarnings("unchecked")
public class ArrayStack<E> implements Stack<E>{
    
    //基于数组实现
    private Object[] datas;
    
    private int top =-1;  //栈顶指针
    
    private int size = 1 << 4; //栈容量

    public ArrayStack() {
        this.datas = new Object[size];
    }
    
    public ArrayStack(int initSize) {
        this.size = initSize;
        this.datas = new Object[size];
    }

    public void push(E item) {
        top ++;
        if(top > size -1) {
            //栈容量扩增
            size <<= 1;
            Object[] newDatas =  new Object[size];
            System.arraycopy(datas , 0 , newDatas  , 0 , datas.length);
            datas = newDatas;
        }
        datas[top] =item;
    }

    public E pop() {
        if(top == -1) {
            throw new RuntimeException("栈空!");  
        }
        return (E)datas[top--];
    }

    public boolean empty() {
        return top == -1;
    }


    public E peek(){
        if(top == -1) {
            throw new RuntimeException("栈空!");  
        }
        return (E)datas[top];
    }
    
    /**
     * 返回栈的元素个数
     */
    public int size(){
        return top + 1;
    }

    /**
     * 返回对象在堆栈中的位置,以 1为基数
     */
    public int search(Object o) {
        int tmp = top;
        
        while (top != -1) {
            if(o == pop()) {
                break;
            }
        }
        int index = top + 2;
        top =tmp;
        return index;
    }

    @Override
    public String toString() {
        return "ArrayStack [datas=" + Arrays.toString(datas) + ", top=" + top + ", size=" + size + "]";
    }

    
    
}
