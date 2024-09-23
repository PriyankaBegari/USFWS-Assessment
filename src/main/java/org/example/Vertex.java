package org.example;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    //This id is how I will uniquely identify a vertex
    private final long id;
    private final List<Edge> outgoingEdges;
    private final List<Edge> incomingEdges;

    public Vertex(long id)
    {
        this.id=id;
        outgoingEdges=new ArrayList<>();
        incomingEdges=new ArrayList<>();
    }

    public long getId()
    {
        return id;
    }

    public List<Edge> getOutgoingEdges()
    {
        return outgoingEdges;
    }

    public List<Edge> getIncomingEdges()
    {
        return incomingEdges;
    }

    public void addOutgoingEdge(Edge edge)
    {
        outgoingEdges.add(edge);
    }

    public void addIncomingEdge(Edge edge)
    {
        incomingEdges.add(edge);
    }

    public String toString()
    {
        return String.valueOf(id);
    }
}
