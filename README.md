
## Task

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

## 1. Time Complexity

- **Topological Sorting**: The DFS to compute topological sorting takes `O(V + E)` time, where `V` is the number of vertices and `E` is the number of edges in the graph.

- **Longest Path Calculation**: For each vertex in the topological order, we check its outgoing edges. This also takes `O(V + E)` time, as we iterate through all vertices and edges.

Thus, the overall time complexity is: **O(V + E)**


## 2. Space Complexity

- **Graph Representation**: We store the graph using adjacency lists, which takes `O(V + E)` space.

- **Auxiliary Data Structures**:
    - The `visited` set and `postOrder` list in DFS take `O(V)` space.
    - The `longestPathLengths` and `longestPathNextNodes` HashMaps take `O(V)` space.

Thus, the overall space complexity is: **O(V + E)**

### Running the Tests

I have added 6 tests in the LongestPathTest.java file. You can run the tests using the below command:

`mvn test`