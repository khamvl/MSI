package ru.academits.kharitonov.list_main;

import ru.academits.kharitonov.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.insertElementAtBegin(20);
        list1.insertElementAtBegin(30);
        list1.insertElementAtBegin(40);
        list1.insertElementAtBegin(50);

        System.out.println("1.  Длина списка: " + list1.getListLength());
        System.out.println("2.  Значение первого элемента списка: " + list1.getFirstElementValue());
        System.out.println("3а. Значение по указанному индексу: " + list1.getValueAtSpecifiedIndex(2));
        System.out.println("3б. Значение элемента до изменения: " + list1.setValueAtSpecifiedIndex(99, 1));
        System.out.println("4.  Значение элемента до удаления: " + list1.removeElementByIndex(1));

        list1.insertElementByIndex(222, 1);

        boolean isRemote = list1.removeItemByValue(20);

        if (isRemote) {
            System.out.println("7.  Элемент удален");
        } else {
            System.out.println("7.  Элемент не был удален");
        }

        System.out.println("8.  Значение первого элемента перед удалением: " + list1.removeFirstElement());
        
        list1.getExpandedList();
        list1.getCopiedList();
    }
}