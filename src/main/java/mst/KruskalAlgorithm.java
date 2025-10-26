package mst;

import java.util.*;

public class KruskalAlgorithm {

    public static MSTResult run(Graph graph) {
        int n = graph.size();
        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);

        UnionFind uf = new UnionFind(n);
        List<MSTEdgeResult> mstEdges = new ArrayList<>();
        int totalCost = 0;
        long operations = 0;

        long start = System.nanoTime();

        for (Edge e : edges) {
            operations++;
            int ru = uf.find(e.u);
            int rv = uf.find(e.v);
            if (ru != rv) {
                if (uf.union(ru, rv)) {
                    mstEdges.add(new MSTEdgeResult(
                            graph.nodes.get(e.u),
                            graph.nodes.get(e.v),
                            e.w
                    ));
                    totalCost += e.w;
                }
            }
            if (mstEdges.size() == n - 1) break;
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        operations += uf.findCalls + uf.unionCalls;

        return new MSTResult(mstEdges, totalCost, operations, timeMs);
    }
}
