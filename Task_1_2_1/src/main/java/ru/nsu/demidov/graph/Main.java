package ru.nsu.demidov.graph;

import java.util.List;
public class Main {
    public static void main(String[] args) throws Exception {
        Graph<String> graph = new AdjacencyMatrix<>();
        graph.readFile("C:\\Users\\dmitr\\oop_rep\\OOP\\Task_1_2_1\\src\\main\\java\\ru\\nsu\\demidov\\graph\\input.txt");
        graph.print();
        List<String> sorted = graph.toposort();
        for (int i = 0; i < sorted.size(); ++i) {
            System.out.print(sorted.get(i) + " ");
        }
    }
}