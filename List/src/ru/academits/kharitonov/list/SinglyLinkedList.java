package ru.academits.kharitonov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("You specified index = " + index + ". But index must be >= 0 and < " + length);
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        return currentItem;
    }

    // 1. Получение размера списка
    public int getLength() {
        return length;
    }

    // 2. Получение значения первого элемента
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty!");
        }

        return head.getData();
    }

    // 3a. Получение значения по указанному индексу
    public T getByIndex(int index) {
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    // 3б. Изменение значения по указанному индексу
    // Изменение значения по индексу пусть выдает старое значение
    public T setByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> currentItem = getItemByIndex(index);
        T oldData = currentItem.getData();
        currentItem.setData(data);

        return oldData;
    }

    // 4. Удаление элемента по индексу
    // Пусть выдает значение элемента
    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        length--;

        return removedData;
    }

    // 5. Вставка элемента в начало
    public void insertFirst(T data) {
        head = new ListItem<>(data, head);
        length++;
    }

    // 6. Вставка элемента по индексу
    public void insertByIndex(int index, T data) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("You specified index = " + index + ". But index must be >= 0 and <= " + length);
        }

        if (index == 0) {
            insertFirst(data);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
    }

    // 7. Удаление узла по значению,
    // Пусть выдает true, если элемент был удален
    public boolean removeByData(T data) {
        if (isEmpty()) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeFirst();
            return true;
        }

        ListItem<T> currentItem = head;

        while (currentItem.getNext() != null) {
            if (Objects.equals(currentItem.getNext().getData(), data)) {
                currentItem.setNext(currentItem.getNext().getNext());
                length--;

                return true;
            }

            currentItem = currentItem.getNext();
        }

        return false;
    }

    // 8. Удаление первого элемента
    // Пусть выдает значение элемента
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty!");
        }

        T removedData = head.getData();
        head = head.getNext();
        length--;

        return removedData;
    }

    // 9. Разворот списка за линейное время
    public void reverse() {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            ListItem<T> temp = currentItem.getNext();

            currentItem.setNext(previousItem);
            previousItem = currentItem;
            head = currentItem;

            currentItem = temp;
        }
    }

    // 10. Копирование списка
    public SinglyLinkedList<T> copy() {
        if (isEmpty()) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();
        listCopy.length = length;

        ListItem<T> currentItem = new ListItem<>(head.getData());
        listCopy.head = currentItem;

        for (ListItem<T> nextItem = head.getNext(); nextItem != null; nextItem = nextItem.getNext()) {
            currentItem.setNext(new ListItem<>(nextItem.getData()));
            currentItem = currentItem.getNext();
        }

        return listCopy;
    }

    private boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (ListItem<T> currentItem = head; currentItem != null; currentItem = currentItem.getNext()) {
            stringBuilder.append(currentItem.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("]");

        return stringBuilder.toString();
    }
}