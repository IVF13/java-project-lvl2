package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Engine {
    public static String toCompare(String format, Map<String, Object> data1, Map<String, Object> data2) {

        data2.forEach((key, value) -> {
            if (value == null) {
                data2.put(key, "null");
            }
            if (!data1.containsKey(key)) {
                assert value != null;
                data1.put(key + ":", value.toString());
            }
        });

        Map<String, Object> data1Sorted = data1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .peek(x -> {
                    if (x.getValue() == null) {
                        data1.put(x.getKey(), "null");
                    }
                })
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        data1.clear();

        if (format.equals("stylish")) {
            return stylishFormatter(data1Sorted, data2);
        }

        return "format is incorrect\n supported formats:\n - stylish\n";
    }

    public static String stylishFormatter(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder("{\n");
        data1.forEach((key, value) -> {
            if (data2.containsKey(key) && data2.get(key).toString().equals(value.toString())) {
                result.append("    " + key + ": " + value.toString() + "\n");
            } else if (data2.containsKey(key) && !data2.get(key).toString().equals(value.toString())) {
                result.append("  - " + key + ": " + value.toString() + "\n");
                result.append("  + " + key + ": " + data2.get(key).toString() + "\n");
            } else if (key.endsWith(":")) {
                result.append("  + " + key + " " + value.toString() + "\n");
            } else {
                result.append("  - " + key + ": " + value.toString() + "\n");
            }
        });
        result.append("}");

        if (!result.toString().contains("+") || !result.toString().contains("-")) {
            return "You are trying to compare identical files.";
        }

        return result.toString();
    }
}
