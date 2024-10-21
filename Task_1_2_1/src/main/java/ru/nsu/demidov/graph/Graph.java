package ru.nsu.demidov.graph;

import java.util.List;

/**
 * Graph interface.
 */

public interface Graph<T> {
    void addVertex(T vertex);

    void removeVertex(T vertex) throws IllegalArgumentException;

    void addEdge(T from, T to) throws IllegalArgumentException;

    void removeEdge(T from, T to) throws IllegalArgumentException;

    List<T> adjacentVertices(int index);

    int verticesCount();

    void readFile(String filePath) throws Exception;

    T getVertex(int pop);

    int getVertexId(T vertex);
}
