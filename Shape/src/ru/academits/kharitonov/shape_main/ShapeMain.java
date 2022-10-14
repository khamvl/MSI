package ru.academits.kharitonov.shape_main;

import ru.academits.kharitonov.shape.*;

import java.util.Arrays;

public class ShapeMain {
    public static void main(String[] args) {
        Shape square1 = new Square(5);
        Shape square2 = new Square(6);

        Shape triangle1 = new Triangle(3, 8, 5, 1, 10, 5);
        Shape triangle2 = new Triangle(5, 1, 2, 7, 10, 6);

        Shape rectangle1 = new Rectangle(3, 9);
        Shape rectangle2 = new Rectangle(16, 5);

        Shape circle1 = new Circle(5);
        Shape circle2 = new Circle(15);
        Shape circle3 = new Circle(5);

        Shape[] shape = new Shape[9];
        shape[0] = square1;
        shape[1] = square2;
        shape[2] = triangle1;
        shape[3] = triangle2;
        shape[4] = rectangle1;
        shape[5] = rectangle2;
        shape[6] = circle1;
        shape[7] = circle2;
        shape[8] = circle3;

        Arrays.sort(shape, new SortByArea());
        Arrays.sort(shape, new SortByPerimeter());

        int i = 0;

        for (Shape ignored : shape) {
              i++;
        }

        System.out.println("Параметры фигуры с наибольшой площадью:");
        System.out.println("Ширина = " + shape[i - 1].getWidth());
        System.out.println("Высота = " + shape[i - 1].getHeight());
        System.out.println("Площадь = " + shape[i - 1].getArea());
        System.out.println("Периметр = " + shape[i - 1].getPerimeter());

        System.out.println("--------------------------------");

        System.out.println("Параметры фигуры со вторым по величени периметром:");
        System.out.println("Ширина = " + shape[i - 2].getWidth());
        System.out.println("Высота = " + shape[i - 2].getHeight());
        System.out.println("Площадь = " + shape[i - 2].getArea());
        System.out.println("Периметр = " + shape[i - 2].getPerimeter());
    }
}