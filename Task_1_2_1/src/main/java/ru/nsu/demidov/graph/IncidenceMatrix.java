package ru.nsu.demidov.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class IncidenceMatrix<T> implements Graph<T> {
    private Map<T, Integer> verticesIndex;
    private List<T> vertices;
    private List<List<Integer>> incidenceMatrix;

    public IncidenceMatrix() {
        verticesIndex = new HashMap<>();
        vertices = new ArrayList<>();
        incidenceMatrix = new ArrayList<>();
    }

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

    @Override
    public void removeVertex(T vertex) throws Exception {
        if (verticesIndex.containsKey(vertex) == true) {
            int index = verticesIndex.get(vertex);
            verticesIndex.remove(vertex);
            vertices.remove(index);
            for (int i = 0; i  < incidenceMatrix.size(); ++i) {
                incidenceMatrix.get(i).remove(index);
            }
        }
        else {
            throw new Exception("You stoopid no such vertex " + vertex);
        }
    }

    @Override
    public void addEdge(T from, T to) throws Exception {
        if (verticesIndex.containsKey(from) && verticesIndex.containsKey(to)) {
            int fromIndex = verticesIndex.get(from);
            int toIndex = verticesIndex.get(to);
            List<Integer> newRow = new ArrayList<>(Collections.nCopies(vertices.size(), 0));
            newRow.set(fromIndex, 1);
            newRow.set(toIndex, 1);
            incidenceMatrix.add(newRow);
        }
        else {
            throw new Exception("You stoopid no such edge " + from + "and " + to);
        }
    }

    @Override
    public void removeEdge(T from, T to) throws Exception {
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
        }
        else {
            throw new Exception("You stoopid no such edge " + from + "and " + to);
        }
    }

    @Override
    public List<T> adjacentVertices(T vertex) {
        List<T> neighbors = new ArrayList<>();
        if (verticesIndex.containsKey(vertex) == true) {
            int index = verticesIndex.get(vertex);
            for (List<Integer> row : incidenceMatrix) {
                if (row.get(index) == 1) {
                    for (int i = 0; i < vertices.size(); i++) {
                        if (row.get(i) == 1) {
                            neighbors.add(vertices.get(i));
                        }
                    }
                }
            }
        }
        return neighbors;
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
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i));
            boolean foundAdjacent = false;
            for (int j = 0; j < incidenceMatrix.size(); ++j) {
                if (incidenceMatrix.get(i).get(i) == 1) {
                    for (int k = 0; k < vertices.size(); k++) {
                        if (incidenceMatrix.get(i).get(k) == 1) {
                            if (foundAdjacent == false) {
                                System.out.print(" - ");
                                foundAdjacent = true;
                            }
                            else {
                                System.out.print(" - ");
                            }
                            System.out.print(vertices.get(k));
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public List<T> toposort() {
        List<T> sortedList = new ArrayList<>();
        Set<T> isVisited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        for (T vertex : vertices) {
            if (isVisited.contains(vertex) == false) {
                traverse(vertex, isVisited, stack);
            }
        }
        while (stack.isEmpty() == false) {
            sortedList.add(stack.pop());
        }
        return sortedList;
    }

    private void traverse(T vertex, Set<T> isVisited, Stack<T> stack) {
        isVisited.add(vertex);
        int index = verticesIndex.get(vertex);
        for (int i = 0; i < incidenceMatrix.size(); ++i) {
            if (incidenceMatrix.get(i).get(index) == 1) {
                for (int j = 0; j < vertices.size(); j++) {
                    if (incidenceMatrix.get(j).get(j) == 1) {
                        T neighbor = vertices.get(j);
                        if (!isVisited.contains(neighbor)) {
                            traverse(neighbor, isVisited, stack);
                        }
                    }
                }
            }
        }
        stack.push(vertex);
    }
}