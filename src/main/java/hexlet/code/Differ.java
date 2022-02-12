package hexlet.code;

import java.io.File;
import java.util.Map;


public class Differ {
    public static String generate(String format, File file1, File file2) throws Exception {
        Map<String, Object> data1JSON = Parser.toParse(format, file1);
        Map<String, Object> data2JSON = Parser.toParse(format, file2);
        if (data1JSON != null && data2JSON != null) {
            return Engine.toCompare(data1JSON, data2JSON);
        }
        return "format is incorrect\n supported formats:\n - JSON\n - YAML";

    }
}

