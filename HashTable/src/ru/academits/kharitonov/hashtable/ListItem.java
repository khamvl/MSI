package ru.academits.kharitonov.hashtable;

public class ListItem<T> {
    private T value;

    public ListItem(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }


}
