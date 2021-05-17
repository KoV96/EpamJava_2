package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    ListImpl list;

    public QueueImpl() {
        this.list = new ListImpl();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int index;

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return getNext(index++);
            } else {
                throw new NoSuchElementException();
            }
        }

        private Object getNext(int index) {
            ListImpl.Node currentNode = list.head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }
            return currentNode.getData();
        }

    }

    @Override
    public void enqueue(Object element) {
        list.addLast(element);
    }

    @Override
    public Object dequeue() {
        if (list.size() > 0) {
            Object obj = list.head.getData();
            list.remove(obj);
            return obj;
        } else {
            return null;
        }
    }

    @Override
    public Object top() {
        if (list.size() > 0) {
            return list.head.getData();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
       return list.toString();
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue(null);
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.top());
        System.out.println(queue);
        System.out.println(queue.size());
    }

}
