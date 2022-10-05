package ru.academits.kharitonov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public double getIntersection(double from2, double to2) {
        if (from < to2 && to > from2) {
            return Math.min(to, to2) - Math.max(from, from2);
        }

        return 0;
    }

    public double[][] getUnion(double from2, double to2) {
        if (from <= to2 && to >= from2) {
            return new double[][]{{Math.min(from, from2)}, {Math.max(to, to2)}};
        }

        if (from < from2) {
            return new double[][]{{from, to}, {from2, to2}};
        }

        return new double[][]{{from2, to2}, {from, to}};
    }

    public double[][] getDifference(double from2, double to2) {
        if (from < to2 && to > from2) {
            if (from <= from2 && to2 > to) {
                if (from == from2) {
                    return new double[][]{{to}, {to2}};
                }

                return new double[][]{{from, from2}, {to, to2}};
            }

            if (from > from2 && to2 > to) {
                return new double[][]{{from2, from}, {to, to2}};
            }

            if (from <= from2 && to2 < to) {
                if (from == from2) {
                    return new double[][]{{to2}, {to}};
                }

                return new double[][]{{from, from2}, {to2, to}};
            }

            if (from > from2 && to2 < to) {
                return new double[][]{{from2, from}, {to2, to}};
            }

            if (from < from2) {
                return new double[][]{{from}, {from2}};
            }

            if (from > from2) {
                return new double[][]{{from2}, {from}};
            }
        }

        return new double[][]{{0}, {0}};
    }
}