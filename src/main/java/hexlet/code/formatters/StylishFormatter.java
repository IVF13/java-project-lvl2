package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class StylishFormatter {
    public static String stylishFormat(TreeMap<String, List> internalRepresentationOfDifferences) {
        List<StringBuilder> result = new ArrayList<>();
        for (Map.Entry<String, List> entry : internalRepresentationOfDifferences.entrySet()) {
            String key = entry.getKey();
            List value = entry.getValue();

            for (int i = 0; i < value.size() - 1; i += 2) {
                if (key.equals("changed")) {
                    result.add(new StringBuilder("  - " + value.get(i).toString()
                            .substring(1, value.get(i).toString().length() - 1)
                            .replaceFirst("=", ": ") + "\n" + "  + "
                            + value.get(i + 1).toString()
                            .substring(1, value.get(i + 1).toString().length() - 1)
                            .replaceFirst("=", ": ") + "\n"));
                }
            }
            for (int i = 0; i < value.size(); i++) {
                if (key.equals("withoutChanges")) {
                    result.add(new StringBuilder("    " + value.get(i).toString()
                            .substring(1, value.get(i).toString().length() - 1)
                            .replaceFirst("=", ": ") + "\n"));
                } else if (key.equals("added")) {
                    result.add(new StringBuilder("  + " + value.get(i).toString()
                            .substring(1, value.get(i).toString().length() - 1)
                            .replaceFirst("=", ": ") + "\n"));
                } else if (key.equals("removed")) {
                    result.add(new StringBuilder("  - " + value.get(i).toString()
                            .substring(1, value.get(i).toString().length() - 1)
                            .replaceFirst("=", ": ") + "\n"));
                }
            }
        }

        Collections.sort(result, Comparator.comparing(s -> s.substring(3)));

        result.add(0, new StringBuilder("{\n"));
        result.add(new StringBuilder("}"));

        return result.toString().substring(1, result.toString().length() - 1).replaceAll("\n, ", "\n");
    }
}
