package ru.academits.kharitonov.vector_main;

import ru.academits.kharitonov.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vectorN = new Vector(2);

        Vector vectorCopy = new Vector(vectorN);

        double[] array = {1.2, 3.2, 5.1, 6.2};
        Vector valuesArray = new Vector(array);

        Vector nValues = new Vector(-2, array);
        System.out.println(nValues);
    }
}
