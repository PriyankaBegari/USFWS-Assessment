package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestPathTest {

    @Test
    public void testLongestPathWithSingleVertex() {
        //Graph with a single vertex
        Vertex v = new Vertex(0);
        Graph graph = new Graph(List.of(v), new ArrayList<>());
        assertEquals(List.of(v), graph.getLongestPathStartingAtAVertex(v));
    }

    @Test
    public void testLongestPathInStarGraph() {
        //Graph with one vertex in the center with edges only going from this vertex to other vertices which are leaves
        Vertex center = new Vertex(0);
        Vertex leaf1 = new Vertex(1);
        Vertex leaf2 = new Vertex(2);
        Vertex leaf3 = new Vertex(3);

        List<Vertex> vertexList = List.of(center, leaf1, leaf2, leaf3);
        List<Edge> edgeList = List.of(
                new Edge(center, leaf1),
                new Edge(center, leaf2),
                new Edge(center, leaf3)
        );

        Graph graph = new Graph(vertexList, edgeList);

        //Either of these 3 could be the output of the algorithm as they all are the longest paths
        assertTrue(List.of(center, leaf1).equals(graph.getLongestPathStartingAtAVertex(center))
                || List.of(center, leaf2).equals(graph.getLongestPathStartingAtAVertex(center))
                || List.of(center, leaf3).equals(graph.getLongestPathStartingAtAVertex(center)));
        assertEquals(List.of(leaf1), graph.getLongestPathStartingAtAVertex(leaf1));
        assertEquals(List.of(leaf2), graph.getLongestPathStartingAtAVertex(leaf2));
        assertEquals(List.of(leaf3), graph.getLongestPathStartingAtAVertex(leaf3));
    }

    @Test
    public void testLongestPathInChainGraph() {
        //Graph with vertices arranged in a line and edges going from left to right in a chain
        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);

        List<Vertex> vertexList = List.of(v0, v1, v2);
        List<Edge> edgeList = List.of(
                new Edge(v0, v1),
                new Edge(v1, v2)
        );

        Graph graph = new Graph(vertexList, edgeList);
        assertEquals(List.of(v0, v1, v2), graph.getLongestPathStartingAtAVertex(v0));
        assertEquals(List.of(v1, v2), graph.getLongestPathStartingAtAVertex(v1));
        assertEquals(List.of(v2), graph.getLongestPathStartingAtAVertex(v2));
    }

    @Test
    public void testLongestPathInBinaryTreeGraph() {
        //Graph which is a binary tree
        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        List<Vertex> vertexList = List.of(v0, v1, v2, v3, v4);
        List<Edge> edgeList = List.of(
                new Edge(v0, v1),
                new Edge(v0, v2),
                new Edge(v1, v3),
                new Edge(v1, v4)
        );

        Graph graph = new Graph(vertexList, edgeList);

        //Either of these 2 could be the output of the algorithm as they all are the longest paths
        assertTrue(List.of(v0, v1, v3).equals(graph.getLongestPathStartingAtAVertex(v0))
                || List.of(v0, v1, v4).equals(graph.getLongestPathStartingAtAVertex(v0)));
        //Either of these 2 could be the output of the algorithm as they all are the longest paths
        assertTrue(List.of(v1, v3).equals(graph.getLongestPathStartingAtAVertex(v1))
                || List.of(v1, v4).equals(graph.getLongestPathStartingAtAVertex(v1)));
        assertEquals(List.of(v2), graph.getLongestPathStartingAtAVertex(v2));
        assertEquals(List.of(v3), graph.getLongestPathStartingAtAVertex(v3));
        assertEquals(List.of(v4), graph.getLongestPathStartingAtAVertex(v4));
    }

    @Test
    public void testLongestPathWithDisconnectedGraph() {
        //Graph with a few disconnected vertices
        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        List<Vertex> vertexList = List.of(v0, v1, v2, v3, v4);
        List<Edge> edgeList = List.of(
                new Edge(v0, v1),
                new Edge(v1, v2)
                // v3 and v4 are disconnected
        );

        Graph graph = new Graph(vertexList, edgeList);
        assertEquals(List.of(v0, v1, v2), graph.getLongestPathStartingAtAVertex(v0));
        assertEquals(List.of(v1, v2), graph.getLongestPathStartingAtAVertex(v1));
        assertEquals(List.of(v2), graph.getLongestPathStartingAtAVertex(v2));
        assertEquals(List.of(v3), graph.getLongestPathStartingAtAVertex(v3));
        assertEquals(List.of(v4), graph.getLongestPathStartingAtAVertex(v4));
    }

    @Test
    public void testLongestPathWithMultipleDisconnectedComponents() {
        //Graph is disconnected with edges in multiple components
        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        List<Vertex> vertexList = List.of(v0, v1, v2, v3, v4, v5);
        List<Edge> edgeList = List.of(
                new Edge(v0, v1),
                new Edge(v1, v2),
                // First component is 0 -> 1 -> 2
                new Edge(v3, v4)
                // Second component is 3 -> 4
                // v5 is completely disconnected
        );

        Graph graph = new Graph(vertexList, edgeList);
        assertEquals(List.of(v0, v1, v2), graph.getLongestPathStartingAtAVertex(v0));
        assertEquals(List.of(v1, v2), graph.getLongestPathStartingAtAVertex(v1));
        assertEquals(List.of(v2), graph.getLongestPathStartingAtAVertex(v2));
        assertEquals(List.of(v3, v4), graph.getLongestPathStartingAtAVertex(v3));
        assertEquals(List.of(v4), graph.getLongestPathStartingAtAVertex(v4));
        assertEquals(List.of(v5), graph.getLongestPathStartingAtAVertex(v5));
    }
}
