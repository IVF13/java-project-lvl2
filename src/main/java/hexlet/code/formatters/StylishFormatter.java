package hexlet.code.formatters;

import java.util.Map;

public class StylishFormatter {
    public static String stylishFormat(Map<String, Object> data1, Map<String, Object> data2) {
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

        return result.toString();
    }
}
