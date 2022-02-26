package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class PlainFormatter {
    public static String plainFormat(TreeMap<String, List> internalRepresentationOfDifferences) {
        List<StringBuilder> result = new ArrayList<>();
        for (Map.Entry<String, List> entry : internalRepresentationOfDifferences.entrySet()) {
            String key = entry.getKey();
            List value = entry.getValue();

            for (int i = 0; i < value.size() - 1; i += 2) {
                if (key.equals("changed")) {
                    result.add(new StringBuilder("Property '"
                            + value.get(i).toString().substring(1, value.get(i).toString().indexOf("="))
                            + toCheckComplexValue(value.get(i).toString().substring(value.get(i)
                            .toString().indexOf("="), value.get(i).toString().length() - 1))
                            .replaceFirst("=", "' was updated. From ")
                            + toCheckComplexValue(value.get(i + 1).toString().substring(value.get(i + 1)
                            .toString().indexOf("="), value.get(i + 1).toString().length() - 1))
                            .replaceFirst("=", " to ") + "\n"));
                }
            }
            for (int i = 0; i < value.size(); i++) {
                if (key.equals("added")) {
                    result.add(new StringBuilder("Property '" + value.get(i).toString()
                            .substring(1, value.get(i).toString().indexOf("="))
                            + toCheckComplexValue(value.get(i).toString().substring(value.get(i)
                            .toString().indexOf("="), value.get(i).toString().length() - 1))
                            .replaceFirst("=", "' was added with value: ") + "\n"));
                } else if (key.equals("removed")) {
                    result.add(new StringBuilder("Property '" + value.get(i).toString()
                            .substring(1, value.get(i).toString().indexOf("=") + 1)
                            .replaceFirst("=", "' was removed") + "\n"));
                }
            }
        }

        Collections.sort(result, Comparator.comparing(s -> s.substring(3)));

        return result.toString().substring(1, result.toString().length() - 2).replaceAll("\n, ", "\n");
    }

    private static String toCheckComplexValue(String value) {
        if (value.contains("[") || value.contains("{")) {
            return ("=[complex value]");
        } else if (!value.contains("true") && !value.contains("null")
                && !value.contains("false") && !isNumeric(value.substring(value.indexOf("=") + 1))) {
            return value.substring(0, value.indexOf("=") + 1) + "'" + value.substring(value.indexOf("="))
                    .replaceFirst("=", "") + "'";
        } else {
            return value;
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
