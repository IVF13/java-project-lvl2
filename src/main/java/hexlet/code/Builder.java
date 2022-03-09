package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Builder {
    public static ArrayList<HashMap<String, Object>>
        toBuildListOfDifferences(Map<String, Object> data1, Map<String, Object> data2) {

        data2.forEach((key, value) -> {
            if (value == null) {
                data2.put(key, "null");
            }
            if (!data1.containsKey(key)) {
                assert value != null;
                data1.put(key + ":", value.toString());
            }
        });

        data1.forEach((key, value) -> {
            if (value == null) {
                data1.put(key, "null");
            }
        });

        ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (data2.containsKey(key) && data2.get(key).toString().equals(value.toString())) {
                internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                        "value1", value, "value2", value, "status", "not changed")));
            } else if (data2.containsKey(key) && !data2.get(key).toString().equals(value.toString())) {
                internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                        "value1", value, "value2", data2.get(key), "status", "changed")));
            } else if (key.endsWith(":")) {
                internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName",
                        key.substring(0, key.length() - 1),
                        "value1", "", "value2", value, "status", "added")));
            } else {
                internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                        "value1", value, "value2", "", "status", "removed")));
            }

        }

        return internalRepresentationOfDifferences;

    }
}
