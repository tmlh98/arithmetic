package xyz.tmlh.airthmetic.example.linked;

import java.util.Scanner;

/*
 * JosephCircle(约瑟夫问题)链表实现
 * 
 * N个人围成一圈，从第一个人开始报数，报到m的人出圈， 剩下的人继续从1开始报数，
 * 报到m的人出圈； 如此往复，直到所有人出圈。（模拟此过程，输出出圈的人的序号）
 */
public class JosephCircle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入人数：");
        int n = sc.nextInt();
        System.out.println("请输入从第几个人出圈：");
        int m = sc.nextInt();
        CircleLinked circleLinked = new CircleLinked(n);
        circleLinked.pop(m);
        sc.close();
    }
}
     
class CircleLinked {

    private int size;// 圆环的大小
    private Person first;// 圆环的第一个节点

    public CircleLinked(int size) {
        this.size = size;
        init();
    }

    private void init() {
        if (size < 1) {
            throw new RuntimeException("size less then 1");
        }
        first = new Person(1);
        if (size == 1) {
            first.next = first; // 自己指向自己
            return;
        }

        Person last = first;

        for (int i = 2; i < size; i++) {
            Person person = new Person(i);
            last.next = person;
            last = person;
        }
        // 构建圆环末端
        last.next = new Person(size, first);
    }

    public void show() {
        Person curr = first;
        while (true) {
            System.out.printf("%d\t", curr.no);
            curr = curr.next;
            if (curr == first) {
                break;
            }
        }
    }

    /*
     * 出圈
     */
    public void pop(int m) {

        Person curr = first;
        Person last = first;
        // 获取最后一个元素
        for (int i = 0; i < size - 1; i++) {
            last = first.next;
        }

        while (true) {
            if (curr == last) {
                System.out.printf("%d\t" , curr.no);
                break;
            }
            
            // 循环 m -1次
            for (int i = 0; i < m - 1; i++) {
                last = curr;
                curr = curr.next;// 当前元素后移
            }
            
            System.out.printf("%d\t" , curr.no);
            curr = curr.next;// 当前元素后移
            last.next = curr;//移除last.next
        }

    }
    
    class Person {
        private int no;// 编号
        private Person next;// 下一个节点

        public Person(int no) {
            this.no = no;
        }

        public Person(int no, Person next) {
            this.no = no;
            this.next = next;
        }

    }

}

