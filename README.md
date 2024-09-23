
# Task

Given a DAG (Directed Acyclic Graph) and a vertex, we need to calculate the longest directed path from that vertex.

## Approach

### 1. Topological Sorting

We perform Depth First Search (DFS) on the graph to compute a topological order of the vertices. Topological order ensures that each vertex appears before all vertices to which it has outgoing edges.

### 2. Dynamic Programming

Using this topological order, we compute the longest path starting from each vertex in reverse order. The dynamic programming formula is:

`DP[v] = 1 + max{DP[to], for all outgoing edges (v -> to)}`

Where `DP[v]` stores the longest path starting from vertex `v`.

Here, while calculating this value, I will also store the next node on the longest path for each node.

### 3. Reconstructing the Path

Once the dynamic programming table is computed, the longest path can be reconstructed by following the `next` pointers along the longest path from the given source vertex.

Note: There can be multiple longest paths, all of the same length starting at a given vertex. My algorithm would return one such path.

## Time and Space Complexity

### 1. Time Complexity

- **Topological Sorting**: The DFS to compute topological sorting takes `O(V + E)` time, where `V` is the number of vertices and `E` is the number of edges in the graph.

- **Longest Path Calculation**: For each vertex in the topological order, we check its outgoing edges. This also takes `O(V + E)` time, as we iterate through all vertices and edges.

Thus, the overall time complexity is: **O(V + E)**


### 2. Space Complexity

- **Graph Representation**: We store the graph using adjacency lists, which takes `O(V + E)` space.

- **Auxiliary Data Structures**:
    - The `visited` set and `postOrder` list in DFS take `O(V)` space.
    - The `longestPathLengths` and `longestPathNextNodes` HashMaps take `O(V)` space.

Thus, the overall space complexity is: **O(V + E)**

## Running the Tests

I have added 6 tests in the LongestPathTest class. You can run the tests using the below command:

`mvn test`

# Questions
## 1. Does the solution work for larger graphs?
- Yes, it works for larger graphs as the space and time complexity of my algorithm just scale linearly with the size of the graph.  
## 2. Can you think of any optimizations?
- One optimization I can think of is in the case of multiple components within the graph if we have some identifiable information like a field in the Vertex class that corresponds to distinct components in the graph then we can first separate out the individual components first and find the longest component in the component containing the source vertex reducing the need to travel over all the edges of the graph. 
## 3. What’s the computational complexity of your solution?
- As explained in the "Time and Space Complexity" section above, both my space and time complexities are O(V+E).
## 4. Are there any unusual cases that aren't handled?
- I wrote my code thinking exhaustively through all the cases. The only assumption I made was the given graph is a DAG which is given in the problem statement. Instead of that, we can also add an additional subroutine to check whether the given graph is a DAG or not and inform the user about that. 