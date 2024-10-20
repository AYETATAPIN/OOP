package ru.nsu.demidov.graph;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;

class TopSortTest {
    @Test
    void TopSortTesting() {
        Graph<String> adjacencyMatrixGraph = new AdjacencyMatrix<>();
        adjacencyMatrixGraph.addVertex("A");
        adjacencyMatrixGraph.addVertex("B");
        adjacencyMatrixGraph.addVertex("C");
        adjacencyMatrixGraph.addVertex("D");
        adjacencyMatrixGraph.addEdge("A", "B");
        adjacencyMatrixGraph.addEdge("A", "C");
        adjacencyMatrixGraph.addEdge("A", "D");
        adjacencyMatrixGraph.addEdge("B", "C");
        TopSort<String> sampleSort1 = new TopSort<>();
        List<String> sampleSorted1 = sampleSort1.toposort(adjacencyMatrixGraph);
        List<String> sorted1 = List.of(new String[]{"A", "D", "B", "C"});
        assert (Objects.equals(sorted1, sampleSorted1));

        Graph<String> incidenceMatrixGraph = new IncidenceMatrix<>();
        incidenceMatrixGraph.addVertex("A");
        incidenceMatrixGraph.addVertex("B");
        incidenceMatrixGraph.addVertex("C");
        incidenceMatrixGraph.addVertex("D");
        incidenceMatrixGraph.addEdge("A", "B");
        incidenceMatrixGraph.addEdge("A", "C");
        incidenceMatrixGraph.addEdge("A", "D");
        incidenceMatrixGraph.addEdge("B", "C");
        TopSort<String> sampleSort2 = new TopSort<>();
        List<String> sampleSorted2 = sampleSort2.toposort(incidenceMatrixGraph);
        List<String> sorted2 = List.of(new String[]{"A", "D", "B", "C"});
        assert (Objects.equals(sorted2, sampleSorted2));

        Graph<String> adjacencyListGraph = new AdjacencyList<>();
        adjacencyListGraph.addVertex("A");
        adjacencyListGraph.addVertex("B");
        adjacencyListGraph.addVertex("C");
        adjacencyListGraph.addVertex("D");
        adjacencyListGraph.addEdge("A", "B");
        adjacencyListGraph.addEdge("A", "C");
        adjacencyListGraph.addEdge("A", "D");
        adjacencyListGraph.addEdge("B", "C");
        TopSort<String> sampleSort3 = new TopSort<>();
        List<String> sampleSorted3 = sampleSort3.toposort(adjacencyListGraph);
        List<String> sorted3 = List.of(new String[]{"A", "D", "B", "C"});
        assert (Objects.equals(sorted3, sampleSorted3));
    }

}