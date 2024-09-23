package org.example;

public class Edge {
    private final Vertex fromVertex;
    private final Vertex toVertex;

    public Edge(Vertex fromVertex, Vertex toVertex) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.fromVertex.addOutgoingEdge(this);
        this.toVertex.addIncomingEdge(this);
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public String toString() {
        return fromVertex + "->" + toVertex;
    }
}
