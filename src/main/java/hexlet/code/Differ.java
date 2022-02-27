package hexlet.code;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String dataType1 = filePath1.substring(filePath1.indexOf("."));
        String dataType2 = filePath2.substring(filePath2.indexOf("."));
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        Map<String, Object> data1 = Parser.toParse(file1, dataType1);
        Map<String, Object> data2 = Parser.toParse(file2, dataType2);

        if (data1 == null) {
            data1 = Collections.<String, Object>emptyMap();
        }
        if (data2 == null) {
            data2 = Collections.<String, Object>emptyMap();
        }

        TreeMap<String, List> internalRepresentationOfDifferences = Builder.toBuildListOfDifferences(data1, data2);
        return Formatter.toChooseFormat(format, internalRepresentationOfDifferences);

    }

    public static Object generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

}

