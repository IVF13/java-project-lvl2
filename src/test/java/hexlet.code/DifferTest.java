package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;

public class DifferTest {
    private File file1JSON = new File("src/test/resources/file1.json");
    private File file2JSON = new File("src/test/resources/file2.json");
    private File file1YAML = new File("src/test/resources/file1.yml");
    private File file2YAML = new File("src/test/resources/file2.yml");
    private File file1WthAbsolutePathJSON = new File(file1JSON.getAbsolutePath());
    private File file2WthAbsolutePathJSON = new File(file2JSON.getAbsolutePath());
    private File file1WthAbsolutePathYAML = new File(file1YAML.getAbsolutePath());
    private File file2WthAbsolutePathYAML = new File(file2YAML.getAbsolutePath());

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

    private String expectedJson = "{chars1=not changed: [a, b, c],"
            + " chars2=was changed from: [d, e, f] to: false,"
            + " checked=was changed from: false to: true,"
            + " default=was changed from: null to: [value1, value2],"
            + " id=was changed from: 45 to: null, key1=was removed: value1,"
            + " key2=was added: value2, numbers1=not changed: [1, 2, 3, 4],"
            + " numbers2=was changed from: [2, 3, 4, 5] to: [22, 33, 44, 55],"
            + " numbers3=was removed: [3, 4, 5], numbers4=was added: [4, 5, 6],"
            + " obj1=was added: {nestedKey=value, isNested=true},"
            + " setting1=was changed from: Some value to: Another value,"
            + " setting2=was changed from: 200 to: 300,"
            + " setting3=was changed from: true to: none}";

    @Test
    void testStylishDifferRelativePathJSON() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1JSON, file2JSON));
    }

    @Test
    void testStylishDifferAbsolutePathJSON() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1WthAbsolutePathJSON, file2WthAbsolutePathJSON));
    }

    @Test
    void testStylishDifferRelativePathYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1YAML, file2YAML));
    }

    @Test
    void testStylishDifferAbsolutePathYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1WthAbsolutePathYAML, file2WthAbsolutePathYAML));
    }

    @Test
    void testStylishDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1JSON, file2YAML));
    }

    @Test
    void testStylishDifferAbsolutePathJSONNYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1WthAbsolutePathJSON, file2WthAbsolutePathYAML));
    }

    @Test
    void testPlainDifferRelativePathJSON() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1JSON, file2JSON));
    }

    @Test
    void testPlainDifferAbsolutePathJSON() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1WthAbsolutePathJSON, file2WthAbsolutePathJSON));
    }

    @Test
    void testPlainDifferRelativePathYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1YAML, file2YAML));
    }

    @Test
    void testPlainDifferAbsolutePathYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1WthAbsolutePathYAML, file2WthAbsolutePathYAML));
    }

    @Test
    void testPlainDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1JSON, file2YAML));
    }

    @Test
    void testPlainDifferAbsolutePathJSONNYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1WthAbsolutePathJSON, file2WthAbsolutePathYAML));
    }

    @Test
    void testJsonDifferRelativePathJSON() throws Exception {
        File result = new File("result.json");
        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals("File was successfully generated.", Differ.generate("json", file1JSON, file2JSON));
        String actual = objectMapper.readValue(result, new TypeReference<>() {
        }).toString();
        assertEquals(expectedJson, actual);
    }

    @Test
    void testJsonDifferAbsolutePathJSON() throws Exception {
        File result = new File("result.json");
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedString = "File was successfully generated.";
        assertEquals(expectedString, Differ.generate("json", file1WthAbsolutePathJSON, file2WthAbsolutePathJSON));
        String actual = objectMapper.readValue(result, new TypeReference<>() {
        }).toString();
        assertEquals(expectedJson, actual);
    }


    @Test
    void testIncorrectFormat() throws Exception {
        String incorrect = "format is incorrect\n supported formats:\n - stylish\n - plain";
        assertEquals(incorrect, Differ.generate("HTML", file1JSON, file2JSON));
    }

    @Test
    void testSameFiles() throws Exception {
        String identical = "You are trying to compare identical files.";
        assertEquals(identical, Differ.generate("stylish", file1JSON, file1JSON));
        assertEquals(identical, Differ.generate("plain", file1JSON, file1JSON));
    }

    @Test
    void testDifferEmptyFile() throws Exception {
        File emptyFile = new File("src/test/resources/emptyfile1.json");
        assertEquals("One of the files, or both of them, are empty", Differ.generate("stylish", emptyFile, file2JSON));
    }

}

