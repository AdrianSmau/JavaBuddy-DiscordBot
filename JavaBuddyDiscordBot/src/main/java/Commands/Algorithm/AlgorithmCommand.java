package Commands.Algorithm;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmCommand implements CommandInterface {
    private static final Map<String, String> AlgorithmMap = new HashMap<>();

    static {
        AlgorithmMap.put("bubblesort", """
                ```void bubbleSort(int arr[])
                   {
                       int n = arr.length;
                       for (int i = 0; i < n-1; i++)
                           for (int j = 0; j < n-i-1; j++)
                               if (arr[j] > arr[j+1])
                               {
                                   // swap arr[j+1] and arr[j]
                                   int temp = arr[j];
                                   arr[j] = arr[j+1];
                                   arr[j+1] = temp;
                               }
                   }```""".indent(1));

        AlgorithmMap.put("dijkstraalgorithm", """
                ```void dijkstra(int graph[][], int src)
                    {
                        int dist[] = new int[V]; // The output array. dist[i] will hold
                        // the shortest distance from src to i
                 \s
                        // sptSet[i] will true if vertex i is included in shortest
                        // path tree or shortest distance from src to i is finalized
                        Boolean sptSet[] = new Boolean[V];
                 \s
                        // Initialize all distances as INFINITE and stpSet[] as false
                        for (int i = 0; i < V; i++) {
                            dist[i] = Integer.MAX_VALUE;
                            sptSet[i] = false;
                        }
                 \s
                        // Distance of source vertex from itself is always 0
                        dist[src] = 0;
                 \s
                        // Find shortest path for all vertices
                        for (int count = 0; count < V - 1; count++) {
                            // Pick the minimum distance vertex from the set of vertices
                            // not yet processed. u is always equal to src in first
                            // iteration.
                            int u = minDistance(dist, sptSet);
                 \s
                            // Mark the picked vertex as processed
                            sptSet[u] = true;
                 \s
                            // Update dist value of the adjacent vertices of the
                            // picked vertex.
                            for (int v = 0; v < V; v++)
                 \s
                                // Update dist[v] only if is not in sptSet, there is an
                                // edge from u to v, and total weight of path from src to
                                // v through u is smaller than current value of dist[v]
                                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                                    dist[v] = dist[u] + graph[u][v];
                        }
                 \s
                        // print the constructed distance array
                        printSolution(dist);
                    }```""");
        AlgorithmMap.put("bfs", """
                 ```void BFS(int start)
                    {
                        \s
                        // Visited vector to so that
                        // a vertex is not visited more than once
                        // Initializing the vector to false as no
                        // vertex is visited at the beginning
                        boolean[] visited = new boolean[v];
                        Arrays.fill(visited, false);
                        List<Integer> q = new ArrayList<>();
                        q.add(start);
                \s
                        // Set source as visited
                        visited[start] = true;
                \s
                        int vis;
                        while (!q.isEmpty())
                        {
                            vis = q.get(0);
                \s
                            // Print the current node
                            System.out.print(vis + " ");
                            q.remove(q.get(0));
                \s
                            // For every adjacent vertex to
                            // the current vertex
                            for(int i = 0; i < v; i++)
                            {
                                if (adj[vis][i] == 1 && (!visited[i]))
                                {
                                    \s
                                    // Push the adjacent node to
                                    // the queue
                                    q.add(i);
                \s
                                    // Set
                                    visited[i] = true;
                                }
                            }
                        }
                    }```""");
        AlgorithmMap.put("dfs", """
                ```void DFS(int[][] matrix, int source){
                        boolean[] visited = new boolean[matrix.length];
                        visited[source-1] = true;
                        Stack<Integer> stack = new Stack<>();
                        stack.push(source);
                        int i,x;
                        System.out.println("The depth first order is");
                        System.out.println(source);
                        while(!stack.isEmpty()){
                            x = stack.pop();
                            for(i=0; i<matrix.length; i++){
                                if(matrix[x-1][i] == 1 && visited[i] == false){
                                    stack.push(x);
                                    visited[i] = true;
                                    System.out.println(i+1);
                                    x = i+1;
                                    i = -1;
                                }
                            }
                        }
                    }```""");
        AlgorithmMap.put("topologicalsort", """
                ```public void topologicalSort()
                    {
                        // Create a array to store
                        // indegrees of all
                        // vertices. Initialize all
                        // indegrees as 0.
                        int indegree[] = new int[V];
                 \s
                        // Traverse adjacency lists
                        // to fill indegrees of
                        // vertices. This step takes
                        // O(V+E) time
                        for (int i = 0; i < V; i++) {
                            ArrayList<Integer> temp
                                = (ArrayList<Integer>)adj[i];
                            for (int node : temp) {
                                indegree[node]++;
                            }
                        }
                 \s
                        // Create a queue and enqueue
                        // all vertices with indegree 0
                        Queue<Integer> q
                            = new LinkedList<Integer>();
                        for (int i = 0; i < V; i++) {
                            if (indegree[i] == 0)
                                q.add(i);
                        }
                 \s
                        // Initialize count of visited vertices
                        int cnt = 0;
                 \s
                        // Create a vector to store result
                        // (A topological ordering of the vertices)
                        Vector<Integer> topOrder = new Vector<Integer>();
                        while (!q.isEmpty()) {
                            // Extract front of queue
                            // (or perform dequeue)
                            // and add it to topological order
                            int u = q.poll();
                            topOrder.add(u);
                 \s
                            // Iterate through all its
                            // neighbouring nodes
                            // of dequeued node u and
                            // decrease their in-degree
                            // by 1
                            for (int node : adj[u]) {
                                // If in-degree becomes zero,
                                // add it to queue
                                if (--indegree[node] == 0)
                                    q.add(node);
                            }
                            cnt++;
                        }
                 \s
                        // Check if there was a cycle
                        if (cnt != V) {
                            System.out.println(
                                "There exists a cycle in the graph");
                            return;
                        }```""");
        AlgorithmMap.put("kruskalalgorithm", """
                ```void KruskalMST()
                {
                // This will store the resultant MST
                Edge result[] = new Edge[V];
                // An index variable, used for result[]
                int e = 0;
                // An index variable, used for sorted edges
                int i = 0;
                for (i = 0; i < V; ++i)
                       result[i] = new Edge();
                    // Step 1:  Sort all the edges in non-decreasing
                    // order of their weight.  If we are not allowed to
                    // change the given graph, we can create a copy of
                    // array of edges
                    Arrays.sort(edge);
                    // Allocate memory for creating V subsets
                    subset subsets[] = new subset[V];
                      for (i = 0; i < V; ++i)
                          subsets[i] = new subset();
                       // Create V subsets with single elements
                       for (int v = 0; v < V; ++v)
                       {
                          subsets[v].parent = v;
                          subsets[v].rank = 0;
                       }
                       i = 0; // Index used to pick next edge
                       // Number of edges to be taken is equal to V-1
                       while (e < V - 1)
                       {
                           // Step 2: Pick the smallest edge. And increment
                           // the index for next iteration
                           Edge next_edge = edge[i++];
                           int x = find(subsets, next_edge.src);
                           int y = find(subsets, next_edge.dest);
                           // If including this edge doesn't cause cycle,
                           // include it in result and increment the index
                           // of result for next edge
                           if (x != y) {
                               result[e++] = next_edge;
                               Union(subsets, x, y);
                        }
                          // Else discard the next_edge
                        }
                     // print the contents of result[] to display
                     // the built MST
                     System.out.println("Following are the edges in "
                                        + "the constructed MST");
                     int minimumCost = 0;
                     for (i = 0; i < e; ++i)
                      {
                         System.out.println(result[i].src + " -- "
                                            + result[i].dest
                                            + " == " + result[i].weight);
                         minimumCost += result[i].weight;
                      }
                     System.out.println("Minimum Cost Spanning Tree "
                                     + minimumCost);}```""");
        AlgorithmMap.put("primalgorithm", """
                   ```void primMST(int graph[][])
                   {
                       // Array to store constructed MST
                       int parent[] = new int[V];
                \s
                       // Key values used to pick minimum weight edge in cut
                       int key[] = new int[V];
                \s
                       // To represent set of vertices included in MST
                       Boolean mstSet[] = new Boolean[V];
                \s
                       // Initialize all keys as INFINITE
                       for (int i = 0; i < V; i++) {
                           key[i] = Integer.MAX_VALUE;
                           mstSet[i] = false;
                       }
                \s
                       // Always include first 1st vertex in MST.
                       key[0] = 0; // Make key 0 so that this vertex is
                       // picked as first vertex
                       parent[0] = -1; // First node is always root of MST
                \s
                       // The MST will have V vertices
                       for (int count = 0; count < V - 1; count++) {
                           // Pick thd minimum key vertex from the set of vertices
                           // not yet included in MST
                           int u = minKey(key, mstSet);
                \s
                           // Add the picked vertex to the MST Set
                           mstSet[u] = true;
                \s
                           // Update key value and parent index of the adjacent
                           // vertices of the picked vertex. Consider only those
                           // vertices which are not yet included in MST
                           for (int v = 0; v < V; v++)
                \s
                               // graph[u][v] is non zero only for adjacent vertices of m
                               // mstSet[v] is false for vertices not yet included in MST
                               // Update the key only if graph[u][v] is smaller than key[v]
                               if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                                   parent[v] = u;
                                   key[v] = graph[u][v];
                               }
                       }
                \s
                       // print the constructed MST
                       printMST(parent, graph);
                   }```""".indent(1));
        AlgorithmMap.put("connectivitycheck", """
                ```void isConnected(Graph graph){
                 \s
                        int vertices = graph.vertices;
                        LinkedList<Integer> adjacencyList [] = graph.adjacencyList;
                 \s
                        // Take a boolean visited array
                        boolean[] visited = new boolean[vertices];
                 \s
                        // Start the DFS from vertex 0
                        DFS(0, adjacencyList, visited);
                 \s
                        // Check if all the vertices are visited
                        // Set connected to False if one node is unvisited
                        boolean connected = true;
                       \s
                        for (int i = 0; i <visited.length ; i++) {
                            if(!visited[i]){
                                connected = false;
                                break;
                            }
                        }
                       \s
                        if(connected){
                            System.out.println("Graph is connected");
                        }else{
                            System.out.println("Graph is disconnected");
                        }
                    }```""");
        AlgorithmMap.put("binarysearch", """
                ```int binarySearch(int arr[], int l, int r, int x)
                    {
                        if (r >= l) {
                            int mid = l + (r - l) / 2;
                \s
                            // If the element is present at the
                            // middle itself
                            if (arr[mid] == x)
                                return mid;
                \s
                            // If element is smaller than mid, then
                            // it can only be present in left subarray
                            if (arr[mid] > x)
                                return binarySearch(arr, l, mid - 1, x);
                \s
                            // Else the element can only be present
                            // in right subarray
                            return binarySearch(arr, mid + 1, r, x);
                        }
                \s
                        // We reach here when element is not present
                        // in array
                        return -1;
                    }```""");
        AlgorithmMap.put("floyd-warshall", """
                ```void floydWarshall(int graph[][])
                    {
                        int dist[][] = new int[V][V];
                        int i, j, k;
                \s
                        /* Initialize the solution matrix
                           same as input graph matrix.
                           Or we can say the initial values
                           of shortest distances
                           are based on shortest paths
                           considering no intermediate
                           vertex. */
                         
                        for (i = 0; i < V; i++)
                            for (j = 0; j < V; j++)
                                dist[i][j] = graph[i][j];
                                
                \s
                        /* Add all vertices one by one
                           to the set of intermediate
                           vertices.
                          ---> Before start of an iteration,
                               we have shortest
                               distances between all pairs
                               of vertices such that
                               the shortest distances consider
                               only the vertices in
                               set {0, 1, 2, .. k-1} as
                               intermediate vertices.
                          ----> After the end of an iteration,
                                vertex no. k is added
                                to the set of intermediate
                                vertices and the set
                                becomes {0, 1, 2, .. k} */
                        for (k = 0; k < V; k++)
                        {
                            // Pick all vertices as source one by one
                            for (i = 0; i < V; i++)
                            {
                                // Pick all vertices as destination for the
                                // above picked source
                                for (j = 0; j < V; j++)
                                {
                                    // If vertex k is on the shortest path from
                                    // i to j, then update the value of dist[i][j]
                                    if (dist[i][k] + dist[k][j] < dist[i][j])
                                        dist[i][j] = dist[i][k] + dist[k][j];
                                }
                            }
                        }
                \s
                        // Print the shortest distance matrix
                        printSolution(dist);
                    }```""");
        AlgorithmMap.put("bellman-ford", """
                ```void BellmanFord(Graph graph, int src)
                    {
                        int V = graph.V, E = graph.E;
                        int dist[] = new int[V];
                 \s
                        // Step 1: Initialize distances from src to all other
                        // vertices as INFINITE
                        for (int i = 0; i < V; ++i)
                            dist[i] = Integer.MAX_VALUE;
                        dist[src] = 0;
                 \s
                        // Step 2: Relax all edges |V| - 1 times. A simple
                        // shortest path from src to any other vertex can
                        // have at-most |V| - 1 edges
                        for (int i = 1; i < V; ++i) {
                            for (int j = 0; j < E; ++j) {
                                int u = graph.edge[j].src;
                                int v = graph.edge[j].dest;
                                int weight = graph.edge[j].weight;
                                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                                    dist[v] = dist[u] + weight;
                            }
                        }
                 \s
                        // Step 3: check for negative-weight cycles. The above
                        // step guarantees shortest distances if graph doesn't
                        // contain negative weight cycle. If we get a shorter
                        // path, then there is a cycle.
                        for (int j = 0; j < E; ++j) {
                            int u = graph.edge[j].src;
                            int v = graph.edge[j].dest;
                            int weight = graph.edge[j].weight;
                            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                                System.out.println("Graph contains negative weight cycle");
                                return;
                            }
                        }
                        printArr(dist, V);
                    }```""");
        AlgorithmMap.put("basiceuclideanalgorithm", """
                ```int gcd(int a, int b)
                    {
                        if (a == 0)
                            return b;
                        \s
                        return gcd(b%a, a);
                    }```""");
        AlgorithmMap.put("extendedeuclideanalgorithm", """
                ```int gcdExtended(int a, int b, int x, int y)
                    {
                        // Base Case
                        if (a == 0)
                        {
                            x = 0;
                            y = 1;
                            return b;
                        }
                \s
                        int x1=1, y1=1; // To store results of recursive call
                        int gcd = gcdExtended(b%a, a, x1, y1);
                \s
                        // Update x and y using results of recursive
                        // call
                        x = y1 - (b/a) * x1;
                        y = x1;
                \s
                        return gcd;
                    }```""");
    }

    public String findAlgorithm(String key) {
        for (Map.Entry<String, String> temp : AlgorithmMap.entrySet()) {
            {
                if (temp.getKey().equalsIgnoreCase(key) || temp.getKey().contains(key)) return temp.getValue();
            }
        }
        return null;
    }

    @Override
    public void handle(CommandContext ctx) {
        if (ctx.getArgs().isEmpty()) {
            ctx.getChannel().sendMessage("Correct usage for the JavaBuddy Algorithm is: `-algorithm 'fundamental_algorithm'`").queue();
            return;
        }
        if (ctx.getArgs().size() != 1) {
            ctx.getChannel().sendMessage("Correct usage for the JavaBuddy Algorithm is: `-algorithm 'fundamental_algorithm'`").queue();
            return;
        }
        String key = ctx.getArgs().get(0);
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Algorithm - " + key);
        info.setDescription("Attempting to get an implementation of `" + key + "` algorithm");
        String result = findAlgorithm(key.toLowerCase());
        boolean found = false;
        if (result != null) {
            info.addField("Algorithm found!", "Generating the found Algorithm below!...", false);
            found = true;
        } else
            info.addField("404 not found!", "This algorithm does not exist! Please consult the `-algorithms` command!", false);
        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
        if (!found)
            return;
        info = new EmbedBuilder();
        info.setTitle(key + " - Our Implementation!");
        info.setDescription(result);
        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
    }

    @Override
    public String getName() {
        return "algorithm";
    }

    @Override
    public String getHelp() {
        return "Displays an implementation of the specified Algorithm";
    }
}
