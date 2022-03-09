package hexlet.code.formatters;

import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class StylishFormatter {
    public static String stylishFormat(ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences) {
        String fieldName = "fieldName";
        String value1 = "value1";
        String value2 = "value2";

        List<StringBuilder> result = new ArrayList<>();

        internalRepresentationOfDifferences.forEach(x -> {
            if (x.containsValue("changed")) {
                result.add(new StringBuilder("  - " + x.get(fieldName) + ": " + x.get(value1) + "\n" + "  + "
                        + x.get(fieldName) + ": " + x.get(value2) + "\n"));
            } else if (x.containsValue("not changed")) {
                result.add(new StringBuilder("    " + x.get(fieldName) + ": " + x.get(value1) + "\n"));
            } else if (x.containsValue("added")) {
                result.add(new StringBuilder("  + " + x.get(fieldName) + ": " + x.get(value2) + "\n"));
            } else if (x.containsValue("removed")) {
                result.add(new StringBuilder("  - " + x.get(fieldName) + ": " + x.get(value1) + "\n"));
            }

        });

        Collections.sort(result, Comparator.comparing(s -> s.substring(3)));

        result.add(0, new StringBuilder("{\n"));
        result.add(new StringBuilder("}"));

        return result.toString().substring(1, result.toString().length() - 1).replaceAll("\n, ", "\n");
    }
}
