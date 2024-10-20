package ru.nsu.demidov.graph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class AdjacencyMatrixTest {
    @Test
    void AdjacencyMatrixTesting() {
        Graph<String> adjacencyMatrixGraph = new AdjacencyMatrix<>();
        adjacencyMatrixGraph.addVertex("A");
        adjacencyMatrixGraph.addVertex("B");
        adjacencyMatrixGraph.addVertex("C");
        adjacencyMatrixGraph.addVertex("D");
        adjacencyMatrixGraph.addEdge("A", "B");
        adjacencyMatrixGraph.addEdge("A", "C");
        adjacencyMatrixGraph.addEdge("A", "D");
        adjacencyMatrixGraph.addEdge("B", "C");
        assert (Objects.equals(adjacencyMatrixGraph.getVertex(0), "A"));
        assert (Objects.equals(adjacencyMatrixGraph.getVertex(1), "B"));
        assert (Objects.equals(adjacencyMatrixGraph.getVertex(2), "C"));
        assert (Objects.equals(adjacencyMatrixGraph.getVertex(3), "D"));
        assert (Objects.equals(adjacencyMatrixGraph.getVertexId("A"), 0));
        assert (Objects.equals(adjacencyMatrixGraph.getVertexId("B"), 1));
        assert (Objects.equals(adjacencyMatrixGraph.getVertexId("C"), 2));
        assert (Objects.equals(adjacencyMatrixGraph.getVertexId("D"), 3));
        assert (Objects.equals(adjacencyMatrixGraph.verticesCount(), 4));
        assert (Objects.equals(adjacencyMatrixGraph.adjacentVertices(0), List.of("B", "C", "D")));
        assert (Objects.equals(adjacencyMatrixGraph.print(), "A - B - C - D\n" + "B - C\n" + "C\n" + "D\n"));
        adjacencyMatrixGraph.removeVertex("A");
        try {
            System.out.println(adjacencyMatrixGraph.getVertexId("A"));
        }
        catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "Cannot invoke \"java.lang.Integer.intValue()\" because the return value of \"java.util.Map.get(Object)\" is null"));
        }

        try {
            System.out.println(adjacencyMatrixGraph.getVertex(3));
        }
        catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "Index 3 out of bounds for length 3"));
        }
    }

}