package ru.academits.kharitonov.arraylist_main;

import ru.academits.kharitonov.arraylist.ArrayList;

import java.util.Arrays;

public class ArrayListMain {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(10);

        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);
        arrayList1.add(4);

        System.out.println("Изначальный список: " + arrayList1);
        System.out.println("--------------------------------------------");

        arrayList1.add(0, 55);
        arrayList1.add(1, 66);
        arrayList1.add(5, 777);

        System.out.println("Список после добавления элементов по индексам: " + arrayList1);
        System.out.println("--------------------------------------------");

        System.out.println("Список как массив: " + Arrays.toString(arrayList1.toArray()));
        System.out.println("--------------------------------------------");

        System.out.println("Список как массив: " + Arrays.toString(arrayList1.toArray(new Integer[1])));
        System.out.println("--------------------------------------------");

        int firstOccurrenceIndex = arrayList1.indexOf(4);

        if (firstOccurrenceIndex == -1) {
            System.out.println("Элемента нет в списке");
        } else {
            System.out.println("Элемент находится под индексом: " + firstOccurrenceIndex);
        }

        System.out.println("--------------------------------------------");

        if (arrayList1.contains(3)) {
            System.out.println("Элемент находится в списке");
        } else {
            System.out.println("Элемента нет в списке");
        }

        System.out.println("--------------------------------------------");

        System.out.println("Элемент под индексом: " + arrayList1.get(1));
        System.out.println("--------------------------------------------");

        System.out.println("Элемент, находившийся под индексом до изменения: " + arrayList1.set(4, 333));
        System.out.println("Список после замены элемента под индексом: " + arrayList1);
        System.out.println("--------------------------------------------");

        System.out.println("Элемент, находившийся под индексом, который был удален: " + arrayList1.remove(0));
        System.out.println("Список после удаления элемента по индексу: " + arrayList1);
        System.out.println("--------------------------------------------");

        Object value1 = 666;

        if (arrayList1.remove(value1)) {
            System.out.println("Элемент " + value1 + " был удален");
        } else {
            System.out.println("Элемент не удален");
        }

        System.out.println("Список после удаления элемента по значению: " + arrayList1);
        System.out.println("--------------------------------------------");

        arrayList1.add(0, 4);
        System.out.println("Список после добавления в него элемента под индексом: " + arrayList1);

        int lastOccurrenceIndex = arrayList1.lastIndexOf(4);

        if (lastOccurrenceIndex == -1) {
            System.out.println("Элемента нет в списке");
        } else {
            System.out.println("Индекс последнего вхождения элемента: " + lastOccurrenceIndex);
        }

        System.out.println("--------------------------------------------");

        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(66);
        arrayList2.add(333);
        arrayList2.add(777);

        if (arrayList1.containsAll(arrayList2)) {
            System.out.println("Список содержит все элементы переданной коллекции");
        } else {
            System.out.println("Список содержит не все элементы переданной коллекции");
        }

        System.out.println("--------------------------------------------");

        System.out.println("Список1: " + arrayList1);
        System.out.println("Список2: " + arrayList2);

        arrayList1.removeAll(arrayList2);
        System.out.println("Список1 после удаления из него элементов из списка2: " + arrayList1);
        System.out.println("--------------------------------------------");

        arrayList1.add(3);
        arrayList1.add(5, 10);

        ArrayList<Integer> arrayList3 = new ArrayList<>();
        arrayList3.add(10);
        arrayList3.add(4);
        arrayList3.add(3);

        System.out.println("Список1: " + arrayList1);
        System.out.println("Список3: " + arrayList3);

        arrayList1.retainAll(arrayList3);
        System.out.println("Список1 после оставления в нем элементов из списка3: " + arrayList1);
        System.out.println("--------------------------------------------");

        ArrayList<Integer> arrayList4 = new ArrayList<>();
        arrayList4.add(999);
        arrayList4.add(888);
        arrayList4.add(777);

        System.out.println("Список1: " + arrayList1);
        System.out.println("Список4: " + arrayList4);

        arrayList1.addAll(1, arrayList4);
        System.out.println("Список1 после добавления в него списка4: " + arrayList1);
        System.out.println("--------------------------------------------");

        arrayList1.addAll(arrayList4);
        System.out.println("Список1 после добавления в него списка4: " + arrayList1);
        System.out.println("--------------------------------------------");

        arrayList1.clear();
        System.out.println("Список после чистки: " + arrayList1);
    }
}