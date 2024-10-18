package ru.nsu.demidov.graph;

public class Vertex<T> {
    T value;
    int index;

    Vertex(T value) {
        this.value = value;
    }

    T getValue() {
        return this.value;
    }

    int getIndex() {
        return index;
    }
}
