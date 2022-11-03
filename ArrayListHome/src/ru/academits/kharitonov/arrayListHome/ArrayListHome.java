package ru.academits.kharitonov.arrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("arrayListHome.txt"))) {
            ArrayList<Integer> list1 = new ArrayList<>();

            while (scanner.hasNextLine()) {
                int number = scanner.nextInt();
                list1.add(number);
            }

            System.out.println("Изначальный список: " + list1);
            System.out.println("----------------------------------------------------------");

            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) % 2 == 0) {
                    list1.remove(i);
                    i--;
                }
            }

            System.out.println("После удаления четных чисел: " + list1);
            System.out.println("----------------------------------------------------------");

            ArrayList<Integer> list2 = new ArrayList<>(list1);

            for (int j = 0; j < list2.size(); j++) {
                int firstOccurrenceOfIndexElement = list2.indexOf(list2.get(j));

                if (firstOccurrenceOfIndexElement != j) {
                    list2.remove(j);
                    j--;
                }
            }

            System.out.println("Новый список после удаления повторений: " + list2);
        }
    }
}