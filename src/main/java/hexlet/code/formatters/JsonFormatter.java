package hexlet.code.formatters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonFormatter {
    public static Object jsonFormat(Map<String, Object> data1, Map<String, Object> data2) throws IOException {
        Map<String, Object> finalData = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        data1.forEach((key, value) -> {
            if (data2.containsKey(key) && data2.get(key).toString().equals(value.toString())) {
                finalData.put(key, "not changed: " + value);
            } else if (data2.containsKey(key) && !data2.get(key).toString().equals(value.toString())) {
                finalData.put(key, "was changed from: " + value + " to: " + data2.get(key).toString());
            } else if (key.endsWith(":")) {
                StringBuilder keyString = new StringBuilder(key);
                keyString.deleteCharAt(keyString.length() - 1);
                finalData.put(keyString.toString(), "was added: " + value);
            } else if (!data2.containsKey(key)) {
                finalData.put(key, "was removed: " + value);
            }
        });
        objectMapper.writeValue(new File("result.json"), finalData);

        System.out.println("File was successfully generated.");

        return objectMapper.readValue(new File("result.json"), new TypeReference<>() {
        });
    }
}
