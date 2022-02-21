package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> toParse(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.toString().endsWith("json")) {
            ObjectMapper mapperJSON = new ObjectMapper();
            return mapperJSON.readValue(file, new TypeReference<>() {
            });
        } else if (file.toString().endsWith("yml")) {
            ObjectMapper mapperYAML = new ObjectMapper(new YAMLFactory());
            return mapperYAML.readValue(file, new TypeReference<>() {
            });
        } else {
            return null;
        }
    }
}
