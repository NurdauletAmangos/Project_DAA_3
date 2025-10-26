package mst;

import java.util.*;

public class Graph {
    public List<String> nodes;
    public List<Edge> edges;

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public int size() {
        return nodes.size();
    }

    public Map<String, Integer> nodeIndexMap() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            map.put(nodes.get(i), i);
        }
        return map;
    }
}
