package ru.nsu.demidov.graph;

import java.util.List;
public class Main {
    public static void main(String[] args) throws Exception {
        Graph<String> graph = new AdjacencyMatrix<>();
        graph.readFile("input.txt");
        graph.print();
        TopSort<String> sampleSort = new TopSort<>();
        List<String> sorted = sampleSort.toposort(graph);
        for (int i = 0; i < sorted.size(); ++i) {
            System.out.print(sorted.get(i) + " ");
        }
    }
}
