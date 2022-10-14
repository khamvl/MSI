package ru.academits.kharitonov.shape;

public class Triangle implements Shape {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double maxX1X2 = Math.max(x1, x2);
        double maxWidth = Math.max(maxX1X2, x3);

        double minX1X2 = Math.min(x1, x2);
        double minWidth = Math.min(minX1X2, x3);

        return (maxWidth - minWidth);
    }

    @Override
    public double getHeight() {
        double maxY1Y2 = Math.max(y1, y2);
        double maxHeight = Math.max(maxY1Y2, y3);

        double minY1Y2 = Math.min(y1, y2);
        double minHeight = Math.min(minY1Y2, y3);

        return (maxHeight - minHeight);
    }

    @Override
    public double getArea() {
        return Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2;
    }

    @Override
    public double getPerimeter() {
        double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double c = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        return a + b + c;
    }

    @Override
    public String toString() {
        return "Ширина равна = " + getWidth() + "\nВысота равна = " + getHeight() +
                "\nПлощадь равна = " + getArea() + "\nПериметр равен = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2 &&
                x3 == triangle.x3 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1) + Double.hashCode(y1) + Double.hashCode(x2) +
                Double.hashCode(y2) + Double.hashCode(x3) + Double.hashCode(y3);

        return hash;
    }
}