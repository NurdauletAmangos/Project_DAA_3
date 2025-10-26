package mst;

import java.util.*;

public class PrimAlgorithm {

    public static MSTResult run(Graph graph) {
        int n = graph.size();
        Map<String, Integer> idx = graph.nodeIndexMap();

        // adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (Edge e : graph.edges) {
            adj.get(e.u).add(new int[]{e.v, e.w});
            adj.get(e.v).add(new int[]{e.u, e.w});
        }

        boolean[] inMst = new boolean[n];
        int[] parent = new int[n];
        int[] key = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        long operations = 0;
        key[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, 0});

        long start = System.nanoTime();

        while (!pq.isEmpty()) {
            int[] kv = pq.poll();
            int u = kv[1];
            if (inMst[u]) continue;
            inMst[u] = true;

            for (int[] vw : adj.get(u)) {
                operations++;
                int v = vw[0], w = vw[1];
                if (!inMst[v] && w < key[v]) {
                    key[v] = w;
                    parent[v] = u;
                    pq.add(new int[]{w, v});
                }
            }
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;

        List<MSTEdgeResult> edges = new ArrayList<>();
        int totalCost = 0;
        for (int v = 0; v < n; v++) {
            if (parent[v] != -1) {
                edges.add(new MSTEdgeResult(graph.nodes.get(parent[v]), graph.nodes.get(v), key[v]));
                totalCost += key[v];
            }
        }

        return new MSTResult(edges, totalCost, operations, timeMs);
    }
}
