package ru.academits.kharitonov.shapes_main;

import ru.academits.kharitonov.shapes.comparators.AreaComparator;
import ru.academits.kharitonov.shapes.comparators.PerimeterComparator;
import ru.academits.kharitonov.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(999),
                new Square(1750),
                new Triangle(3, 8, 5, 1, 9548, 5),
                new Triangle(5, 1, 2, 7, 10, 6),
                new Rectangle(3, 9),
                new Rectangle(16, 5),
                new Circle(999),
                new Circle(15),
                new Circle(5)
        };

        Arrays.sort(shapes, new AreaComparator());

        System.out.println("Параметры фигуры с наибольшей площадью:");
        System.out.println(shapes[shapes.length - 1]);

        Arrays.sort(shapes, new PerimeterComparator());

        System.out.println("Параметры фигуры со вторым по величине периметром:");
        System.out.println(shapes[shapes.length - 2]);
    }
}