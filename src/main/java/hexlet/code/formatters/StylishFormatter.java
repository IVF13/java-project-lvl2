package hexlet.code.formatters;


public class StylishFormatter {
    public static String stylishFormat(String[][] resultArr) {
        StringBuilder result = new StringBuilder("{\n");

        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[i][0].startsWith("changed")) {
                result.append("  - " + resultArr[i][0].substring(8) + ": " + resultArr[i][1] + "\n");
                result.append("  + " + resultArr[i][0].substring(8) + ": " + resultArr[i][2] + "\n");
            } else if (resultArr[i][0].startsWith("added")) {
                result.append("  + " + resultArr[i][0].substring(6) + ": " + resultArr[i][2] + "\n");
            } else if (resultArr[i][0].startsWith("removed")) {
                result.append("  - " + resultArr[i][0].substring(8) + ": " + resultArr[i][1] + "\n");
            } else {
                result.append("    " + resultArr[i][0] + ": " + resultArr[i][1] + "\n");
            }
        }

        result.append("}");

        return result.toString();
    }
}
