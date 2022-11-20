package ru.academits.kharitonov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorCoordinates;

    public Vector(int vectorSize) {
        if (vectorSize > 0) {
            vectorCoordinates = new double[vectorSize];
        } else {
            throw new ArrayIndexOutOfBoundsException("vectorSize must be > 0");
        }
    }

    public Vector(Vector vector) {
        vectorCoordinates = vector.vectorCoordinates;
    }

    public Vector(double[] values) {
        if (values.length > 0) {
            vectorCoordinates = new double[values.length];

            for (int i = 0; i < vectorCoordinates.length; i++) {
                vectorCoordinates[i] = values[i];
            }
        } else {
            throw new IllegalArgumentException("Array length must be > 0");
        }
    }

    public Vector(int vectorSize, double[] values) {
        if (vectorSize > 0) {
            vectorCoordinates = new double[vectorSize];
            int i = 0;

            if (vectorSize >= values.length) {
                while (i <= values.length - 1) {
                    vectorCoordinates[i] = values[i];
                    i++;
                }
            } else {
                while (i <= vectorSize - 1) {
                    vectorCoordinates[i] = values[i];
                    i++;
                }
            }
        } else {
            throw new IllegalArgumentException("vectorSize must be > 0");
        }
    }

    public int getSize() {
        return vectorCoordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (double vectorCoordinate : vectorCoordinates) {
            stringBuilder.append(vectorCoordinate).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        int i = 0;

        if (vectorCoordinates.length < vector.vectorCoordinates.length) {
            double[] resultVector = new double[vector.vectorCoordinates.length];

            while (i < vectorCoordinates.length) {
                resultVector[i] = vectorCoordinates[i] + vector.vectorCoordinates[i];
                i++;
            }

            while (i < vector.vectorCoordinates.length) {
                resultVector[i] = vector.vectorCoordinates[i];
                i++;
            }

            vectorCoordinates = resultVector;
        } else {
            if (vectorCoordinates.length > vector.vectorCoordinates.length) {
                while (i < vector.vectorCoordinates.length) {
                    vectorCoordinates[i] += vector.vectorCoordinates[i];
                    i++;
                }
            } else {
                while (i < vectorCoordinates.length) {
                    vectorCoordinates[i] += vector.vectorCoordinates[i];
                    i++;
                }
            }
        }
    }

    public void subtract(Vector vector) {
        int i = 0;

        if (vectorCoordinates.length < vector.vectorCoordinates.length) {
            double[] resultVector = new double[vector.vectorCoordinates.length];

            while (i < vectorCoordinates.length) {
                resultVector[i] = vectorCoordinates[i] - vector.vectorCoordinates[i];
                i++;
            }

            while (i < vector.vectorCoordinates.length) {
                resultVector[i] = vector.vectorCoordinates[i];
                i++;
            }

            vectorCoordinates = resultVector;
        } else {
            if (vectorCoordinates.length > vector.vectorCoordinates.length) {
                while (i < vector.vectorCoordinates.length) {
                    vectorCoordinates[i] -= vector.vectorCoordinates[i];
                    i++;
                }
            } else {
                while (i < vectorCoordinates.length) {
                    vectorCoordinates[i] -= vector.vectorCoordinates[i];
                    i++;
                }
            }
        }
    }

    public void multiplyByScalar(double coefficient) {
        for (int i = 0; i < vectorCoordinates.length; i++) {
            vectorCoordinates[i] *= coefficient;
        }
    }

    public void expand() {
        multiplyByScalar(-1);
    }

    public double getVectorLength() {
        double maxValues = vectorCoordinates[vectorCoordinates.length - 1];
        double minValues = vectorCoordinates[0];

        for (double vectorCoordinate : vectorCoordinates) {
            if (maxValues < vectorCoordinate) {
                maxValues = vectorCoordinate;
            }

            if (minValues > vectorCoordinate) {
                minValues = vectorCoordinate;
            }
        }

        return maxValues - minValues;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= vectorCoordinates.length) {
            throw new IllegalArgumentException("You specified index = " + index + ". But index must be >= 0 and < " + vectorCoordinates.length);
        }

        return vectorCoordinates[index];
    }

    public void setComponent(int index, double values) {
        if (index < 0 || index >= vectorCoordinates.length) {
            throw new IllegalArgumentException("You specified index = " + index + ". But index must be >= 0 and < " + vectorCoordinates.length);
        }

        for (int i = 0; i < vectorCoordinates.length; i++) {
            if (i == index) {
                vectorCoordinates[i] = values;
                break;
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(vectorCoordinates);

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

        if (vectorCoordinates.length != vector.vectorCoordinates.length) {
            return false;
        }

        for (int i = 0; i < vectorCoordinates.length; i++) {
            if (vectorCoordinates[i] != vector.vectorCoordinates[i]) {
                return false;
            }
        }

        return Arrays.equals(vectorCoordinates, vector.vectorCoordinates);
    }

    public static Vector getAmount(Vector vector1, Vector vector2) {
        int i = 0;
        Vector vector;

        if (vector1.vectorCoordinates.length > vector2.vectorCoordinates.length) {
            vector = new Vector(vector1.vectorCoordinates.length);

            while (i < vector2.vectorCoordinates.length) {
                vector.vectorCoordinates[i] = vector1.vectorCoordinates[i] + vector2.vectorCoordinates[i];
                i++;
            }

            while (i < vector1.vectorCoordinates.length) {
                vector.vectorCoordinates[i] = vector1.vectorCoordinates[i];
                i++;
            }

            return vector;
        }

        vector = new Vector(vector2.vectorCoordinates.length);

        while (i < vector1.vectorCoordinates.length) {
            vector.vectorCoordinates[i] = vector1.vectorCoordinates[i] + vector2.vectorCoordinates[i];
            i++;
        }

        while (i < vector2.vectorCoordinates.length) {
            vector.vectorCoordinates[i] = vector2.vectorCoordinates[i];
            i++;
        }

        return vector;
    }


    public static Vector getDifference(Vector vector1, Vector vector2) {
        int i = 0;
        Vector vector;

        if (vector1.vectorCoordinates.length > vector2.vectorCoordinates.length) {
            vector = new Vector(vector1.vectorCoordinates.length);

            while (i < vector2.vectorCoordinates.length) {
                vector.vectorCoordinates[i] = vector1.vectorCoordinates[i] - vector2.vectorCoordinates[i];
                i++;
            }

            while (i < vector1.vectorCoordinates.length) {
                vector.vectorCoordinates[i] = vector1.vectorCoordinates[i];
                i++;
            }

            return vector;
        }

        vector = new Vector(vector2.vectorCoordinates.length);

        while (i < vector1.vectorCoordinates.length) {
            vector.vectorCoordinates[i] = vector1.vectorCoordinates[i] - vector2.vectorCoordinates[i];
            i++;
        }

        while (i < vector2.vectorCoordinates.length) {
            vector.vectorCoordinates[i] = vector2.vectorCoordinates[i];
            i++;
        }

        return vector;
    }

    public static Vector getDotProduct(Vector vector1, Vector vector2) {
        int i = 0;
        Vector vector;

        if (vector1.vectorCoordinates.length > vector2.vectorCoordinates.length) {
            vector = new Vector(vector1.vectorCoordinates.length);

            while (i < vector2.vectorCoordinates.length) {
                vector.vectorCoordinates[i] = vector1.vectorCoordinates[i] * vector2.vectorCoordinates[i];
                i++;
            }

            while (i < vector1.vectorCoordinates.length) {
                vector.vectorCoordinates[i] = vector1.vectorCoordinates[i];
                i++;
            }

            return vector;
        }

        vector = new Vector(vector2.vectorCoordinates.length);

        while (i < vector1.vectorCoordinates.length) {
            vector.vectorCoordinates[i] = vector1.vectorCoordinates[i] * vector2.vectorCoordinates[i];
            i++;
        }

        while (i < vector2.vectorCoordinates.length) {
            vector.vectorCoordinates[i] = vector2.vectorCoordinates[i];
            i++;
        }

        return vector;
    }
}