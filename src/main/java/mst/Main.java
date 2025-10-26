package mst;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import com.google.gson.reflect.TypeToken;

public class Main {
    private static final String INPUT_PATH = "ass_3_input.json";
    private static final String OUTPUT_PATH = "ass_3_output_generated.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        try {
            String json = Files.readString(Path.of(INPUT_PATH));
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonArray graphs = root.getAsJsonArray("graphs");

            List<Map<String, Object>> outputResults = new ArrayList<>();

            for (JsonElement gEl : graphs) {
                JsonObject gObj = gEl.getAsJsonObject();
                int id = gObj.get("id").getAsInt();

                Type nodeType = new TypeToken<List<String>>() {}.getType();
                List<String> nodes = gson.fromJson(gObj.get("nodes"), nodeType);

                Type edgeType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                List<Map<String, Object>> edgesRaw = gson.fromJson(gObj.get("edges"), edgeType);

                List<Edge> edges = new ArrayList<>();
                Map<String, Integer> idx = new HashMap<>();
                for (int i = 0; i < nodes.size(); i++) idx.put(nodes.get(i), i);

                for (Map<String, Object> e : edgesRaw) {
                    int u = idx.get((String) e.get("from"));
                    int v = idx.get((String) e.get("to"));
                    int w = ((Number) e.get("weight")).intValue();
                    edges.add(new Edge(u, v, w));
                }

                Graph graph = new Graph(nodes, edges);

                MSTResult primRes = PrimAlgorithm.run(graph);
                MSTResult kruskalRes = KruskalAlgorithm.run(graph);

                Map<String, Object> resEntry = new LinkedHashMap<>();
                resEntry.put("graph_id", id);
                resEntry.put("input_stats", Map.of("vertices", nodes.size(), "edges", edges.size()));
                resEntry.put("prim", primRes);
                resEntry.put("kruskal", kruskalRes);

                outputResults.add(resEntry);
            }

            ResultWriter.writeResults(OUTPUT_PATH, outputResults);
            System.out.println("✅ Results written to " + OUTPUT_PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




