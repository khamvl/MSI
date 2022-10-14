package ru.academits.kharitonov.shape;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}