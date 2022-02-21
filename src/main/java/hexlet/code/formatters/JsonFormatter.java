package hexlet.code.formatters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFormatter {
    public static Object jsonFormat(String[][] resultArr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/test/resources/resultFile.json"), resultArr);
        System.out.println("File was successfully generated.");
        return objectMapper.readValue(new File("src/test/resources/resultFile.json"), new TypeReference<>() {
        });
    }
}
