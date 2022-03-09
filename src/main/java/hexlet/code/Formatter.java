package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Formatter {
    public static String
        toChooseFormat(String format, ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences)
            throws IOException {
        switch (format) {
            case ("stylish"):
                return StylishFormatter.stylishFormat(internalRepresentationOfDifferences);
            case ("plain"):
                return PlainFormatter.plainFormat(internalRepresentationOfDifferences);
            case ("json"):
                return JsonFormatter.jsonFormat(internalRepresentationOfDifferences);
            default:
                return "format is incorrect\n supported formats:\n - stylish\n - plain";
        }
    }
}
