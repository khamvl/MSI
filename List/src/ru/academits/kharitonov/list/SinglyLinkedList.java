package ru.academits.kharitonov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    //1. получение размера списка
    public int getLength() {
        return length;
    }

    private void checkingIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException("You specified index = " + index + ". But index must be >= 0 and < " + length);
        }
    }

    private boolean arms (int index, int i) {
        return index == i;
    }

    //2. получение значения первого элемента
    public T getFirst() {
        if (head == null) {
            throw new IllegalArgumentException("List is empty!");
        }

        return head.getData();
    }

    //3a. получение значения по указанному индексу
    public T getValue(int index) {
        checkingIndex(index);
        int i = 0;

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            if (arms(index, i)) {
                return currentItem.getData();
            }

            i++;
        }

        return head.getData();
    }

    //3б. изменение значения по указанному индексу
    // Изменение значения по индексу пусть выдает старое значение
    public T setValue(int index, T value) {
        checkingIndex(index);

        T oldValue = null;

        int i = 0;

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            if (arms(index, i)) {
                oldValue = currentItem.getData();
                currentItem.setData(value);
                break;
            }

            i++;
        }

        return oldValue;
    }

    //4. удаление элемента по индексу
    // пусть выдает значение элемента
    public T removeByIndex(int index) {
        checkingIndex(index);

        T deletedData = null;

        if (index == 0) {
            return removeFirst();
        }

        int i = 0;

        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (arms(index, i)) {
                deletedData = currentItem.getData();
                previousItem.setNext(currentItem.getNext());
                length--;
                break;
            }

            i++;
        }

        return deletedData;
    }

    //5. вставка элемента в начало
    public void insertFirst(T value) {
        head = new ListItem<>(value, head);
        length++;
    }

    //6. вставка элемента по индексу
    public void insertElementByIndex(int index, T value) {
        checkingIndex(index);

        if (index == 0) {
            insertFirst(value);
            return;
        }

        int i = 0;

        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (arms(index, i)) {
                ListItem<T> item = new ListItem<>(value, previousItem.getNext());
                previousItem.setNext(item);
                length++;
                break;
            }

            i++;
        }
    }

    //7. удаление узла по значению,
    // пусть выдает true, если элемент был удален
    public boolean removeByMatch(T value) {
        if (value == head.getData()) {
            head = head.getNext();
            length--;
            return true;
        }

        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (value == currentItem.getData()) {
                previousItem.setNext(currentItem.getNext());
                length--;
                return true;
            }
        }

        return false;
    }

    //8. удаление первого элемента
    // пусть выдает значение элемента
    public T removeFirst() {
        if (head == null) {
            throw new IllegalArgumentException("List is empty!");
        }

        T deletedData = head.getData();
        head = head.getNext();
        length--;

        return deletedData;
    }

    //9. разворот списка за линейное время
    public void deploy() {
        int i = 1;
        ListItem<T> currentItem = head;

        while (i <= length / 2) {
            T valueFromEnd = setValue(length - i, currentItem.getData());
            currentItem.setData(valueFromEnd);

            currentItem = currentItem.getNext();
            i++;
        }
    }

    //10. копирование списка
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();

        ListItem<T> oldItem = null;
        int i = 0;

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            ListItem<T> item = new ListItem<>(currentItem.getData());

            if (i == 0) {
                listCopy.head = new ListItem<>(currentItem.getData());
            } else if (i == 1) {
                oldItem = item;
                listCopy.head.setNext(oldItem);
            } else {
                oldItem.setNext(item);
                oldItem = item;
            }

            listCopy.length++;
            i++;
        }

        return listCopy;
    }

    @Override
    public String toString() {
        ListItem<T> currentItem = head;

        if (currentItem == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            stringBuilder.append(currentItem.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("]");

        return stringBuilder.toString();
    }
}