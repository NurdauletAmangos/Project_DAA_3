package mst;

import java.util.List;

public class MSTResult {
    public List<MSTEdgeResult> mst_edges;
    public int total_cost;
    public long operations_count;
    public double execution_time_ms;

    public MSTResult(List<MSTEdgeResult> mst_edges, int total_cost, long operations_count, double execution_time_ms) {
        this.mst_edges = mst_edges;
        this.total_cost = total_cost;
        this.operations_count = operations_count;
        this.execution_time_ms = Math.round(execution_time_ms * 1_000_000d) / 1_000_000d;
    }
}
