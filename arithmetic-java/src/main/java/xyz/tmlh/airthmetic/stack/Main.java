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
//        linkStack();
        int a = 4 ;
        a <<= 1;
        System.out.println(a<< 1);
        System.out.println(a);
    }

    private static void linkStack() {
        LinkStack<Integer> stack = new LinkStack<Integer>();
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



        System.out.println(stack.length());
        while (!stack.empty()){
            System.out.println(stack.peek());
        }
        System.out.println(stack.pop());


    }


    private static void  arrayStack(){
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
    }
    
}
