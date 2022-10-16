package ru.academits.kharitonov.vector;

import java.util.Arrays;

public class Vector {
    double[] arrayCopy;
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

        arrayCopy = new double[n];

        for (int i = 0; i < n; i++) {
            try {
                this.arrayCopy[i] = values[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                if (values.length < n) {
                    arrayCopy[i] = 0;
                }
            }
        }
    }


    public String toString() {
        return "" + Arrays.toString(arrayCopy);
    }

}