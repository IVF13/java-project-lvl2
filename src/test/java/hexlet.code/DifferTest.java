package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;

public class DifferTest {
    private File file1JSON = new File("src/test/resources/file1.json");
    private File file2JSON = new File("src/test/resources/file2.json");
    private File file1YAML = new File("src/test/resources/file1.yml");
    private File file2YAML = new File("src/test/resources/file2.yml");
    private String expected = "{\n"
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

    @Test
    void testDifferRelativePathJSON() throws Exception {
        assertEquals(expected, Differ.generate("stylish", file1JSON, file2JSON));
    }


    @Test
    void testDifferAbsolutePathJSON() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2JSON.getAbsolutePath());
        assertEquals(expected, Differ.generate("stylish", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testDifferRelativePathYAML() throws Exception {
        assertEquals(expected, Differ.generate("stylish", file1YAML, file2YAML));
    }

    @Test
    void testDifferAbsolutePathYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1YAML.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expected, Differ.generate("stylish", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testDifferRelativePathJSONNYAML() throws Exception {
        assertEquals(expected, Differ.generate("stylish", file1JSON, file2YAML));
    }

    @Test
    void testDifferAbsolutePathJSONNYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expected, Differ.generate("stylish", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testIncorrectFormat() throws Exception {
        String incorrect = "format is incorrect\n supported formats:\n - stylish\n";
        assertEquals(incorrect, Differ.generate("HTML", file1JSON, file2JSON));
    }

    @Test
    void testSameFiles() throws Exception {
        String identical = "You are trying to compare identical files.";
        assertEquals(identical, Differ.generate("stylish", file1JSON, file1JSON));
    }
}

