package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //Below is an example of 5 vertices for which I will show the working of the algorithm
        int n = 5;
        List<Vertex> vertexList = new ArrayList<>(n);

        //this will generate the unique IDs
        //this is not very important to the core of the algorithm
        Random random = new Random();
        HashSet<Long> uniqueIDs = new HashSet<>();
        long minRandomValue = 1;
        long maxRandomValue = 1000;
        while (uniqueIDs.size() < n) {
            long randomValue = minRandomValue + (long) (random.nextDouble() * (maxRandomValue - minRandomValue));
            uniqueIDs.add(randomValue);
        }

        for (Long id : uniqueIDs) {
            vertexList.add(new Vertex(id));
        }

        //create a list of edges
        //2->1
        //2->0
        //1->0
        //1->3
        //0->4
        List<Edge> edgeList = List.of(
                new Edge(vertexList.get(2), vertexList.get(0)),
                new Edge(vertexList.get(2), vertexList.get(1)),
                new Edge(vertexList.get(1), vertexList.get(0)),
                new Edge(vertexList.get(1), vertexList.get(3)),
                new Edge(vertexList.get(0), vertexList.get(4))
        );

        //Here a longest paths starting at each of the respective vertices will be
        //starting at vertex 0 it will be 0->4
        //starting at vertex 1 it will be 1->0->4
        //starting at vertex 2 it will be 2->1->0->4
        //starting at vertex 3 it will be 3
        //starting at vertex 4 it will be 4

        System.out.println("These are our vertices " + vertexList);
        System.out.println("These are our edges " + edgeList);

        //creating a graph
        Graph graph = new Graph(vertexList, edgeList);

        //for each vertex, I will output a longest path starting there
        for (Vertex v : vertexList) {
            List<Vertex> longestPathStartingAtAVertex = graph.getLongestPathStartingAtAVertex(v);
            System.out.println("A longest path starting at vertex " + v + " will be " + longestPathStartingAtAVertex + " of length " + longestPathStartingAtAVertex.size());
        }
    }
}