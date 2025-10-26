package mst;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ResultWriter {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeResults(String outputPath, List<Map<String, Object>> results) throws IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("results", results);
        try (FileWriter writer = new FileWriter(outputPath)) {
            gson.toJson(root, writer);
        }
    }
}


