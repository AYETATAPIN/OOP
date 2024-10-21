package ru.nsu.demidov.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Topological sort.
 */

public class TopSort<T> {

    /**
     * Toposort realisation.
     */

     public static <T> List<T> toposort(Graph<T> graph) {
        boolean[] isVisited;
        Deque<Integer> stack;
        int verticesCount = graph.verticesCount();
        isVisited = new boolean[verticesCount];
        stack = new ArrayDeque<>();
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i] == false) {
                dfs(i, graph, isVisited, stack);
            }
        }
        List<T> sorted = new ArrayList<>();
        while (stack.isEmpty() == false) {
            sorted.add(graph.getVertex(stack.pop()));
        }
        return sorted;
    }

    /**
     * DFS realisation.
     */

    private static <T> void dfs(int verticeIndex, Graph<T> graph, boolean[] isVisited, Deque<Integer> stack) {
        if (isVisited[verticeIndex] == true) {
            return;
        }
        isVisited[verticeIndex] = true;
        List<T> adjacent = graph.adjacentVertices(verticeIndex);
        for (T neigbour : adjacent) {
            int neighborId = graph.getVertexId(neigbour);
            dfs(neighborId, graph, isVisited, stack);
        }
        stack.push(verticeIndex);
    }
}