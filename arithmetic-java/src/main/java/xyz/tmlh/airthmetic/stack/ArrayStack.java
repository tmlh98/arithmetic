package xyz.tmlh.airthmetic.stack;

/**
 * <p>
 *   基于数组实现的顺序栈
 * </p>
 *
 * @author TianXin
 * @since 2019年5月6日上午11:12:20
 */
@SuppressWarnings("unchecked")
public class ArrayStack<E> {
    
    //基于数组实现
    private Object[] datas = null;
    
    private int top =-1;  //栈顶指针
    
    private int size = 1 << 4; //栈容量
    
    /**
     * 默认初始化一个长度为16的栈
     */
    public ArrayStack() {
        this.datas = new Object[size];
    }
    
    public ArrayStack(int initSize) {
        this.size = initSize;
        this.datas = new Object[size];
    }
    
    /**
     * 进栈
     * 
     * @param item
     * @return E
     */
    public void push(E item) {
        top ++;
        System.out.println(top);
        if(top > size -1) {
           throw new RuntimeException("栈满!"); 
        }
        datas[top] =item;
    }

    /**
     * 出栈
     * 
     * @return 
     * @return E
     */
    public E pop() {
        if(top == -1) {
            throw new RuntimeException("栈空!");  
        }
        return (E)datas[top--];
    }
    
    /**
     * 栈置空
     * 
     * @return 
     * @return boolean
     */
    public boolean empty() {
        return top == -1;
    }
    
    
    /**
     * 查看栈顶元素但不移除
     * @return 
     * @return E
     */
    public E peek(){
        if(top == -1) {
            throw new RuntimeException("栈空!");  
        }
        return (E)datas[top];
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
    
}
