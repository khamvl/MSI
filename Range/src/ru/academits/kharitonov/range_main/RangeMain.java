package ru.academits.kharitonov.range_main;

import ru.academits.kharitonov.range.Range;

import java.util.Arrays;

public class RangeMain {
    public static void main(String[] args) {
        Range range = new Range(4, 8);

        double from = range.getFrom();
        double to = range.getTo();
        boolean isNumberInRange = range.isInside(565.45);

        System.out.println("Наименьшее число в диапазоне: " + from);
        System.out.println("Наибольшее число в диапазоне: " + to);
        System.out.println("Длина диапазана = " + range.getLength());

        if (isNumberInRange) {
            System.out.println("Число входит в диапазон");
        } else {
            System.out.println("Число не входит в диапазон");
        }

        System.out.println("---------------------------------");

        range.setFrom(555.213);
        range.setTo(999.6453);

        from = range.getFrom();
        to = range.getTo();
        isNumberInRange = range.isInside(9564.45);

        System.out.println("Наименьшее число в диапазоне: " + from);
        System.out.println("Наибольшее число в диапазоне: " + to);
        System.out.println("Длина диапазона = " + range.getLength());

        if (isNumberInRange) {
            System.out.println("Число входит в диапазон");
        } else {
            System.out.println("Число не входит в диапазон");
        }

        System.out.println("---------------------------------");

        range.setFrom(1);
        range.setTo(7);

        Range range1 = new Range(3, 5);

        Range intersectionRange = range.getIntersection(range1);

        if (intersectionRange == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println("Диапазон пересечения : " + intersectionRange);
        }

        System.out.println("---------------------------------");

        System.out.println("Диапазон объединения двух интервалов : " + Arrays.toString(range.getUnion(range1)));

        System.out.println("---------------------------------");

        System.out.println("Разность двух интервалов : " + Arrays.toString(range.getDifference(range1)));
    }
}