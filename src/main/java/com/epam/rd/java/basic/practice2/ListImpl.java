package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    Node head;
    private int size;

    static class Node {
        private Object data;
        private Node nextNode;

        Node(Object data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public Object getData() {
            return data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }

    @Override
    public void clear() {
        this.head = new Node(null, null);
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int index;

        @Override
        public boolean hasNext() {
            return index < size;
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
            Node currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }
            return currentNode.getData();
        }
    }


    @Override
    public void addFirst(Object element) {
        try {
            this.head = new Node(element, head);
            size++;
        } catch (NullPointerException e) {
            this.head = new Node(element, null);
            size++;
        }
    }

    @Override
    public void addLast(Object element) {
        if (head == null) {
            head = new Node(element, null);
        } else {
            Node checkNode = getLastNode();
            checkNode.setNextNode(new Node(element, null));
        }
        size++;
    }

    public Node getLastNode() {
        Node checkNode = this.head;
        for (int i = 1; i < size; i++) {
            checkNode = checkNode.getNextNode();
        }
        return checkNode;
    }

    @Override
    public void removeFirst() {
        try {
            Node nexNode = head.getNextNode().getNextNode();
            this.head = new Node(head.getNextNode().getData(), nexNode);
            size--;
        } catch (NullPointerException e) {
            try {
                this.head = new Node(head.getNextNode().getData(), null);
                size--;
            } catch (NullPointerException e2) {
                this.head = new Node(null, null);
                size--;
            }
        }
    }

    @Override
    public void removeLast() {
        Node currentNode = this.head;
        if (size > 1) {
            for (int i = 1; i < size - 1; i++) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.nextNode = null;
        } else {
            this.head = null;
        }
        size--;
    }

    @Override
    public Object getFirst() {
        return this.head != null ? this.head.getData() : null;
    }

    @Override
    public Object getLast() {
        if (size > 1) {
            try {
                return getLastNode().getData();
            } catch (NullPointerException e) {
                return null;
            }
        } else {
            return this.head == null ? null : this.head.getData();
        }
    }

    @Override
    public Object search(Object element) {
        if (element == null) {
            return null;
        }
        Node currentNode = this.head;
        try {
            while (!currentNode.getData().equals(element)) {
                currentNode = currentNode.getNextNode();
            }
            return currentNode.getData();
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public boolean remove(Object element) {
        Node currentNode = this.head;
        if (currentNode.getData() == element) {
            this.head = currentNode.getNextNode();
            size--;
            return true;
        }
        try {
            for (int i = 1; i < size; i++) {
                if (currentNode.getNextNode().getData() == element) {
                    currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                    size--;
                    return true;
                } else {
                    currentNode = currentNode.getNextNode();
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node currentNode = this.head;
        while (currentNode != null) {
            sb.append(currentNode.getData());
            if (currentNode.getNextNode() != null) {
                sb.append(", ");
            } else {
                break;
            }
            currentNode = currentNode.getNextNode();
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast(null);
        list.addLast("Z");
        System.out.println(list.size);
        list.removeLast();
        System.out.println(list.size);
        Iterator<Object> itr = list.iterator();
        for (Iterator i = itr; i.hasNext(); ) {
            System.out.println(i.next());
        }
    }
}
