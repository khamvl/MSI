package ru.academits.kharitonov.vector_main;

import ru.academits.kharitonov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {22, 3, 55, 0, 1, 0};
        Vector vector1 = new Vector(6, array1);
        System.out.println("Вектор 1: " + vector1);

        double[] array2 = {22, 3, 55, 0, 77, 0};
        Vector vector2 = new Vector(array2);
        System.out.println("Вектор 2: " + vector2);

        System.out.println("------------------------------------------");
        System.out.println("Статические методы:");
        System.out.println("Сумма векторов: " + Vector.getSum(vector1, vector2));
        System.out.println("Разность векторов: " + Vector.getDifference(vector1, vector2));
        System.out.println("Скалярное произведение векторов: " + Vector.getDotProduct(vector1, vector2));

        System.out.println("------------------------------------------");
        System.out.println("Нестатические методы:");

        vector1.add(vector2);
        System.out.println("Вектор после прибавления к нему другого вектора: " + vector1);

        vector1.subtract(vector2);
        System.out.println("Вектор после вычитания из него другого вектора: " + vector1);

        vector2.multiplyByScalar(5);
        System.out.println("Вектор после умножения его на скаляр: " + vector2);

        vector2.reverse();
        System.out.println("Вектор после разворота: " + vector2);

        System.out.println("Компонента вектора под индексом: " + vector2.getComponent(2));

        vector2.setComponent(2, 999);
        System.out.println("Вектор после замены значения под индексом: " + vector2);

        System.out.println("Длина вектора: " + vector2.getLength());

        if (vector1.equals(vector2)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }
    }
}