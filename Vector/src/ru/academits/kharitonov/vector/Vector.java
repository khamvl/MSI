package ru.academits.kharitonov.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    private void checkSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("vectorSize = " + size + ". But must be > 0");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= coordinates.length) {
            throw new IndexOutOfBoundsException("Index = " + index + ". But index must be >= 0 and < " + coordinates.length);
        }
    }

    public Vector(int size) {
        checkSize(size);

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] values) {
        checkSize(values.length);

        coordinates = Arrays.copyOf(values, values.length);
    }

    public Vector(int size, double[] values) {
        checkSize(size);

        coordinates = Arrays.copyOf(values, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double coordinate : coordinates) {
            stringBuilder.append(coordinate).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtract(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; i++) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double coefficient) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] *= coefficient;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double vectorCoordinate : coordinates) {
            sum += vectorCoordinate * vectorCoordinate;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        checkIndex(index);

        return coordinates[index];
    }

    public void setComponent(int index, double value) {
        checkIndex(index);

        coordinates[index] = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(coordinates);

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(coordinates, vector.coordinates);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        double result = 0;

        int minSize = Math.min(vector1.coordinates.length, vector2.coordinates.length);

        for (int i = 0; i < minSize; i++) {
            result += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return result;
    }
}