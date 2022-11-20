package ru.academits.kharitonov.vector_main;

import ru.academits.kharitonov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = new double[]{22, 3, 55, 0, 77, 0};
        Vector vector1 = new Vector(10, array1);
        System.out.println("Вектор 1: " + vector1);

        double[] array2 = new double[]{25, 3, 4, 0, 7, 9};
        Vector vector2 = new Vector(array2);
        System.out.println("Вектор 2: " + vector2);

        Vector vector3 = Vector.getAmount(vector1, vector2);
        Vector vector4 = Vector.getDifference(vector1, vector2);
        Vector vector5 = Vector.getDotProduct(vector1, vector2);

        System.out.println("------------------------------------------");
        System.out.println("Статические методы:");
        System.out.println("Сумма векторов: " + vector3);
        System.out.println("Разность векторов: " + vector4);
        System.out.println("Скалярное произведение векторов: " + vector5);

        System.out.println("------------------------------------------");
        System.out.println("Нестатические методы:");

        vector2.add(vector1);
        System.out.println("Вектор после прибавления к нему другого вектора: " + vector2);

        vector2.subtract(vector1);
        System.out.println("Вектор после вычитания из него другого вектора: " + vector2);

        vector2.multiplyByScalar(5);
        System.out.println("Вектор после умножения его на скаляр: " + vector2);

        vector2.expand();
        System.out.println("Вектор после разворота: " + vector2);

        System.out.println("Компонента вектора под индексом: " + vector2.getComponent(2));

        vector2.setComponent(3, -6);
        System.out.println("Вектор после замены значения под индексом: " + vector2);

        System.out.println("Длина вектора: " + vector2.getVectorLength());

        if (vector1.equals(vector2)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }
    }
}