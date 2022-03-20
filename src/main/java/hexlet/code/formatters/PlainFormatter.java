package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlainFormatter {
    public static String plainFormat(ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences) {
        String fieldName = "fieldName";
        String value1 = "value1";
        String value2 = "value2";
        List<String> result = new ArrayList<>();

        internalRepresentationOfDifferences.forEach(x -> {

            if (x.containsValue("changed")) {
                result.add("Property '" + x.get(fieldName) + "' was updated. From "
                        + toCheckComplexValue(x.get(value1).toString()) + " to "
                        + toCheckComplexValue(x.get(value2).toString()) + "\n");
            } else if (x.containsValue("added")) {
                result.add("Property '" + x.get(fieldName) + "' was added with value: "
                        + toCheckComplexValue(x.get(value2).toString()) + "\n");
            } else if (x.containsValue("removed")) {
                result.add("Property '" + x.get(fieldName) + "' was removed" + "\n");
            }
        });

        return result.toString().substring(1, result.toString().length() - 2).replaceAll("\n, ", "\n");
    }

    private static String toCheckComplexValue(String value) {
        if (value.contains("[") || value.contains("{")) {
            return ("[complex value]");
        } else if (!value.contains("true") && !value.contains("null")
                && !value.contains("false") && !isNumeric(value.substring(value.indexOf("=") + 1))) {
            return "'" + value + "'";
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
