package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> toParse(String format, File file) throws IOException {
        switch (format) {
            case ("JSON"):
                ObjectMapper mapperJSON = new ObjectMapper();
                return mapperJSON.readValue(file, new TypeReference<>() {
                });
            case ("YAML"):
                ObjectMapper mapperYAML = new ObjectMapper(new YAMLFactory());
                return mapperYAML.readValue(file, new TypeReference<>() {
                });
            default:
                return null;
        }
    }
}
