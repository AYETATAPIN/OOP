package ru.nsu.demidov.graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdjacencyMatrix<T> implements Graph<T> {
    private Map<T, Integer> verticesIndex;
    private List<T> vertices;
    private boolean[][] adjacencyMatrix;

    public AdjacencyMatrix() {
        verticesIndex = new HashMap<>();
        vertices = new ArrayList<>();
    }

    @Override
    public void addVertex(T vertex) {
        if (verticesIndex.containsKey(vertex) == false) {
            verticesIndex.put(vertex, vertices.size());
            vertices.add(vertex);
            resizeMatrix();
        }
    }

    @Override
    public void removeVertex(T vertex) throws IllegalArgumentException {
        if (verticesIndex.containsKey(vertex) == true) {
            int index = verticesIndex.get(vertex);
            verticesIndex.remove(vertex);
            vertices.remove(index);
            resizeMatrix();
        }
        else {
            throw new IllegalArgumentException("You stoopid no such vertex " + vertex);
        }
    }

    @Override
    public void addEdge(T from, T to) throws IllegalArgumentException {
        if (verticesIndex.containsKey(from) && verticesIndex.containsKey(to)) {
            int fromIndex = verticesIndex.get(from);
            int toIndex = verticesIndex.get(to);
            adjacencyMatrix[fromIndex][toIndex] = true;
        }
        else {
            throw new IllegalArgumentException("You stoopid no such vertices " + from + "and " + to);
        }
    }

    @Override
    public void removeEdge(T from, T to) throws IllegalArgumentException {
        if (verticesIndex.containsKey(from) && verticesIndex.containsKey(to)) {
            int fromIndex = verticesIndex.get(from);
            int toIndex = verticesIndex.get(to);
            if (adjacencyMatrix[fromIndex][toIndex] == false) {
                throw new IllegalArgumentException("You stoopid no such edge " + from + " " + to);
            }
            adjacencyMatrix[fromIndex][toIndex] = false;
        }
    }

    @Override
    public List<T> adjacentVertices(int verticeIndex) {
        T vertex = vertices.get(verticeIndex);
        List<T> neighbours = new ArrayList<>();
        if (verticesIndex.containsKey(vertex)) {
            int index = verticesIndex.get(vertex);
            for (int i = 0; i < vertices.size(); i++) {
                if (adjacencyMatrix[index][i]) {
                    neighbours.add(vertices.get(i));
                }
            }
        }
        return neighbours;
    }

    @Override
    public int verticesCount() {
        return vertices.size();
    }

    @Override
    public int getVertexId(T vertex) {
        return verticesIndex.get(vertex);
    }

    @Override
    public T getVertex(int verticeIndex) {
        return vertices.get(verticeIndex);
    }

    @Override
    public void readFile(String path) {
        try(BufferedReader input = new BufferedReader(new FileReader(path))) {
            String currentString;
            while ((currentString = input.readLine()) != null) {
                String[] args = currentString.split(" ");
                T from = (T) args[0];
                T to = (T) args[1];
                addVertex(from);
                addVertex(to);
                addEdge(from, to);
            }
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void print() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i));
            boolean foundAdjacent = false;
            for (int j = 0; j < vertices.size(); j++) {
                if (adjacencyMatrix[i][j] == true) {
                    if (foundAdjacent == false) {
                        System.out.print(" - ");
                        foundAdjacent = true;
                    }
                    else {
                        System.out.print(" - ");
                    }
                    System.out.print(vertices.get(j));
                }
            }
            System.out.println();
        }
    }

    private void resizeMatrix() {
        int size = vertices.size();
        boolean[][] temp = new boolean[size][size];
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                temp[i][j] = adjacencyMatrix[i][j];
            }
        }
        adjacencyMatrix = temp;
    }
}