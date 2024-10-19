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
    public List<T> adjacentVertices(T vertex) {
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

    @Override
    public List<T> toposort() {
        List<T> sorted = new ArrayList<>();
        Set<T> isVisited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        for (int i = 0; i < vertices.size(); ++i) {
            if (isVisited.contains(vertices.get(i)) == false) {
                traverse(vertices.get(i), isVisited, stack);
            }
        }
        while (stack.isEmpty() == false) {
            sorted.add(stack.pop());
        }
        return sorted;
    }

    private void traverse(T vertex, Set<T> isVisited, Stack<T> stack) {
        isVisited.add(vertex);
        int index = verticesIndex.get(vertex);
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencyMatrix[index][i]) {
                T neighbor = vertices.get(i);
                if (isVisited.contains(neighbor) == false) {
                    traverse(neighbor, isVisited, stack);
                }
            }
        }
        stack.push(vertex);
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