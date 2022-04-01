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
        return switch (format) {
            case ("stylish") -> StylishFormatter.stylishFormat(internalRepresentationOfDifferences);
            case ("plain") -> PlainFormatter.plainFormat(internalRepresentationOfDifferences);
            case ("json") -> JsonFormatter.jsonFormat(internalRepresentationOfDifferences);
            default -> "format is incorrect\n supported formats:\n - stylish\n - plain\n - json";
        };
    }
}
