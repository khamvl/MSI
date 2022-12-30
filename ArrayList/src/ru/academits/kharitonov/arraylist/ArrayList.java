package ru.academits.kharitonov.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] values;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private int modCount;

    public ArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size = " + size + ". But size must be >= 0");
        }

        //noinspection unchecked
        values = (E[]) new Object[size];
    }

    @SuppressWarnings("unchecked")
    public ArrayList() {
        values = (E[]) new Object[DEFAULT_CAPACITY];
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index = " + index + ". But index must be >= 0 and < " + size);
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
    public Iterator<E> iterator() {
        return new ListsIterator();
    }

    private class ListsIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int startModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The collection is over");
            }

            if (startModCount != modCount) {
                throw new ConcurrentModificationException("The collection has changed");
            }

            currentIndex++;
            return values[currentIndex];
        }
    }

    @Override
    public boolean add(E value) {
        add(size, value);

        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index = " + index + ". But index must be >= 0 and <= " + size);
        }

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
            //noinspection unchecked
            values = (E[]) new Object[DEFAULT_CAPACITY];

            return;
        }

        values = Arrays.copyOf(values, values.length * 2);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(values, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(values, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(values, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(values[i], object)) {
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

        //noinspection unchecked
        ArrayList<E> arrayList = (ArrayList<E>) object;

        if (size != arrayList.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(values[i], arrayList.values[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        for (int i = 0; i < size; i++) {
            if (values == null) {
                hash = prime * hash;
            } else {
                hash = prime * hash + values[i].hashCode();
            }
        }

        return hash;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return values[index];
    }

    @Override
    public E set(int index, E value) {
        checkIndex(index);

        E oldValue = values[index];
        values[index] = value;

        return oldValue;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedValue = values[index];

        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        values[size - 1] = null;
        size--;
        modCount++;

        return removedValue;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
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
        if (isEmpty() || collection.isEmpty()) {
            return false;
        }

        boolean hasChange = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(values[i])) {
                remove(i);
                hasChange = true;
                i--;
            }
        }

        return hasChange;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (isEmpty()) {
            return false;
        }

        if (collection.isEmpty()) {
            clear();
            return true;
        }

        boolean hasChange = false;

        for (int i = 0; i < size; i++) {
            if (!collection.contains(values[i])) {
                remove(i);
                hasChange = true;
                i--;
            }
        }

        return hasChange;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index = " + index + ". But index must be >= 0 and <= " + size);
        }

        if (collection.isEmpty()) {
            return false;
        }

        int finalSize = collection.size() + size;
        ensureCapacity(finalSize);

        System.arraycopy(values, index, values, index + collection.size(), size - index);

        int i = index;

        for (E currentValue : collection) {
            values[i] = currentValue;
            i++;
        }

        size = finalSize;

        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    /////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ///////////////////////////////////////////////

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}