package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.ArrayList;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String dataType1 = filePath1.substring(filePath1.indexOf("."));
        String dataType2 = filePath2.substring(filePath2.indexOf("."));
        String firstFileContent = Files.readString(Path.of(filePath1));
        String secondFileContent = Files.readString(Path.of(filePath2));
        Map<String, Object> data1 = Parser.toParse(firstFileContent, dataType1);
        Map<String, Object> data2 = Parser.toParse(secondFileContent, dataType2);

        if (data1 == null) {
            data1 = Collections.<String, Object>emptyMap();
        }
        if (data2 == null) {
            data2 = Collections.<String, Object>emptyMap();
        }

        ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences = Builder
                .toBuildListOfDifferences(data1, data2);
        return Formatter.toChooseFormat(format, internalRepresentationOfDifferences);

    }

    public static Object generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

}

