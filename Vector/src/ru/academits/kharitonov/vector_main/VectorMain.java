package ru.academits.kharitonov.vector_main;

import ru.academits.kharitonov.vector.Vector;

import java.util.Arrays;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {1.2, 3.2, 1.1, 5.2, 1.7};
        Vector nValues1 = new Vector(5, array1);
        System.out.println("Вектор 1: " + nValues1);

        double[] array2 = {1.2, 3.2, 1.1, 10.2, 1.7};
        Vector nValues2 = new Vector(4, array2);
        System.out.println("Вектор 2: " + nValues2);

        System.out.println("-----------------------------------");

        System.out.println("Статические:");
        System.out.println("Сложение векторов: " + Arrays.toString(Vector.getVectorAddition(nValues1, nValues2)));
        System.out.println("Вычитание векторов: " + Arrays.toString(Vector.getVectorSubtraction(nValues1, nValues2)));
        System.out.println("Скалярное произведение векторов: " + Vector.getDotVectorsProduct(nValues1, nValues2));

        System.out.println("------------------------------------");

        System.out.println("Нестатические:");
        System.out.println("Сумма векторов: " + Arrays.toString(nValues1.getVectorAddition(nValues2)));
        System.out.println("Разность векторов: " + Arrays.toString(nValues1.getVectorSubtraction(nValues2)));
        System.out.println("Умножение векторов: " + Arrays.toString(nValues1.getMultiplicationOfVectorByScalar(3)));
        System.out.println("Разворот вектора: " + Arrays.toString(nValues1.getVectorReversal()));
        System.out.println("Длина вектора: " + nValues2.getSize());
        System.out.println("Компонента вектора по индексу: " + nValues1.getVectorComponent(2));
    }
}
