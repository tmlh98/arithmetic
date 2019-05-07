package xyz.tmlh.airthmetic.queue;

public class Main {
    public static void main(String[] args) {
        testQueue(new LinkQueue<Integer>());
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
