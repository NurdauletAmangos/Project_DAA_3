package mst;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MSTAlgorithmsTest {

    @Test
    public void testSmallGraphMST() {
        List<String> nodes = List.of("A", "B", "C", "D");
        List<Edge> edges = List.of(
                new Edge(0, 1, 1),
                new Edge(1, 2, 2),
                new Edge(2, 3, 3),
                new Edge(0, 2, 4),
                new Edge(1, 3, 5)
        );
        Graph g = new Graph(nodes, edges);

        MSTResult prim = PrimAlgorithm.run(g);
        MSTResult kruskal = KruskalAlgorithm.run(g);

        assertEquals(6, prim.total_cost, "Prim total cost must be 6");
        assertEquals(6, kruskal.total_cost, "Kruskal total cost must be 6");
        assertEquals(3, prim.mst_edges.size());
        assertEquals(3, kruskal.mst_edges.size());
    }
}


