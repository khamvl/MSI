package ru.academits.kharitonov.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static ArrayList<String> getFileLinesList(String filePath) throws IOException {
        ArrayList<String> fileLinesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                fileLinesList.add(line);
            }
        }

        return fileLinesList;
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> fileLinesList = getFileLinesList("arrayListHome.txt");
            System.out.println("Список строк файла: " + fileLinesList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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

        ArrayList<Integer> integersListWithoutRepetition = new ArrayList<>(integersListWithRepetition.size());

        for (Integer number : integersListWithRepetition) {
            if (!integersListWithoutRepetition.contains(number)) {
                integersListWithoutRepetition.add(number);
            }
        }

        System.out.println("Список целых чисел после удаления повторений: " + integersListWithoutRepetition);
    }
}