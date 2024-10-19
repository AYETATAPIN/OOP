package ru.nsu.demidov.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    public void removeVertex(T vertex) throws IllegalArgumentException {
        if (adjacencyList.containsKey(vertex) == true) {
            adjacencyList.remove(vertex);
            for (List<T> neighbors : adjacencyList.values()) {
                neighbors.remove(vertex);
            }
        } else {
            throw new IllegalArgumentException("You stoopid no such vertex " + vertex);
        }
    }

    @Override
    public void addEdge(T from, T to) throws IllegalArgumentException {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
        } else {
            throw new IllegalArgumentException("You stoopid no such edge " + from + "and " + to);
        }
    }

    @Override
    public void removeEdge(T from, T to) throws IllegalArgumentException {
        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).remove(to);
        } else {
            throw new IllegalArgumentException("You stoopid no such edge " + from + "and " + to);
        }
    }

    @Override
    public List<T> adjacentVertices(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
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
        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " - ");
            for (T neighbour : entry.getValue()) {
                System.out.print(neighbour + " ");
            }
            System.out.println();
        }
    }

    public List<T> toposort() {
        List<T> sorted = new ArrayList<>();
        Set<T> isVisited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        for (T vertex : adjacencyList.keySet()) {
            if (isVisited.contains(vertex) == false) {
                traverse(vertex, isVisited, stack);
            }
        }
        while (stack.isEmpty() == false) {
            sorted.add(stack.pop());
        }

        return sorted;
    }

    private void traverse(T vertex, Set<T> isVisited, Stack<T> stack) {
        isVisited.add(vertex);
        for (int i = 0; i < adjacencyList.size(); ++i   ) {
            if (isVisited.contains(adjacencyList.get(vertex).get(i)) == false) {
                traverse(adjacencyList.get(vertex).get(i), isVisited, stack);
            }
        }
        stack.push(vertex);
    }
}
