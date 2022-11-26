package ru.academits.kharitonov.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] list;
    private int size;

    private ArrayList<T> iter;

    public HashTable() {
    }

    public void tableArray(int size) {
        this.size = size;
        list = new ArrayList[size];
    }

    private int getIndex(T t) {
        return Math.abs(t.hashCode() % list.length);
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);

        if (list[index] == null) {
            list[index] = new ArrayList<>();
        }

        list[index].add(t);

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(list);
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            if (list[i] != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex((T) o);

        if (list[index] == null) {
            return false;
        }

        return list[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            ++currentIndex;
            return (T) list[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[20];

        for (int i = 0; i < size; i++) {
            if (list[i] == null) {
                continue;
            }

            for (int j = 0; j < list[i].size(); j++) {
                newArray[i] = list[i].get(j);
            }
        }

        return newArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
