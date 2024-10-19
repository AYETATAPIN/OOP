package ru.nsu.demidov.graph;

import java.util.List;

public interface Graph<T> {
    void addVertex(T vertex);

    void removeVertex(T vertex) throws IllegalArgumentException;

    void addEdge(T from, T to) throws IllegalArgumentException;

    void removeEdge(T from, T to) throws IllegalArgumentException;

    List<T> adjacentVertices(T vertex);

    default List<T> topologicalSort() {
        return toposort();
    }

    List<T> toposort();

    void readFile(String filePath) throws Exception;

    void print();
}

