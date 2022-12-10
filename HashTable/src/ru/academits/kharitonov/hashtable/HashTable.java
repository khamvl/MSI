package ru.academits.kharitonov.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] list;
    private int size;
    private int modCount;

    public HashTable() {
        list = new ArrayList[10];
    }

    public HashTable(int size) {
        if (size <= 0) {
            throw new NegativeArraySizeException("Size = " + size + ". But size must be > 0");
        }

        list = new ArrayList[size];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % list.length);
    }

    @Override
    public boolean add(T value) {
        int index = getIndex(value);

        if (list[index] == null) {
            list[index] = new ArrayList<>();
        }

        list[index].add(value);
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
        return Arrays.toString(list);
    }

    @Override
    public boolean isEmpty() {
        for (ArrayList<T> currentList : list) {
            if (currentList != null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }

        int index = getIndex(o);

        return list[index] != null && list[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T> {
        private int start = modCount;
        private int arrayIndex;
        private int listIndex = -1;
        private int totalElementalQuantity;

        public boolean hasNext() {
            return totalElementalQuantity < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The collection is over");
            }

            if (start != modCount) {
                throw new ConcurrentModificationException("The number of items in the collection has changed during the crawl");
            }

            while (true) {
                if (list[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    listIndex++;

                    if (listIndex >= list[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        totalElementalQuantity++;
                        break;
                    }
                }
            }

            return list[arrayIndex].get(listIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (T value : this) {
            array[i] = value;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(toArray(), size);
        }

        T1[] temp = (T1[]) toArray();

        for (int i = 0; i < size; i++) {
            if (i < temp.length) {
                a[i] = temp[i];
            } else {
                a[i] = null;
            }
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (contains(o)) {
            list[index].remove(o);
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
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            throw new NullPointerException("Collection is empty");
        }

        for (T value : c) {
            add(value);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            throw new NullPointerException("Collection is empty");
        }

        for (Object value : c) {
            remove(value);
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        HashTable<T> hashTable = (HashTable<T>) o;

        return list == hashTable.list && size == hashTable.size && modCount == hashTable.modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            return true;
        }

        boolean hasChange = false;

        for (ArrayList<T> currentList : list) {
            if (currentList != null) {
                int startCurrentListSize = currentList.size();

                if (currentList.retainAll(c)) {
                    hasChange = true;
                    size = size - startCurrentListSize - currentList.size();
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

        for (ArrayList<T> currentList : list) {
            if (currentList != null) {
                currentList.clear();
            }
        }

        size = 0;
        modCount++;
    }
}