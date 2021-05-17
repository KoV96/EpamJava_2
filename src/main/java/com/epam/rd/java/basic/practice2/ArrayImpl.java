package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] array;
    int addSize;

    public ArrayImpl(Object[] array) {
        this.array = array;
    }

    public ArrayImpl() {
        this.array = new Object[]{null};
    }

    public ArrayImpl(int capacity) {
        this.array = new Object[capacity];
    }

    @Override
    public void clear() {
        this.array = null;
    }

    @Override
    public int size() {
        return array == null ? 0 : array.length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int index;

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Object next() {
            if (hasNext()){
                return array[index++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public void add(Object element) {
        int length = (array != null ? array.length : 0);
        try {
            assert array != null;
            array[addSize] = element;
            addSize++;
        } catch (ArrayIndexOutOfBoundsException e) {
            Object[] newArray = new Object[length + 1];
            for (int i = 0; i < newArray.length; i++) {
                if (i == newArray.length - 1) {
                    newArray[i] = element;
                } else {
                    newArray[i] = array[i];
                }
            }
            array = newArray;
            addSize++;
        }
    }

    @Override
    public void set(int index, Object element) {
        try {
            array[index] = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such position --> " + e.getMessage());
        }
    }

    @Override
    public Object get(int index) {
        if (index < array.length) {
            return array[index];
        }
        System.out.println("No such index");
        return null;
    }

    @Override
    public int indexOf(Object element) {
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(element)) {
                    return i;
                }
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }

        return -1;
    }

    @Override
    public void remove(int index) {
        if (index < array.length) {
            int pos = 0;
            Object[] newArray = new Object[array.length - 1];
            for (int i = 0; i < newArray.length; i++){
                if (i == index) {
                    newArray[i] = array[++pos];
                } else {
                    newArray[i] = array[pos];
                }
                pos ++;
            }
            this.array = newArray;
        } else {
            System.out.println("No such index element!!!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        try {
            for (int i = 0; i < array.length; i++) {
                if (i == array.length - 1) {
                    sb.append(array[i]).append("]");
                } else {
                    sb.append(array[i]).append(", ");
                }
            }
            return sb.toString();
        } catch (NullPointerException e) {
            return sb.append("]").toString();
        }
    }

    public static void main(String[] args) {
        ArrayImpl array1 = new ArrayImpl(4);
        array1.add("A");
        array1.add("B");
        array1.add("C");
        array1.remove(1);
        System.out.println(array1);
    }
}
