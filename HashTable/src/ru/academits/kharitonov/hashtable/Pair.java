package ru.academits.kharitonov.hashtable;

public class Pair<T> {
    private int hashCode;
    private T t;

    public Pair(int hashCode, T t) {
        this.hashCode = hashCode;
        this.t = t;
    }

    public int getHashCode() {
        return hashCode;
    }

    public T getT() {
        return t;
    }

    @Override
    public String toString() {
        return t + "";
    }
}