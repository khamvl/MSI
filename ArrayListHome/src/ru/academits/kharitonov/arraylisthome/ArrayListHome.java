package ru.academits.kharitonov.arraylisthome;

import java.io.*;
import java.util.ArrayList;

public class ArrayListHome {
    public static ArrayList<Integer> getFileLinesList(String filePath) throws IOException {
        ArrayList<Integer> fileLinesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentString;

            while ((currentString = reader.readLine()) != null) {
                int number = Integer.parseInt(currentString);
                fileLinesList.add(number);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found. Check the specified path and the existence of the file.");
        }

        return fileLinesList;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Изначальный список: " + getFileLinesList("arrayListHome.txt"));

        System.out.println("----------------------------------------------------------");

        ArrayList<Integer> integersList = new ArrayList<>();
        int i = 1;

        while (i <= 10) {
            integersList.add(i);
            i++;
        }

        System.out.println("Список целых чисел: " + integersList);

        for (i = 0; i < integersList.size(); i++) {
            if (integersList.get(i) % 2 == 0) {
                integersList.remove(i);
            }
        }

        System.out.println("Список целых чисел после удаления четных чисел: " + integersList);
        System.out.println("----------------------------------------------------------");

        integersList.add(1);
        integersList.add(3);
        integersList.add(10);

        System.out.println("Список целых чисел: " + integersList);

        ArrayList<Integer> integersListWithoutRepetition = new ArrayList<>();

        for (i = 0; i < integersList.size(); i++) {
            int elementIndexFirstOccurrence = integersList.indexOf(integersList.get(i));

            if (elementIndexFirstOccurrence == i) {
                integersListWithoutRepetition.add(integersList.get(elementIndexFirstOccurrence));
            }
        }

        System.out.println("Список целых чисел после удаления повторений: " + integersListWithoutRepetition);
    }
}