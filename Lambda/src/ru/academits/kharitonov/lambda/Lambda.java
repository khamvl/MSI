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
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println("A) " + uniqueNames);
        System.out.println("-------------------------------------------------");

        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("Б) " + uniqueNamesString);
        System.out.println("-------------------------------------------------");

        List<String> peopleNamesUnder18 = persons.stream()
                .filter(x -> x.age() < 18)
                .map(Person::name)
                .toList();

        OptionalDouble peopleAverageAgeUnder18 = persons.stream()
                .filter(x -> x.age() < 18)
                .mapToInt(Person::age)
                .average();

        System.out.println("В) " + peopleNamesUnder18);

        if (peopleAverageAgeUnder18.isPresent()) {
            System.out.println("Средний возраст: " + peopleAverageAgeUnder18.getAsDouble());
        }

        System.out.println("-------------------------------------------------");

        Map<String, Double> averageAgesByName = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        System.out.println("Г) " + averageAgesByName);
        System.out.println("-------------------------------------------------");

        List<String> from20To45YearsOldPeopleList = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .map(Person::name)
                .toList();

        System.out.println("Д) " + from20To45YearsOldPeopleList);
        System.out.println("-------------------------------------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сколько элементов необходимо вычислить: ");
        int elementsCount = scanner.nextInt();

        DoubleStream squareRoots = DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(elementsCount);

        squareRoots.forEach(System.out::println);
    }
}