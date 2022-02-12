package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Engine {
    public static String toCompare(Map<String, Object> data1, Map<String, Object> data2) {
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

        if (!result.toString().contains("+") || !result.toString().contains("-")) {
            return "You are trying to compare identical files.";
        }
        return result.toString();
    }
}
