package Commands.Algorithm;

import Commands.CommandContext;
import Commands.CommandInterface;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class AlgorithmHelperCommand implements CommandInterface {
    @Override
    public void handle(CommandContext ctx) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Algorithms Command Guide");
        info.setDescription("In order to have JavaBuddy deliver you implementations of you much needed Java Algorithms, select one of the below mentioned Algorithms and mention its name (cAsE iNsEnSiTiVe) when typing the command `-algorithm`!");
        info.addField("BubbleSort", "Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order", false);
        info.addField("DijkstraAlgorithm", "Dijkstra's Algorithm is an algorithm for finding the shortest paths between nodes in a graph. It was conceived by computer scientist Edsger W. Dijkstra in 1956 and published three years later", false);
        info.addField("BFS", "Breadth-First Search (BFS) is an algorithm for traversing or searching tree or graph data structures. It starts at the tree root (or some arbitrary node of a graph), and explores all of the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level", false);
        info.addField("DFS", "Depth-First Search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as far as possible along each branch before backtracking", false);
        info.addField("TopologicalSort", "Topological Sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG", false);
        info.addField("KruskalAlgorithm", "Kruskal's Algorithm finds a minimum spanning forest of an undirected edge-weighted graph. It is a greedy algorithm in graph theory as in each step it adds the next lowest-weight edge that will not form a cycle to the minimum spanning forest", false);
        info.addField("PrimAlgorithm", "Prim's Algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph. The algorithm operates by building this tree one vertex at a time, from an arbitrary starting vertex, at each step adding the cheapest possible connection from the tree to another vertex", false);
        info.addField("ConnectivityCheck", "Connectivity is one of the basic concepts of graph theory: it asks for the minimum number of elements (nodes or edges) that need to be removed to separate the remaining nodes into two or more isolated subgraphs. This algorithm checks if a given graph is connected", false);
        info.addField("BinarySearch", "Binary Search is a search algorithm that finds the position of a target value within a sorted array. Binary search compares the target value to the middle element of the array. If they are not equal, the half in which the target cannot lie is eliminated and the search continues on the remaining half, again taking the middle element to compare to the target value, and repeating this until the target value is found. If the search ends with the remaining half being empty, the target is not in the array", false);
        info.addField("Floyd-Warshall", "The Floyd–Warshall Algorithm is an algorithm for finding shortest paths in a directed weighted graph with positive or negative edge weights (but with no negative cycles). A single execution of the algorithm will find the lengths (summed weights) of shortest paths between all pairs of vertices", false);
        info.addField("Bellman-Ford", "The Bellman–Ford Algorithm is an algorithm that computes shortest paths from a single source vertex to all of the other vertices in a weighted digraph. It is slower than Dijkstra's algorithm for the same problem, but more versatile, as it is capable of handling graphs in which some of the edge weights are negative numbers", false);
        info.addField("BasicEuclideanAlgorithm", "The Euclidean Algorithm is an efficient method for computing the greatest common divisor (GCD) of two integers (numbers), the largest number that divides them both without a remainder", false);
        info.addField("ExtendedEuclideanAlgorithm", "The Extended Euclidean Algorithm is an extension to the Euclidean algorithm, and computes, in addition to the greatest common divisor (gcd) of integers a and b, also the coefficients of Bézout's identity", false);
        info.setFooter("Definitions and implementation of the algorithms provided by `https://www.geeksforgeeks.org/` and `https://en.wikipedia.org/wiki/`!");
        info.setColor(Color.white);
        ctx.getEvent().getChannel().sendMessage(info.build()).queue();
    }

    @Override
    public String getName() {
        return "algorithms";
    }

    @Override
    public String getHelp() {
        return "Displays helpful information regarding our `-algorithm` command";
    }
}
