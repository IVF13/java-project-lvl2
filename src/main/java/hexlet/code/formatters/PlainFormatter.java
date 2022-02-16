package hexlet.code.formatters;

import java.util.Map;

public class PlainFormatter {
    public static String plainFormat(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder();

        data1.forEach((key, value) -> {
            if (data2.containsKey(key) && !data2.get(key).toString().equals(value.toString())) {
                result.append("Property '" + key + "' was updated. From ");
                result.append(toCheckComplexValue(value));
                result.append(" to ");
                result.append(toCheckComplexValue(data2.get(key)));
                result.append("\n");
            } else if (key.endsWith(":")) {
                result.append("Property '" + key);
                result.deleteCharAt(result.length() - 1);
                result.append("' was added with value: ");
                result.append(toCheckComplexValue(value.toString()));
                result.append("\n");
            } else if (!data2.containsKey(key)) {
                result.append("Property '" + key + "' was removed\n");
            }
        });
        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    private static String toCheckComplexValue(Object value) {
        if (value.toString().startsWith("[") || value.toString().startsWith("{")) {
            return ("[complex value]");
        } else if (value instanceof String && !value.equals("null")) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
