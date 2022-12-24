package ru.academits.kharitonov.lambda;

public record Person(String name, int age) {
    public Person {
        if (age <= 0) {
            throw new IllegalArgumentException("Age = " + age + ". But age must be > 0");
        }
    }
}