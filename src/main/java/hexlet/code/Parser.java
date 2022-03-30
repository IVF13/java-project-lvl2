package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> toParse(String content, String dataType) throws IOException {
        if (dataType.equals(".json")) {
            ObjectMapper mapperJSON = new ObjectMapper();
            return mapperJSON.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } else if (dataType.equals(".yml")) {
            ObjectMapper mapperYAML = new ObjectMapper(new YAMLFactory());
            return mapperYAML.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } else {
            return null;
        }
    }

}
