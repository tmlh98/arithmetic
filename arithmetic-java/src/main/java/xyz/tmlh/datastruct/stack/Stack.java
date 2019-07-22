package xyz.tmlh.datastruct.stack;

/**
 * <p>
 *   栈：LIFO（后进先出）
 * </p>
 *
 * @author TianXin
 * @since 2019年5月6日下午1:39:27
 */
public interface Stack<E> {

    /**
     * 进栈
     * 
     * @param item
     * @return E
     */
    public void push(E item);
    
    /**
     * 出栈
     * 
     * @return 
     * @return E
     */
    public E pop();
    
    /**
     * 栈是否为空
     * 
     * @return 
     * @return boolean
     */
    public boolean empty();
    
    /**
     * 查看栈顶元素但不移除
     * @return 
     * @return E
     */
    public E peek();

}
