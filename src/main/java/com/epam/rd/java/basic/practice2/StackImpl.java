package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    ListImpl stack = new ListImpl();

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int index = stack.size();

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return getNext(index--);
            } else {
                throw new NoSuchElementException();
            }
        }

        private Object getNext(int index) {
            ListImpl.Node currentNode = stack.head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }
            return currentNode.getData();
        }

    }

    @Override
    public void push(Object element) {
        stack.addLast(element);
    }

    @Override
    public Object pop() {
        if (stack.size() > 0) {
            Object element = stack.getLast();
            stack.removeLast();
            return element;
        } else {
            return null;
        }
    }

    @Override
    public Object top() {
        if (stack.size() > 0) {
            return stack.getLast();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        Stack test = new StackImpl();
        test.push("A");
        test.push("B");
        test.push("C");
        test.push(null);
        System.out.println(test);
        test.pop();
        System.out.println(test);
    }

}
