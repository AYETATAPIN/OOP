package ru.nsu.demidov.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adjacency list graph realisation.
 */

public class AdjacencyList<T> implements Graph<T> {
    private Map<T, Integer> verticesIndex;
    private List<T> vertices;
    private Map<T, List<T>> adjacencyList;

    /**
     * Adjacency list graph constructor.
     */

    public AdjacencyList() {
        adjacencyList = new HashMap<>();
        vertices = new ArrayList<>();
        verticesIndex = new HashMap<>();
    }

    /**
     * Adjacency list graph addVertex realisation.
     */

    @Override
    public void addVertex(T vertex) {
        if (adjacencyList.containsKey(vertex) == false) {
            adjacencyList.put(vertex, new ArrayList<>());
            verticesIndex.put(vertex, vertices.size());
            vertices.add(vertex);
        }
    }

    /**
     * Adjacency list graph removeVertex realisation.
     */

    @Override
    public void removeVertex(T vertex) throws IllegalArgumentException {
        if (adjacencyList.containsKey(vertex) == true) {
            adjacencyList.remove(vertex);
            int index = verticesIndex.get(vertex);
            verticesIndex.remove(vertex);
            vertices.remove(index);
            for (List<T> neighbors : adjacencyList.values()) {
                neighbors.remove(vertex);
            }
        } else {
            throw new IllegalArgumentException("You stoopid no such vertex " + vertex);
        }
    }

    /**
     * Adjacency list graph addEdge realisation.
     */

    @Override
    public void addEdge(T from, T to) throws IllegalArgumentException {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
        } else {
            throw new IllegalArgumentException("You stoopid no such vertices "
                + from + " and " + to);
        }
    }

    /**
     * Adjacency list graph removeEdge realisation.
     */

    @Override
    public void removeEdge(T from, T to) throws IllegalArgumentException {
        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).remove(to);
        } else {
            throw new IllegalArgumentException("You stoopid no such edge " + from + " and " + to);
        }
    }

    /**
     * Adjacency matrix graph adjacentVertices realisation.
     */

    @Override
    public List<T> adjacentVertices(int verticeIndex) {
        return adjacencyList.getOrDefault(vertices.get(verticeIndex), new ArrayList<>());
    }

    /**
     * Adjacency matrix graph verticesCount realisation.
     */

    @Override
    public int verticesCount() {
        return adjacencyList.size();
    }

    /**
     * Adjacency matrix graph getVertexId realisation.
     */

    @Override
    public int getVertexId(T vertex) {
        return verticesIndex.get(vertex);
    }

    /**
     * Adjacency matrix graph getVertex realisation.
     */

    @Override
    public T getVertex(int verticeIndex) {
        return vertices.get(verticeIndex);
    }

    /**
     * Adjacency matrix graph readFile realisation.
     */

    @Override
    public void readFile(String path) {
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            String currentString;
            while ((currentString = input.readLine()) != null) {
                String[] args = currentString.split(" ");
                T from = (T) args[0];
                T to = (T) args[1];
                addVertex(from);
                addVertex(to);
                addEdge(from, to);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
