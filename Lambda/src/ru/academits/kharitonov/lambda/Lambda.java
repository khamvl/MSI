package ru.academits.kharitonov.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Lambda {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Пётр", 65),
                new Person("Максим", 22),
                new Person("Георгий", 45),
                new Person("Даниил", 2),
                new Person("Владимир", 35),
                new Person("Максим", 7),
                new Person("Владимир", 9)
        );

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();

        System.out.println("A) " + uniqueNames);
        System.out.println("-------------------------------------------------");

        String uniqueNamesInFormat = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("Б) " + uniqueNamesInFormat);
        System.out.println("-------------------------------------------------");

        List<String> peopleNamesUnder18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .map(Person::getName)
                .toList();

        OptionalDouble peopleAverageAgeUnder18 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .mapToInt(Person::getAge)
                .average();

        System.out.println("В) " + peopleNamesUnder18);
        System.out.println("Средний возраст: " + peopleAverageAgeUnder18.getAsDouble());
        System.out.println("-------------------------------------------------");

        Map<String, Double> averageAgesByName = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println("Г) " + averageAgesByName);
        System.out.println("-------------------------------------------------");

        List<String> from20To45YearsOldPeopleList = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(Person::getName)
                .toList();

        System.out.println("Д) " + from20To45YearsOldPeopleList);
        System.out.println("-------------------------------------------------");

        System.out.print("Введите сколько элементов необходимо вычислить: ");
        Scanner scanner = new Scanner(System.in);
        int elementsCount = scanner.nextInt();

        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(elementsCount);

        squareRoots.forEach(System.out::println);
    }
}