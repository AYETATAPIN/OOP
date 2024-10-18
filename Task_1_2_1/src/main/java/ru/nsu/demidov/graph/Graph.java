package ru.nsu.demidov.graph;

import java.util.List;

public interface Graph<T> {
    void addVertex(T vertex);

    void removeVertex(T vertex) throws Exception;

    void addEdge(T from, T to) throws Exception;

    void removeEdge(T from, T to) throws Exception;

    List<T> adjacentVertices(T vertex);

    List<T> toposort();

    void readFile(String filePath) throws Exception;

    void print();
}

