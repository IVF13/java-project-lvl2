package hexlet.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Builder {
    public static ArrayList<HashMap<String, Object>>
        toBuildListOfDifferences(Map<String, Object> data1, Map<String, Object> data2) {
        ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>();

        data2.forEach((key, value) -> keys.add(key));
        data1.forEach((key, value) -> keys.add(key));

        for (String key : keys) {
            Object value1;
            Object value2;

            if (data1.get(key) == null) {
                value1 = "null";
            } else {
                value1 = data1.get(key);
            }

            if (data2.get(key) == null) {
                value2 = "null";
            } else {
                value2 = data2.get(key);
            }

            if (data2.containsKey(key) && data1.containsKey(key)) {
                if (value2.toString().equals(value1.toString())) {
                    internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                            "value1", value1, "value2", value2, "status", "not changed")));
                } else {
                    internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                            "value1", value1, "value2", value2, "status", "changed")));
                }
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                        "value1", "", "value2", value2, "status", "added")));
            } else {
                internalRepresentationOfDifferences.add(new HashMap<>(Map.of("fieldName", key,
                        "value1", value1, "value2", "", "status", "removed")));
            }

        }

        internalRepresentationOfDifferences.sort(Comparator.comparing(s -> s.get("fieldName").toString()));

        return internalRepresentationOfDifferences;

    }
}

