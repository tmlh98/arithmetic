package xyz.tmlh.datastruct.queue;

public class Main {
    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<String>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        while (!queue.empty()) {
            System.out.println(queue.poll());
        }
        System.out.println(queue.poll());
        System.out.println(queue.remove());
    }

    private static void testQueue(Queue<?> q) {
        Queue<Object> queue = (Queue<Object>)q;
        queue.add(1);
        queue.add(2);
        System.out.println(queue.poll());
        queue.add(3);
        queue.add(4);
        System.out.println(queue.poll());
        queue.add(5);
        System.out.println(queue.peek());
        System.out.println(queue.length());
        System.out.println("============================");
        while (!queue.empty()) {
            System.out.println(queue.poll());
        }
    }
}
