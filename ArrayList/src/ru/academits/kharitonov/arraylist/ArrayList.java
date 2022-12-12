package ru.academits.kharitonov.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] values;
    private int size;
    private int modCount;

    public ArrayList(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException("Size = " + size + ". But size must be => 0");
        }

        values = (T[]) new Object[size];
    }

    public ArrayList() {
        values = (T[]) new Object[10];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(values[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index = " + index + ". But index must be >= 0 and <= " + size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new myListIterator();
    }

    private class myListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int startModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The collection is over");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("The number of items in the collection has changed during the crawl");
            }

            currentIndex++;
            return values[currentIndex];
        }
    }

    @Override
    public boolean add(T value) {
        add(size, value);

        return true;
    }

    @Override
    public void add(int index, T value) {
        checkIndex(index);

        if (size >= values.length) {
            increaseCapacity();
        }

        System.arraycopy(values, index, values, index + 1, size - index);

        values[index] = value;
        size++;
        modCount++;
    }

    private void increaseCapacity() {
        if (values.length == 0) {
            values = (T[]) new Object[10];

            return;
        }

        values = Arrays.copyOf(values, values.length * 2);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(values, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < values.length) {
            return (T1[]) Arrays.copyOf(values, size, array.getClass());
        }

        System.arraycopy(values, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        ArrayList<T> arrayList = (ArrayList<T>) object;

        return Arrays.equals(values, arrayList.values) && size == arrayList.size && modCount == arrayList.modCount;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return values[index];
    }

    @Override
    public T set(int index, T value) {
        checkIndex(index);

        T oldValue = values[index];
        values[index] = value;

        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedValue = values[index];

        System.arraycopy(values, index + 1, values, index, size - index);
        size--;
        modCount++;

        return removedValue;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (!contains(object)) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public int lastIndexOf(Object object) {
        int lastIndex = -1;

        for (int i = 0; i < size; i++) {
            if (values[i].equals(object)) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            values[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object currentValue : collection) {
            if (!contains(currentValue)) {
                return false;
            }
        }

        return true;
    }

    public void trimToSize() {
        if (size < values.length) {
            values = Arrays.copyOf(values, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (values.length < minCapacity) {
            values = Arrays.copyOf(values, minCapacity);
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object currentValue : collection) {
            for (int i = 0; i < size; i++) {
                if (currentValue.equals(values[i])) {
                    remove(values[i]);
                    i--;
                }
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        for (int i = 0; i < size; i++) {
            if (!collection.contains(values[i])) {
                remove(values[i]);
                i--;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndex(index);

        if (isEmpty()) {
            return false;
        }

        System.arraycopy(values, index, values, index + collection.size(), size - index);

        int i = index;

        for (Object currentValue : collection) {
            values[i] = (T) currentValue;
            i++;
        }

        size = collection.size() + size;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        addAll(size, collection);

        return true;
    }

    /////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ///////////////////////////////////////////////

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}