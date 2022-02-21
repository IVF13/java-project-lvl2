package hexlet.code;

import java.util.Map;

public class Differ {
    public static Object generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.toParse(filePath1);
        Map<String, Object> data2 = Parser.toParse(filePath2);

        if (!isCollectionMapNull(data1) && !isCollectionMapNull(data2)) {
            Map<String, Object> sortedMergedMap = Builder.toBuildSortedMergedMap(data1, data2);
            String[][] resultArr = Builder.toBuildListOfDifferences(sortedMergedMap, data2);
            return Formatter.toChooseFormat(format, resultArr);
        }

        return null;
    }

    protected static boolean isCollectionMapNull(final Map<?, ?> m) {
        return m == null;
    }

    public static Object generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

}

