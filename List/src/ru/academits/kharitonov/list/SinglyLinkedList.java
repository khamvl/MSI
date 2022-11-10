package ru.academits.kharitonov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int listLength;

    //1. получение размера списка
    public int getListLength() {
        int listLength = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            listLength++;
        }

        return listLength;
    }

    //2. получение значения первого элемента
    public T getFirstElementValue() {
        return head.getData();
    }

    //3a. получение значения по указанному индексу
    public T getValueAtSpecifiedIndex(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0");
        }

        if (index == 0) {
            return head.getData();
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                return p.getData();
            }

            i++;
        }


        return null;
    }

    //3б. изменение значения по указанному индексу
    // Изменение значения по индексу пусть выдает старое значение
    public T setValueAtSpecifiedIndex(T data, int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0");
        }

        T oldValue = null;

        if (index == 0) {
            oldValue = head.getData();
            head = new ListItem<>(data, head.getNext());

            return oldValue;
        }

        int i = 0;

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (index == i) {
                oldValue = p.getData();
                ListItem<T> item = new ListItem<>(data, p.getNext());
                prev.setNext(item);
            }

            i++;
        }

        return oldValue;
    }

    //4. удаление элемента по индексу
    // пусть выдает значение элемента
    public T removeElementByIndex(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0");
        }

        T oldValue = null;

        if (index == 0) {
            oldValue = head.getData();
            head = head.getNext();
            return oldValue;
        }

        int i = 0;

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (index == i) {
                oldValue = p.getData();
                prev.setNext(p.getNext());
            }

            i++;
        }

        return oldValue;
    }

    //5. вставка элемента в начало
    public void insertElementAtBegin(T data) {
        head = new ListItem<>(data, head);
    }

    //6. вставка элемента по индексу
    public void insertElementByIndex(T data, int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0");
        }

        if (index == 0) {
            head = new ListItem<>(data, head);
        } else {
            int i = 0;

            for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
                if (index == i) {
                    ListItem<T> item = new ListItem<>(data, prev.getNext());
                    prev.setNext(item);
                    break;
                }

                i++;
            }
        }
    }

    //7. удаление узла по значению,
    // пусть выдает true, если элемент был удален
    public boolean removeItemByValue(T data) {
        if (data == head.getData()) {
            head = head.getNext();

            return true;
        }

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (data == p.getData()) {
                prev.setNext(p.getNext());

                return true;
            }
        }

        return false;
    }

    //8. удаление первого элемента
    // пусть выдает значение элемента
    public T removeFirstElement() {
        T oldValue = head.getData();
        head = head.getNext();
        return oldValue;
    }

    //9. разворот списка за линейное время
    public SinglyLinkedList<T> getExpandedList() {
        SinglyLinkedList<T> reverseList = new SinglyLinkedList<>();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            reverseList.insertElementAtBegin(p.getData());
        }

        return reverseList;
    }

    //10. копирование списка
    public SinglyLinkedList<T> getCopiedList() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            listCopy.insertElementAtBegin(p.getData());
        }

        return listCopy.getExpandedList();
    }
}