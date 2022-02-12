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
            +
            "  - follow: false\n"
            +
            "    host: hexlet.io\n"
            +
            "  - proxy: 123.234.53.22\n"
            +
            "  - timeout: 50\n"
            +
            "  + timeout: 20\n"
            +
            "  + verbose: true\n"
            +
            "}";

    @Test
    void testDifferRelativePathJSON() throws Exception {
        assertEquals(expected, Differ.generate("JSON", file1JSON, file2JSON));
    }


    @Test
    void testDifferAbsolutePathJSON() throws Exception {
        File file1WthAbsolutePath = new File(file1JSON.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2JSON.getAbsolutePath());
        assertEquals(expected, Differ.generate("JSON", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testDifferRelativePathYAML() throws Exception {
        assertEquals(expected, Differ.generate("YAML", file1JSON, file2JSON));
    }

    @Test
    void testDifferAbsolutePathYAML() throws Exception {
        File file1WthAbsolutePath = new File(file1YAML.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2YAML.getAbsolutePath());
        assertEquals(expected, Differ.generate("YAML", file1WthAbsolutePath, file2WthAbsolutePath));
    }

    @Test
    void testIncorrectFormat() throws Exception {
        String incorrect = "format is incorrect\n supported formats:\n - JSON\n - YAML";
        assertEquals(incorrect, Differ.generate("HTML", file1JSON, file2JSON));
    }

    @Test
    void testSameFiles() throws Exception {
        String identical = "You are trying to compare identical files.";
        assertEquals(identical, Differ.generate("JSON", file1JSON, file1JSON));
    }
}

