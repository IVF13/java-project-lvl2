package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StylishFormatter {
    public static String stylishFormat(ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences) {
        String fieldName = "fieldName";
        String value1 = "value1";
        String value2 = "value2";

        List<String> result = new ArrayList<>();

        internalRepresentationOfDifferences.forEach(x -> {
            if (x.containsValue("changed")) {
                result.add("  - " + x.get(fieldName) + ": " + x.get(value1) + "\n" + "  + "
                        + x.get(fieldName) + ": " + x.get(value2) + "\n");
            } else if (x.containsValue("not changed")) {
                result.add("    " + x.get(fieldName) + ": " + x.get(value1) + "\n");
            } else if (x.containsValue("added")) {
                result.add("  + " + x.get(fieldName) + ": " + x.get(value2) + "\n");
            } else if (x.containsValue("removed")) {
                result.add("  - " + x.get(fieldName) + ": " + x.get(value1) + "\n");
            }

        });

        result.add(0, "{\n");
        result.add("}");

        return result.toString().substring(1, result.toString().length() - 1).replaceAll("\n, ", "\n");
    }
}
