package com.epam.rd.java.basic.practice2;

public class Demo {

    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl(4);
        array.add("A");
        array.add("C");
        ListImpl list = new ListImpl();
        list.addFirst("CCC");
        list.addFirst("BBB");
        list.addFirst("CCC");
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue(null);
        StackImpl stack = new StackImpl();
        stack.push("Aaa");
        stack.push("Bbb");
        stack.push("Ccc");
        stack.push("Ddd");
        System.out.println("---------------------ArrayList-----------------------");
        System.out.println(array);
        System.out.println("---------------------List-----------------------");
        System.out.println(list.getLast());
        System.out.println(list);
        System.out.println("---------------------Queue-----------------------");
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println("---------------------Stack-----------------------");
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
