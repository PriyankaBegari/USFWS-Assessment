package org.example;

import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;
    private final List<Edge> edgeList;

    public Graph(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = new ArrayList<>(vertexList);
        this.edgeList = new ArrayList<>(edgeList);
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    private void DFS(Vertex v, HashSet<Long> visited, List<Vertex> postOrder) {
        visited.add(v.getId());
        for (Edge outgoingEdge : v.getOutgoingEdges()) {
            Vertex toVertex = outgoingEdge.getToVertex();
            if (!visited.contains(toVertex.getId())) {
                DFS(toVertex, visited, postOrder);
            }
        }
        postOrder.add(v);
    }

    //This method returns topological ordering
    private List<Vertex> getTopologicalOrdering() {
        HashSet<Long> visited = new HashSet<>();
        List<Vertex> postOrder = new ArrayList<>();

        for (Vertex vertex : this.vertexList) {
            if (!visited.contains(vertex.getId())) {
                DFS(vertex, visited, postOrder);
            }
        }

        //topological order is nothing but reverse postorder
        Collections.reverse(postOrder);
        return postOrder;
    }

    public List<Vertex> getLongestPathStartingAtAVertex(Vertex src) {
        //I am topologically sorting the vertices
        List<Vertex> topologicalOrdering = getTopologicalOrdering();

        //I will calculate the next nodes along the longest path starting from each node
        HashMap<Long, Vertex> nextNodeAlongLongestPath = nextNodeInTheLongestPathForAllVertices(topologicalOrdering);

        //Now reconstruct the path for the given vertex v
        List<Vertex> longestPathStartingAtAVertex = new ArrayList<>();
        Vertex currentNode = src;
        while (currentNode != null) {
            longestPathStartingAtAVertex.add(currentNode);
            currentNode = nextNodeAlongLongestPath.get(currentNode.getId());
        }

        return longestPathStartingAtAVertex;
    }

    public HashMap<Long, Vertex> nextNodeInTheLongestPathForAllVertices(List<Vertex> topologicalOrdering) {
        //Now calculate the length of the longest path starting at every vertex using dynamic programming
        //For a DAG, this would be similar to finding the length of the shortest path starting at every node
        //The DP formula will be DP[v] = 1 + max{DP[to], for all outgoing edges (v -> to)}
        //Here, I calculate these DP values in reverse topologicalOrdering in a bottom up manner
        int n = topologicalOrdering.size();
        HashMap<Long, Integer> longestPathLengths = new HashMap<>();
        HashMap<Long, Vertex> longestPathNextNodes = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            Vertex vertex = topologicalOrdering.get(i);
            long id = vertex.getId();
            int longestPathLength = 1;
            Vertex longestPathNextNode = null;
            for (Edge outgoingEdge : vertex.getOutgoingEdges()) {
                Vertex toVertex = outgoingEdge.getToVertex();
                int longestPathLengthUsingThisVertexAsTheSecondVertex = 1 + longestPathLengths.get(toVertex.getId());
                if (longestPathLengthUsingThisVertexAsTheSecondVertex > longestPathLength) {
                    longestPathLength = longestPathLengthUsingThisVertexAsTheSecondVertex;
                    longestPathNextNode = toVertex;
                }
            }
            longestPathLengths.put(id, longestPathLength);
            longestPathNextNodes.put(id, longestPathNextNode);
        }

        return longestPathNextNodes;
    }
}
