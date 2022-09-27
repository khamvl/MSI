package ru.academits.kharitonov.range_main;

import ru.academits.kharitonov.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range = new Range(132.123, 14124.213);

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
    }
}