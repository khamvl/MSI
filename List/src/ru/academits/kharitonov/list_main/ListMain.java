package ru.academits.kharitonov.list_main;

import ru.academits.kharitonov.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertFirst(20);
        list1.insertFirst(40);
        list1.insertFirst(60);
        list1.insertFirst(80);
        list1.insertFirst(100);
        list1.insertFirst(150);

        System.out.println("Изначальный список: " + list1);
        System.out.println("1.  Длина списка: " + list1.getLength());
        System.out.println("2.  Значение первого элемента списка: " + list1.getFirst());
        System.out.println("3а. Значение по указанному индексу: " + list1.getByIndex(1));

        System.out.println("-----------------------------------------------------------------");
        System.out.println("3б. Значение элемента до изменения: " + list1.setByIndex(3, 77));
        System.out.println("Список после изменения элемента: " + list1);
        System.out.println("-----------------------------------------------------------------");

        System.out.println("4.  Значение элемента до удаления: " + list1.removeByIndex(5));
        System.out.println("Список после уделения элемента: " + list1);
        System.out.println("-----------------------------------------------------------------");

        list1.insertItemByIndex(5, 222);
        System.out.println("Список после вставки элемента по индексу: " + list1);
        System.out.println("-----------------------------------------------------------------");

        if (list1.removeByData(78)) {
            System.out.println("7.  Элемент удален");
        } else {
            System.out.println("7.  Элемент не был удален");
        }

        System.out.println("Список после удаление элемента по значению: " + list1);
        System.out.println("-----------------------------------------------------------------");

        System.out.println("8.  Значение первого элемента перед удалением: " + list1.removeFirst());
        System.out.println("Список после удаления первого элемента: " + list1);
        System.out.println("-----------------------------------------------------------------");

        list1.reverse();
        System.out.println("Список после разворота: " + list1);
        System.out.println("Копия списка: " + list1.copy());
    }
}