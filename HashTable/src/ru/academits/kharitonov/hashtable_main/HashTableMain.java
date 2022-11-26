package ru.academits.kharitonov.hashtable_main;

import ru.academits.kharitonov.hashtable.ListItem;
import ru.academits.kharitonov.hashtable.HashTable;

import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<ListItem<Integer>> myHashTable = new HashTable<>();
        myHashTable.tableArray(10);

        ListItem<Integer> value1 = new ListItem<>(111);
        ListItem<Integer> value2 = new ListItem<>(222);
        ListItem<Integer> value3 = new ListItem<>(333);
        ListItem<Integer> value4 = new ListItem<>(444);
        ListItem<Integer> value5 = new ListItem<>(555);
        ListItem<Integer> value6 = new ListItem<>(666);
        ListItem<Integer> value7 = new ListItem<>(111);

        myHashTable.add(value1);
        myHashTable.add(value2);
        myHashTable.add(value3);
        myHashTable.add(value4);
        myHashTable.add(value5);
        myHashTable.add(value6);
        myHashTable.add(value7);

        System.out.println(myHashTable);
        System.out.println("Длина хэш-таблицы = " + myHashTable.size());

        if (myHashTable.isEmpty()) {
            System.out.println("Хэш таблица не пуста");
        } else {
            System.out.println("Хэш таблица пуста");
        }

        if (myHashTable.contains(value5)) {
            System.out.println("Хэш таблица содержит объект");
        } else {
            System.out.println("Хэш таблица не содержит объект");
        }

        System.out.println(Arrays.toString(myHashTable.toArray()));
    }
}