package hexlet.code;

import java.io.File;
import java.util.Map;


public class Differ {
    public static String generate(String format, File file1, File file2) throws Exception {
        Map<String, Object> data1JSON = Parser.toParse(file1);
        Map<String, Object> data2JSON = Parser.toParse(file2);
        if (data1JSON != null && data2JSON != null) {
            return Engine.toCompare(format, data1JSON, data2JSON);
        }
        return "";
    }
}

