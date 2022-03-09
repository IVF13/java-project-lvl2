package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class JsonFormatter {
    public static String jsonFormat(ArrayList<HashMap<String, Object>> internalRepresentationOfDifferences)
        throws IOException {
        StringBuilder json = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();

        json.append(objectMapper.writeValueAsString(internalRepresentationOfDifferences));

        return json.toString();
    }
}
