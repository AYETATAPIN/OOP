package ru.nsu.demidov.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AdjacencyList<T> implements Graph<T> {
    private Map<T, List<T>> adjacencyList;

    public AdjacencyList() {
        adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(T vertex) {
        if (adjacencyList.containsKey(vertex) == false) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
    }

    @Override
    public void removeVertex(T vertex) throws Exception {
        if (adjacencyList.containsKey(vertex) == true) {
            adjacencyList.remove(vertex);
            for (List<T> neighbors : adjacencyList.values()) {
                neighbors.remove(vertex);
            }
        } else {
            throw new Exception("You stoopid no such vertex " + vertex);
        }
    }

    @Override
    public void addEdge(T from, T to) throws Exception {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
        } else {
            throw new Exception("You stoopid no such edge " + from + "and " + to);
        }
    }

    @Override
    public void removeEdge(T from, T to) throws Exception {
        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).remove(to);
        } else {
            throw new Exception("You stoopid no such edge " + from + "and " + to);
        }
    }

    @Override
    public List<T> adjacentVertices(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    @Override
    public void readFile(String path) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader(path));
        String line;
        while ((line = input.readLine()) != null) {
            String[] args = line.split(" ");
            T from = (T) args[0];
            T to = (T) args[1];
            addVertex(from);
            addVertex(to);
            addEdge(from, to);
        }
        input.close();
    }

    @Override
    public void print() {

    }

    @Override
    public List<T> toposort() {
        return null;
    }

    private void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack) {

    }
}
