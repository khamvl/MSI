package ru.academits.kharitonov.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable() {
        lists = new ArrayList[10];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity = " + capacity + ". But size must be > 0");
        }

        lists = new ArrayList[capacity];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public boolean add(E value) {
        int index = getIndex(value);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(value);
        size++;
        modCount++;

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(lists);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }

        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private final int startModCount = modCount;
        private int arrayIndex;
        private int listIndex = -1;
        private int passedElementsCount;

        public boolean hasNext() {
            return passedElementsCount < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The collection is over");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("The number of items in the collection has changed during the crawl");
            }

            while (true) {
                if (lists[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    listIndex++;

                    if (listIndex >= lists[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        passedElementsCount++;
                        break;
                    }
                }
            }

            return lists[arrayIndex].get(listIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (E value : this) {
            array[i] = value;
            i++;
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        System.arraycopy(toArray(), 0, a, 0, size);

        if (size < a.length) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        if (c.isEmpty()) {
            return false;
        }

        for (E value : c) {
            add(value);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        if (c.isEmpty()) {
            return false;
        }

        boolean hasChange = true;

        for (Object value : c) {
            if (!remove(value)) {
                hasChange = false;
            }
        }

        return hasChange;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            clear();

            return false;
        }

        boolean hasChange = false;

        for (ArrayList<E> currentList : lists) {
            if (currentList != null) {
                int startCurrentListSize = currentList.size();

                if (currentList.retainAll(c)) {
                    hasChange = true;
                    size -= startCurrentListSize - currentList.size();
                }
            }
        }

        return hasChange;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (ArrayList<E> currentList : lists) {
            if (currentList != null) {
                currentList.clear();
            }
        }

        size = 0;
        modCount++;
    }
}