package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;

public class DifferTest {
    private String file1JSON = "src/test/resources/file1.json";
    private String file2JSON = "src/test/resources/file2.json";
    private String file1YAML = "src/test/resources/file1.yml";
    private String file2YAML = "src/test/resources/file2.yml";
    private String emptyFileJSON = "src/test/resources/emptyFile.json";
    private String fileResultJson = "src/test/resources/resultFile.json";
    private String file1WthAbsolutePathJSON = new File(file1JSON).getAbsolutePath();
    private String file2WthAbsolutePathJSON = new File(file2JSON).getAbsolutePath();
    private String file1WthAbsolutePathYAML = new File(file1YAML).getAbsolutePath();
    private String file2WthAbsolutePathYAML = new File(file2YAML).getAbsolutePath();
    private String fileResultJsonWthAbsolutePath = new File(fileResultJson).getAbsolutePath();

    private String expectedStylish = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";

    private String expectedPlain = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";


    @Test
    void testStylishDifferRelativePathJSON() throws Exception {
        assertEquals(expectedStylish, Differ.generate(file1JSON, file2JSON, "stylish"));
    }

    @Test
    void testStylishDifferAbsolutePathJSON() throws Exception {
        assertEquals(expectedStylish, Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathJSON, "stylish"));
    }

    @Test
    void testStylishDifferRelativePathYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate(file1YAML, file2YAML, "stylish"));
    }

    @Test
    void testStylishDifferAbsolutePathYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate(file1WthAbsolutePathYAML, file2WthAbsolutePathYAML, "stylish"));
    }

    @Test
    void testStylishDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate(file1JSON, file2YAML, "stylish"));
    }

    @Test
    void testStylishDifferAbsolutePathJSONNYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathYAML, "stylish"));
    }

    @Test
    void testPlainDifferRelativePathJSON() throws Exception {
        assertEquals(expectedPlain, Differ.generate(file1JSON, file2JSON, "plain"));
    }

    @Test
    void testPlainDifferAbsolutePathJSON() throws Exception {
        assertEquals(expectedPlain, Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathJSON, "plain"));
    }

    @Test
    void testPlainDifferRelativePathYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate(file1YAML, file2YAML, "plain"));
    }

    @Test
    void testPlainDifferAbsolutePathYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate(file1WthAbsolutePathYAML, file2WthAbsolutePathYAML, "plain"));
    }

    @Test
    void testPlainDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate(file1JSON, file2YAML, "plain"));
    }

    @Test
    void testPlainDifferAbsolutePathJSONNYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathYAML, "plain"));
    }

    @Test
    void testJsonDifferRelativePathJSON() throws Exception {
        ObjectMapper testMapper = new ObjectMapper();
        String expectedJson = testMapper.readValue(new File(fileResultJson), new TypeReference<>() {
        }).toString();
        String actual = Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathJSON, "json");
        assertEquals(expectedJson, actual);
    }

    @Test
    void testJsonDifferAbsolutePathJSON() throws Exception {
        ObjectMapper testMapper = new ObjectMapper();
        String expectedJson = testMapper.readValue(new File(fileResultJsonWthAbsolutePath), new TypeReference<>() {
        }).toString();
        String actual = Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathJSON, "json");
        assertEquals(expectedJson, actual);
    }


    @Test
    void testIncorrectFormat() throws Exception {
        String incorrect = "format is incorrect\n supported formats:\n - stylish\n - plain";
        assertEquals(incorrect, Differ.generate(file1JSON, file2JSON, "HTML"));
    }

    @Test
    void testStylishSameFilesDifferRelativePathJSON() throws Exception {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "    chars2: [d, e, f]\n"
                + "    checked: false\n"
                + "    default: null\n"
                + "    id: 45\n"
                + "    key1: value1\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "    numbers2: [2, 3, 4, 5]\n"
                + "    numbers3: [3, 4, 5]\n"
                + "    setting1: Some value\n"
                + "    setting2: 200\n"
                + "    setting3: true\n"
                + "}";
        assertEquals(expected, Differ.generate(file1JSON, file1JSON, "stylish"));
    }

    @Test
    void testStylishEmptyFileDifferRelativePathJSON() throws Exception {
        String expected = "{\n"
                + "  - chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  - checked: false\n"
                + "  - default: null\n"
                + "  - id: 45\n"
                + "  - key1: value1\n"
                + "  - numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  - setting1: Some value\n"
                + "  - setting2: 200\n"
                + "  - setting3: true\n"
                + "}";

        assertEquals(expected, Differ.generate(file1JSON, emptyFileJSON, "stylish"));
    }

    @Test
    void testPlainEmptyFileDifferJSON() throws Exception {
        String expected = "Property 'chars1' was removed\n"
                + "Property 'chars2' was removed\n"
                + "Property 'checked' was removed\n"
                + "Property 'default' was removed\n"
                + "Property 'id' was removed\n"
                + "Property 'key1' was removed\n"
                + "Property 'numbers1' was removed\n"
                + "Property 'numbers2' was removed\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'setting1' was removed\n"
                + "Property 'setting2' was removed\n"
                + "Property 'setting3' was removed";
        assertEquals(expected, Differ.generate(file1JSON, emptyFileJSON, "plain"));
    }


}

