package ru.academits.kharitonov.hashtable_main;

import ru.academits.kharitonov.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<String> myHashTable = new HashTable<>(5);

        myHashTable.add(null);
        myHashTable.add("1");
        myHashTable.add("2");
        myHashTable.add("3");
        myHashTable.add("4");
        myHashTable.add("5");

        System.out.println(myHashTable);
        System.out.println("---------------------------------");

        if (myHashTable.isEmpty()) {
            System.out.println("Таблица пуста");
        } else {
            System.out.println("Таблица не пуста");
        }

        System.out.println("---------------------------------");

        if (myHashTable.contains("0")) {
            System.out.println("Таблица содержит объект");
        } else {
            System.out.println("Таблица не содержит объект");
        }

        System.out.println("---------------------------------");

        System.out.println(Arrays.toString(myHashTable.toArray()));
        System.out.println("---------------------------------");

        if (myHashTable.remove("2")) {
            System.out.println("Элемент удален");
        } else {
            System.out.println("Элемент не удален");
        }

        System.out.println(myHashTable);
        System.out.println("---------------------------------");

        myHashTable.addAll(new ArrayList<>(Arrays.asList("22", "33", "44")));
        System.out.println(myHashTable);
        System.out.println("---------------------------------");

        myHashTable.removeAll(new ArrayList<>(Arrays.asList("4", "1")));
        System.out.println(myHashTable);
        System.out.println("---------------------------------");

        System.out.println(Arrays.toString(myHashTable.toArray(new String[8])));
        System.out.println("---------------------------------");

        myHashTable.retainAll(new ArrayList<>((Arrays.asList("22", "5", "44"))));
        System.out.println(myHashTable);
        System.out.println("---------------------------------");

        myHashTable.clear();
        System.out.println(myHashTable);
    }
}