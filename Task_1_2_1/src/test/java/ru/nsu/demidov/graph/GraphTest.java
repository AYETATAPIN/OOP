package ru.nsu.demidov.graph;

import java.util.Objects;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class GraphTest {
    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjacencyMatrix<String>()),
                    Arguments.of(new IncidenceMatrix<String>()),
                    Arguments.of(new AdjacencyList<String>())
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void graphTesting(Graph<String> sampleGraph) throws Exception {
        sampleGraph.readFile("input.txt");
        assert (Objects.equals(sampleGraph.getVertex(0), "A"));
        assert (Objects.equals(sampleGraph.getVertex(1), "B"));
        assert (Objects.equals(sampleGraph.getVertex(2), "C"));
        assert (Objects.equals(sampleGraph.getVertex(3), "D"));
        assert (Objects.equals(sampleGraph.getVertexId("A"), 0));
        assert (Objects.equals(sampleGraph.getVertexId("B"), 1));
        assert (Objects.equals(sampleGraph.getVertexId("C"), 2));
        assert (Objects.equals(sampleGraph.getVertexId("D"), 3));
        assert (Objects.equals(sampleGraph.verticesCount(), 4));
        assert (Objects.equals(sampleGraph.adjacentVertices(0), List.of("B", "C", "D")));
        sampleGraph.removeVertex("A");
        try {
            System.out.println(sampleGraph.getVertexId("A"));
        } catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "Cannot invoke \"java.lang.Integer."
                + "intValue()\" because the return value "
                + "of \"java.util.Map.get(Object)\" is null"));
        }

        try {
            sampleGraph.removeVertex("A");
        } catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "You stoopid no such vertex A"));
        }

        try {
            System.out.println(sampleGraph.getVertex(3));
        } catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(),
                "Index 3 out of bounds for length 3"));
        }

        try {
            sampleGraph.removeEdge("A", "B");
        } catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "You stoopid no such edge A and B"));
        }

        try {
            sampleGraph.addEdge("BOOBS", "KNOCKERS");
        } catch (Exception exception) {
            assert (Objects.equals(exception.getMessage(), "You stoopid no such vertices "
                + "BOOBS and KNOCKERS"));
        }
    }
}