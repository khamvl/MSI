package ru.academits.kharitonov.vector;

import java.util.Arrays;

public class Vector {
    double[] array;
    int n;
    Vector vector;
    double[] values;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n (vector dimension) must be > 0");
        }

        this.n = 0;
    }

    public Vector(Vector vector) {
        this.vector = vector;
    }

    public Vector(double[] values) {
        this.values = values;
    }


    public Vector(int n, double[] values) {
        if (n < 0) {
            throw new NegativeArraySizeException("n must be >= 0");
        }

        array = new double[n];

        for (int i = 0; i < n; i++) {
            try {
                this.array[i] = values[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                if (values.length < n) {
                    array[i] = 0;
                }
            }
        }
    }

    public int getSize() {
        return array.length;
    }

    @Override
    public String toString() {
        int i = 0;
        StringBuilder arrayString = new StringBuilder();

        while (i < array.length) {
            if (i == 0) {
                arrayString = new StringBuilder(String.valueOf(array[i]));
            } else {
                arrayString.append(", ").append(array[i]);
            }

            i++;
        }

        return "{" + arrayString + "}";
    }

    public double[] getVectorAddition(Vector nValues2) {
        int maxLength = Math.max(array.length, nValues2.array.length);
        double[] resultVector = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            try {
                resultVector[i] = array[i] + nValues2.array[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                if (array.length < nValues2.array.length) {
                    resultVector[i] = nValues2.array[i];
                } else {
                    resultVector[i] = array[i];
                }
            }
        }

        array = resultVector;

        return array;
    }

    public double[] getVectorSubtraction(Vector nValues2) {
        int maxLength = Math.max(array.length, nValues2.array.length);
        double[] resultVector = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            try {
                resultVector[i] = array[i] - nValues2.array[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                if (array.length < nValues2.array.length) {
                    resultVector[i] = -nValues2.array[i];
                } else {
                    resultVector[i] = -array[i];
                }
            }
        }
        array = resultVector;

        return array;
    }

    public double[] getMultiplicationOfVectorByScalar(double coefficient) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * coefficient;
        }

        return array;
    }

    public double[] getVectorReversal() {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * -1;
        }

        return array;
    }

    public double getVectorComponent(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("index must be => 0");
        }

        if (index > array.length - 1) {
            throw new ArrayIndexOutOfBoundsException("index must be < arrayLength");
        } else {
            return array[index];
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(array);
        hash = prime * hash + n;
        hash = prime * hash + (vector != null ? vector.hashCode() : 0);
        hash = prime * hash + Arrays.hashCode(values);

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

        int maxLength = Math.max(array.length, vector.array.length);
        boolean areEqual = true;

        for (int i = 0; i < maxLength; i++) {
            try {
                if (array[i] != vector.array[i]) {
                    areEqual = false;
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }

        }

        return areEqual && n == vector.n;
    }

    public static double[] getVectorAddition(Vector nValues1, Vector nValues2) {
        int maxLength = Math.max(nValues1.array.length, nValues2.array.length);
        double[] resultVector = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            try {
                resultVector[i] = nValues1.array[i] + nValues2.array[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                if (nValues1.array.length < nValues2.array.length) {
                    resultVector[i] = nValues2.array[i];
                } else {
                    resultVector[i] = nValues1.array[i];
                }
            }
        }

        return resultVector;
    }

    public static double[] getVectorSubtraction(Vector nValues1, Vector nValues2) {
        int maxLength = Math.max(nValues1.array.length, nValues2.array.length);
        double[] resultVector = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            try {
                resultVector[i] = nValues1.array[i] - nValues2.array[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                if (nValues1.array.length < nValues2.array.length) {
                    resultVector[i] = -nValues2.array[i];
                } else {
                    resultVector[i] = -nValues1.array[i];
                }
            }
        }

        return resultVector;
    }

    public static double getDotVectorsProduct(Vector nValues1, Vector nValues2) {
        int maxLength = Math.max(nValues1.array.length, nValues2.array.length);
        double resultVector = 0;

        for (int i = 0; i < maxLength; i++) {
            try {
                resultVector += nValues1.array[i] * nValues2.array[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                resultVector += 0;
            }
        }

        return resultVector;
    }
}