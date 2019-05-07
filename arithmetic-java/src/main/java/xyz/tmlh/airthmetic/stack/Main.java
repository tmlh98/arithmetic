package xyz.tmlh.airthmetic.stack;

/**
 * <p>
 *   Main
 * </p>
 *
 * @author TianXin
 * @since 2019年5月6日上午11:40:06
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println(4 << 2);
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(5);
        stack.push(4);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        stack.push(10);
        stack.push(10);
        stack.push(10);
        stack.push(10);
        stack.push(10);
        stack.push(10);
        
        int search = stack.search(10);
        System.err.println(search);
        
    }
    
}
