package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Builder {
    public static ArrayList<HashMap<String, Object>>
        toBuildListOfDifferences(Map<String, Object> data1, Map<String, Object> data2) {
        ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>();

        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        keys.forEach(key -> {

            Object value1 = Utils.toGetValueFromMap(data1, key);
            Object value2 = Utils.toGetValueFromMap(data2, key);

            if (data2.containsKey(key) && data1.containsKey(key)) {
                if (value2.toString().equals(value1.toString())) {
                    internalRepresentationOfDifferences.add(
                            Utils.toCreateMapWthValueStateChanges(key, value1, value2, "not changed"));
                } else {
                    internalRepresentationOfDifferences.add(
                            Utils.toCreateMapWthValueStateChanges(key, value1, value2, "changed"));
                }
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                internalRepresentationOfDifferences.add(new HashMap<>(
                        Utils.toCreateMapWthValueStateChanges(key, "", value2, "added")));
            } else {
                internalRepresentationOfDifferences.add(
                        Utils.toCreateMapWthValueStateChanges(key, value1, "", "removed"));
            }

        });

        return internalRepresentationOfDifferences;

    }

    static class Utils {

        public static Object toGetValueFromMap(Map<String, Object> data, String key) {

            if (data.get(key) == null) {
                return "null";
            } else {
                return data.get(key);
            }

        }

        public static HashMap<String, Object>
            toCreateMapWthValueStateChanges(String key, Object value1, Object value2, String status) {
            return new HashMap<>(Map.of("fieldName", key,
                    "value1", value1, "value2", value2, "status", status));
        }

    }

}

