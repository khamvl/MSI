package ru.academits.kharitonov.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static ArrayList<Integer> getFileIntegersList(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<Integer> fileIntegersList = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                fileIntegersList.add(number);
            }

            return fileIntegersList;
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Изначальный список: " + getFileIntegersList("arrayListHome.txt"));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } finally {
            System.out.println("----------------------------------------------------------");

            ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 2, 6, 7, 8, 9, 10, 10, 9));
            System.out.println("Список целых чисел: " + integersList);

            for (int i = 0; i < integersList.size(); i++) {
                if (integersList.get(i) % 2 == 0) {
                    integersList.remove(i);
                    i--;
                }
            }

            System.out.println("Список целых чисел после удаления четных чисел: " + integersList);
            System.out.println("----------------------------------------------------------");

            ArrayList<Integer> integersListWithRepetition = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1));
            System.out.println("Список целых чисел с повторением: " + integersListWithRepetition);

            ArrayList<Integer> integersListWithoutRepetition = new ArrayList<>(5);

            for (Integer number : integersListWithRepetition) {
                if (integersListWithoutRepetition.contains(number)) {
                    continue;
                }

                integersListWithoutRepetition.add(number);
            }

            integersListWithoutRepetition.trimToSize();

            System.out.println("Список целых чисел после удаления повторений: " + integersListWithoutRepetition);
        }
    }
}