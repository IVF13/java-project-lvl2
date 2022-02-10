package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Differ {
    public static String generate(File file1, File file2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data1 = mapper.readValue(file1, Map.class);
        Map<String, Object> data2 = mapper.readValue(file2, Map.class);
        StringBuilder result = new StringBuilder("{\n");

        data2.forEach((key, value) -> {
            if (!data1.containsKey(key)) {
                data1.put(key + ":", value);
            }
        });

        Map<String, Object> data1Sorted = data1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        data1.clear();

        data1Sorted.forEach((key, value) -> {
            if (data2.containsKey(key) && data2.get(key).equals(value)) {
                result.append("    " + key + ": " + value + "\n");
            } else if (data2.containsKey(key) && !data2.get(key).equals(value)) {
                result.append("  - " + key + ": " + value + "\n");
                result.append("  + " + key + ": " + data2.get(key) + "\n");
            } else if (key.endsWith(":")) {
                result.append("  + " + key + " " + value + "\n");
            } else {
                result.append("  - " + key + ": " + value + "\n");
            }
        });
        result.append("}");

        return result.toString();
    }
}
