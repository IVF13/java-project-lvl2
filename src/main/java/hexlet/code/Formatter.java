package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;

public class Formatter {
    public static Object toChooseFormat(String format, String[][] resultArr)
            throws IOException {
        switch (format) {
            case ("stylish"):
                return StylishFormatter.stylishFormat(resultArr);
            case ("plain"):
                return PlainFormatter.plainFormat(resultArr);
            case ("json"):
                return JsonFormatter.jsonFormat(resultArr);
            default:
                return "format is incorrect\n supported formats:\n - stylish\n - plain";
        }
    }
}
