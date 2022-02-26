package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Builder {
    public static TreeMap<String, List> toBuildListOfDifferences(Map<String, Object> data1, Map<String, Object> data2) {

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

        TreeMap<String, List> internalRepresentationOfDifferences = new TreeMap<>();
        List<HashMap<String, Object>> listOfValuesWthNoChanges = new ArrayList<>();
        List<HashMap<String, Object>> listOfValuesWthChanges = new ArrayList<>();
        List<HashMap<String, Object>> listOfAddedValues = new ArrayList<>();
        List<HashMap<String, Object>> listOfRemovedValues = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (data2.containsKey(key) && data2.get(key).toString().equals(value.toString())) {
                listOfValuesWthNoChanges.add(new HashMap<>(Map.of(key, value)));
            } else if (data2.containsKey(key) && !data2.get(key).toString().equals(value.toString())) {
                listOfValuesWthChanges.add(new HashMap<>(Map.of(key, value)));
                listOfValuesWthChanges.add(new HashMap<>(Map.of(key, data2.get(key))));
            } else if (key.endsWith(":")) {
                listOfAddedValues.add(new HashMap<>(Map.of(key.substring(0, key.length() - 1), value)));
            } else {
                listOfRemovedValues.add(new HashMap<>(Map.of(key, value)));
            }

        }

        internalRepresentationOfDifferences.put("changed", listOfValuesWthChanges);
        internalRepresentationOfDifferences.put("withoutChanges", listOfValuesWthNoChanges);
        internalRepresentationOfDifferences.put("added", listOfAddedValues);
        internalRepresentationOfDifferences.put("removed", listOfRemovedValues);

        return internalRepresentationOfDifferences;
    }
}
