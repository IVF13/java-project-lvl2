package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {
    public static String toChooseFormat(String format, Map<String, Object> data1, Map<String, Object> data2) {
        switch (format) {
            case ("stylish"):
                return StylishFormatter.stylishFormat(data1, data2);
            case ("plain"):
                return PlainFormatter.plainFormat(data1, data2);
            default:
                return "format is incorrect\n supported formats:\n - stylish\n - plain";
        }
    }
}
