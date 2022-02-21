package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Builder {
    public static Map<String, Object> toBuildSortedMergedMap(Map<String, Object> data1, Map<String, Object> data2) {

        data2.forEach((key, value) -> {
            if (value == null) {
                data2.put(key, "null");
            }
            if (!data1.containsKey(key)) {
                assert value != null;
                data1.put(key + ":", value.toString());
            }
        });

        Map<String, Object> sortedMergedMap = data1.entrySet().stream()
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


        return sortedMergedMap;
    }

    public static String[][] toBuildListOfDifferences(Map<String, Object> sortedMergedMap, Map<String, Object> data2) {
        String[][] resultArr = new String[sortedMergedMap.size()][3];
        int i = 0;

        for (Map.Entry<String, Object> entry : sortedMergedMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (data2.containsKey(key) && data2.get(key).toString().equals(value.toString())) {
                resultArr[i][0] = key;
                resultArr[i][1] = value.toString();
                resultArr[i][2] = data2.get(key).toString();
            } else if (data2.containsKey(key) && !data2.get(key).toString().equals(value.toString())) {
                resultArr[i][0] = "changed " + key;
                resultArr[i][1] = value.toString();
                resultArr[i][2] = data2.get(key).toString();
            } else if (key.endsWith(":")) {
                resultArr[i][0] = "added " + key.substring(0, key.length() - 1);
                resultArr[i][1] = "";
                resultArr[i][2] = value.toString();
            } else {
                resultArr[i][0] = "removed " + key;
                resultArr[i][1] = value.toString();
                resultArr[i][2] = "";
            }
            i++;
        }

        return resultArr;
    }
}
