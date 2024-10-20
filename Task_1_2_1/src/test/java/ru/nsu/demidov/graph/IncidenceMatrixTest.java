package ru.nsu.demidov.graph;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class IncidenceMatrixTest {
    @Test
    void IncidenceMatrixTesting() {
        Graph<String> incidenceMatrixGraph = new IncidenceMatrix<>();
        incidenceMatrixGraph.addVertex("A");
        incidenceMatrixGraph.addVertex("B");
        incidenceMatrixGraph.addVertex("C");
        incidenceMatrixGraph.addVertex("D");
        incidenceMatrixGraph.addEdge("A", "B");
        incidenceMatrixGraph.addEdge("A", "C");
        incidenceMatrixGraph.addEdge("A", "D");
        incidenceMatrixGraph.addEdge("B", "C");
        assert (Objects.equals(incidenceMatrixGraph.getVertex(0), "A"));
        assert (Objects.equals(incidenceMatrixGraph.getVertex(1), "B"));
        assert (Objects.equals(incidenceMatrixGraph.getVertex(2), "C"));
        assert (Objects.equals(incidenceMatrixGraph.getVertex(3), "D"));
        assert (Objects.equals(incidenceMatrixGraph.getVertexId("A"), 0));
        assert (Objects.equals(incidenceMatrixGraph.getVertexId("B"), 1));
        assert (Objects.equals(incidenceMatrixGraph.getVertexId("C"), 2));
        assert (Objects.equals(incidenceMatrixGraph.getVertexId("D"), 3));
        assert (Objects.equals(incidenceMatrixGraph.verticesCount(), 4));
        System.out.println(incidenceMatrixGraph.print());
        assert (Objects.equals(incidenceMatrixGraph.adjacentVertices(0), List.of("B", "C", "D")));
        //assert (Objects.equals(incidenceMatrixGraph.print(), "A - B - C - D\n" + "B - C\n" + "C\n" + "D\n"));
        incidenceMatrixGraph.removeVertex("A");
        try {
            System.out.println(incidenceMatrixGraph.getVertexId("A"));
        }
        catch (Exception exception) {
            //assert (Objects.equals(exception.getMessage(), "Cannot invoke \"java.lang.Integer.intValue()\" because the return value of \"java.util.Map.get(Object)\" is null"));
        }

        try {
            System.out.println(incidenceMatrixGraph.getVertex(3));
        }
        catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "Index 3 out of bounds for length 3"));
        }
    }
}