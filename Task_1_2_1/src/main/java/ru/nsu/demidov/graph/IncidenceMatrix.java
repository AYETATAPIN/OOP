package ru.nsu.demidov.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Incidence matrix graph realisation.
 */

public class IncidenceMatrix<T> implements Graph<T> {
    private Map<T, Integer> verticesIndex;
    private List<T> vertices;
    private List<List<Integer>> incidenceMatrix;

    /**
     * Incidence matrix graph constructor.
     */

    public IncidenceMatrix() {
        verticesIndex = new HashMap<>();
        vertices = new ArrayList<>();
        incidenceMatrix = new ArrayList<>();
    }

    /**
     * Incidence matrix graph addVertex realisation.
     */

    @Override
    public void addVertex(T vertex) {
        if (verticesIndex.containsKey(vertex) == false) {
            verticesIndex.put(vertex, vertices.size());
            vertices.add(vertex);
            for (int i = 0; i < incidenceMatrix.size(); ++i) {
                incidenceMatrix.get(i).add(0);
            }
        }
    }

    /**
     * Incidence matrix graph removeVertex realisation.
     */

    @Override
    public void removeVertex(T vertex) throws IllegalArgumentException {
        if (verticesIndex.containsKey(vertex) == true) {
            int index = verticesIndex.get(vertex);
            verticesIndex.remove(vertex);
            vertices.remove(index);
            for (int i = 0; i < incidenceMatrix.size(); ++i) {
                incidenceMatrix.get(i).remove(index);
            }
        } else {
            throw new IllegalArgumentException("You stoopid no such vertex " + vertex);
        }
    }

    /**
     * Incidence matrix graph addEdge realisation.
     */

    @Override
    public void addEdge(T from, T to) throws IllegalArgumentException {
        if (verticesIndex.containsKey(from) && verticesIndex.containsKey(to)) {
            int fromIndex = verticesIndex.get(from);
            int toIndex = verticesIndex.get(to);
            List<Integer> newRow = new ArrayList<>(Collections.nCopies(vertices.size(), 0));
            newRow.set(fromIndex, 1);
            newRow.set(toIndex, 1);
            incidenceMatrix.add(newRow);
        } else {
            throw new IllegalArgumentException("You stoopid no such vertices "
                + from + " and " + to);
        }
    }

    /**
     * Incidence matrix graph removeEdge realisation.
     */

    @Override
    public void removeEdge(T from, T to) throws IllegalArgumentException {
        if (verticesIndex.containsKey(from) && verticesIndex.containsKey(to)) {
            int fromIndex = verticesIndex.get(from);
            int toIndex = verticesIndex.get(to);
            for (int i = 0; i < incidenceMatrix.size(); i++) {
                List<Integer> row = incidenceMatrix.get(i);
                if (row.get(fromIndex) == 1 && row.get(toIndex) == 1) {
                    incidenceMatrix.remove(i);
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("You stoopid no such edge " + from + " and " + to);
        }
    }

    /**
     * Incidence matrix graph adjacentVertices realisation.
     */

    @Override
    public List<T> adjacentVertices(int verticeIndex) {
        T vertex = vertices.get(verticeIndex);
        List<T> neighbors = new ArrayList<>();
        if (verticesIndex.containsKey(vertex) == true) {
            int index = verticesIndex.get(vertex);
            for (List<Integer> row : incidenceMatrix) {
                if (row.get(index) == 1) {
                    for (int i = 0; i < vertices.size(); i++) {
                        if (row.get(i) == 1 && neighbors.contains(vertices.get(i)) == false
                            && i != verticeIndex) {
                            neighbors.add(vertices.get(i));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    /**
     * Incidence matrix graph verticesCount realisation.
     */

    @Override
    public int verticesCount() {
        return vertices.size();
    }

    /**
     * Incidence matrix graph getVertexId realisation.
     */

    @Override
    public int getVertexId(T vertex) {
        return verticesIndex.get(vertex);
    }

    /**
     * Incidence matrix graph getVertex realisation.
     */

    @Override
    public T getVertex(int verticeIndex) {
        return vertices.get(verticeIndex);
    }

    /**
     * Incidence matrix graph readFile realisation.
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