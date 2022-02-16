package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;

public class DifferTest {
    private File file1JSON = new File("src/test/resources/file1.json");
    private File file2JSON = new File("src/test/resources/file2.json");
    private File file1YAML = new File("src/test/resources/file1.yml");
    private File file2YAML = new File("src/test/resources/file2.yml");
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
        assertEquals(expectedStylish, Differ.generate("stylish", file1JSON, file2JSON));
    }

    @Test
    void testStylishDifferAbsolutePathJSON() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2JSON.getAbsolutePath());
        assertEquals(expectedStylish, Differ.generate("stylish", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testStylishDifferRelativePathYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1YAML, file2YAML));
    }

    @Test
    void testStylishDifferAbsolutePathYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1YAML.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expectedStylish, Differ.generate("stylish", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testStylishDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expectedStylish, Differ.generate("stylish", file1JSON, file2YAML));
    }

    @Test
    void testStylishDifferAbsolutePathJSONNYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expectedStylish, Differ.generate("stylish", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testPlainDifferRelativePathJSON() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1JSON, file2JSON));
    }

    @Test
    void testPlainDifferAbsolutePathJSON() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2JSON.getAbsolutePath());
        assertEquals(expectedPlain, Differ.generate("plain", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testPlainDifferRelativePathYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1YAML, file2YAML));
    }

    @Test
    void testPlainDifferAbsolutePathYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1YAML.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expectedPlain, Differ.generate("plain", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testPlainDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expectedPlain, Differ.generate("plain", file1JSON, file2YAML));
    }

    @Test
    void testPlainDifferAbsolutePathJSONNYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expectedPlain, Differ.generate("plain", file1WthAbsolutePath, file2WthAbsolutePath));
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

}

