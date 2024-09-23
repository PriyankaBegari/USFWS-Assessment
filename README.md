
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

# Questions based on understanding the problem statement and approach.
## 1. Did you fully read and understand the problem requirements?
    - Yes,I thoroughly understood the problem
## 2. Did you write and run tests? 
    -  Yes, I did. And added all the possible test cases.
## 3. Did you check for edge cases? 
    - Yes, I did. My test cases include a graph with no edges, another one with disconnected components, star graph, and a binary tree graph.
## 4. Did you fully consider performance? Efficiency? Scalability? 
    - Yes, I've implemented it with O (V +E) time and space complexity which are the optimal computational complexities for the given problem. I have also made it very scalable by structuring it into separate Vertex, Edge and Graph classes. This allows us to add any new functionalities that we want to add to the Graph class e.g., the problem of finding the shortest path between two nodes which BFS, or we can also add a weight field to the Edge class and setup a Dijkstra's algorithm in the Graph class to find a path with minimum sum of weights.
## 5. Did you answer the questions at the bottom of the explanation? 
    -  yes, I answered them and included them in the file
## 6. Are you prepared to discuss your work if you are asked to participate in the interview process?
    - Yes, I am eager to discuss about this in the interview.

# Questions to be answered along with coding exercise:
## 1. Does the solution work for larger graphs?
- Yes, it works for larger graphs as the space and time complexity of my algorithm just scale linearly with the size of the graph.  
## 2. Can you think of any optimizations?
- One optimization I can think of is we don't actually need the whole topological ordering. We can just do DFS starting at the given vertex and get the topological ordering of only the vertices reachable from the given vertex as we are looking for only the longest path starting at the given vertex. Then, the rest of the procedure remains the same.
- Another optimization I can think of is in the case of multiple components within the graph if we have some identifiable information like a field in the Vertex class that directly corresponds to distinct components in the graph then we can separate out the individual components first and find the longest component in the component containing the source vertex reducing the need to travel over all the edges of the graph.
## 3. What’s the computational complexity of your solution?
- As explained in the "Time and Space Complexity" section above, both my space and time complexities are O(V+E).
## 4. Are there any unusual cases that aren't handled?
- I wrote my code thinking exhaustively through all the cases. The only assumption I made was the given graph is a DAG which is given in the problem statement. Instead of that, we can also add an additional subroutine to check whether the given graph is a DAG or not and inform the user about that. 
