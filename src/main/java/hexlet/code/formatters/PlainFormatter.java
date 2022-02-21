package hexlet.code.formatters;

public class PlainFormatter {
    public static String plainFormat(String[][] resultArr) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[i][0].startsWith("changed")) {
                result.append("Property '" + resultArr[i][0].substring(8) + "' was updated. From ");
                result.append(toCheckComplexValue(resultArr[i][1]));
                result.append(" to ");
                result.append(toCheckComplexValue(resultArr[i][2]));
                result.append("\n");
            } else if (resultArr[i][0].startsWith("added")) {
                result.append("Property '" + resultArr[i][0].substring(6));
                result.append("' was added with value: ");
                result.append(toCheckComplexValue(resultArr[i][2]));
                result.append("\n");
            } else if (resultArr[i][0].startsWith("removed")) {
                result.append("Property '" + resultArr[i][0].substring(8) + "' was removed\n");
            }
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private static String toCheckComplexValue(Object value) {
        if (value.toString().startsWith("[") || value.toString().startsWith("{")) {
            return ("[complex value]");
        } else if (!value.equals("true") && !value.equals("null")
                && !value.equals("false") && !isNumeric(value.toString())) {
            return "'" + value + "'";
        } else {
            return value.toString();
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

