package ru.nsu.demidov.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class TopSort<T> {
    private boolean[] isVisited;
    private Deque<Integer> stack;

    public List<T> toposort(Graph<T> graph) {
        int verticesCount = graph.verticesCount();
        isVisited = new boolean[verticesCount];
        stack = new ArrayDeque<>();
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i] == false) {
                dfs(i, graph);
            }
        }
        List<T> sorted = new ArrayList<>();
        while (stack.isEmpty() == false) {
            sorted.add(graph.getVertex(stack.pop()));
        }
        return sorted;
    }

    private void dfs(int verticeIndex, Graph<T> graph) {
        if (isVisited[verticeIndex] == true) {
            return;
        }
        isVisited[verticeIndex] = true;
        List<T> adjacent = graph.adjacentVertices(verticeIndex);
        for (T neigbour : adjacent) {
            int neighborId = graph.getVertexId(neigbour);
            dfs(neighborId, graph);
        }
        stack.push(verticeIndex);
    }
}