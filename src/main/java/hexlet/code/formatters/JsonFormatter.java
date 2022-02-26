package hexlet.code.formatters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class JsonFormatter {
    public static String jsonFormat(TreeMap<String, List> internalRepresentationOfDifferences) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/test/resources/resultFile.json"), internalRepresentationOfDifferences);
        System.out.println("File was successfully generated.");
        return objectMapper.readValue(new File("src/test/resources/resultFile.json"), new TypeReference<>() {
        }).toString();
    }
}
