package ru.academits.kharitonov.range;

import java.util.Arrays;

public class RangeStar {
    private final double from1;
    private final double to1;

    private final double from2;
    private final double to2;

    public RangeStar(double from1, double from2, double to1, double to2) {
        this.from1 = from1;
        this.from2 = from2;
        this.to1 = to1;
        this.to2 = to2;
    }

    public String getTwoIntervalsIntersection() {
        if (from1 < to2 && to1 > from2) {
            double commonSegmentStartPoint = Math.max(from1, from2);
            double commonSegmentEndPoint = Math.min(to1, to2);

            return commonSegmentStartPoint + " ; " + commonSegmentEndPoint;
        }

        return null;
    }

    public String getTwoIntervalsUnion() {
        if (from1 <= to2 && to1 >= from2) {
            double commonSegmentStartPoint = Math.min(from1, from2);
            double commonSegmentEndPoint = Math.max(to1, to2);

            return "[" + commonSegmentStartPoint + " ; " + commonSegmentEndPoint + "]";
        }

        double[][] array;

        if (from1 < from2) {
            array = new double[][]{{from1, to1}, {from2, to2}};
        } else {
            array = new double[][]{{from2, to2}, {from1, to1}};
        }

        return Arrays.deepToString(array);
    }

    public String getTwoIntervalsDifference() {
        if (from1 < to2 && to1 > from2) {
            if ((from1 == from2 && to1 != to2)) {
                double commonSegmentStartPoint = Math.min(to1, to2);
                double commonSegmentEndPoint = Math.max(to1, to2);

                return "[" + commonSegmentStartPoint + " ; " + commonSegmentEndPoint + "]";
            }

            if ((to1 == to2 && from1 != from2)) {
                double commonSegmentStartPoint = Math.min(from1, from2);
                double commonSegmentEndPoint = Math.max(from1, from2);

                return "[" + commonSegmentStartPoint + " ; " + commonSegmentEndPoint + "]";
            }

            double[][] array;

            if (from1 < from2 && to2 < to1) {
                array = new double[][]{{from1, from2}, {to2, to1}};
            } else if (from1 > from2 && to1 < to2) {
                array = new double[][]{{from2, from1}, {to1, to2}};
            } else if (from2 < from1) {
                array = new double[][]{{from2, from1}, {to2, to1}};
            } else {
                array = new double[][]{{from1, from2}, {to1, to2}};
            }

            return Arrays.deepToString(array);
        }

        return null;
    }
}