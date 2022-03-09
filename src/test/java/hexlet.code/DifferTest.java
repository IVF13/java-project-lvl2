package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;

public class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private final String file1JSON = "src/test/resources/file1.json";
    private final String file2JSON = "src/test/resources/file2.json";
    private final String file1YAML = "src/test/resources/file1.yml";
    private final String file2YAML = "src/test/resources/file2.yml";
    private final String emptyFileJSON = "src/test/resources/emptyFile.json";
    private final String fileResultJson = "src/test/resources/expectedJsonFile.json";
    private final String file1WthAbsolutePathJSON = new File(file1JSON).getAbsolutePath();
    private final String file2WthAbsolutePathJSON = new File(file2JSON).getAbsolutePath();
    private final String file1WthAbsolutePathYAML = new File(file1YAML).getAbsolutePath();
    private final String file2WthAbsolutePathYAML = new File(file2YAML).getAbsolutePath();
    private final String fileResultJsonWthAbsolutePath = new File(fileResultJson).getAbsolutePath();

    @BeforeAll
    static void readFiles() throws IOException {
        Path expectedStylishFile = Path.of("src/test/resources/expectedStylishFile.rtf");
        Path expectedPlainFile = Path.of("src/test/resources/expectedPlainFile.rtf");
        expectedStylish = Files.readString(expectedStylishFile);
        expectedPlain = Files.readString(expectedPlainFile);
    }

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
        String expectedJson = Files.readString(Path.of(fileResultJsonWthAbsolutePath));
        String actual = Differ.generate(file1WthAbsolutePathJSON, file2WthAbsolutePathJSON, "json");
        assertEquals(expectedJson, actual);
    }

    @Test
    void testJsonDifferAbsolutePathJSON() throws Exception {
        ObjectMapper testMapper = new ObjectMapper();
        String expectedJson = Files.readString(Path.of(fileResultJsonWthAbsolutePath));
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
        Path stylishSameFilesFile = Path.of("src/test/resources/expectedStylishSameFilesFile.rtf");
        String expected = Files.readString(stylishSameFilesFile);
        assertEquals(expected, Differ.generate(file1JSON, file1JSON, "stylish"));
    }

    @Test
    void testStylishEmptyFileDifferRelativePathJSON() throws Exception {
        Path stylishEmptyFile = Path.of("src/test/resources/expectedStylishEmptyFile.rtf");
        String expected = Files.readString(stylishEmptyFile);
        assertEquals(expected, Differ.generate(file1JSON, emptyFileJSON, "stylish"));
    }

    @Test
    void testPlainEmptyFileDifferJSON() throws Exception {
        Path plainEmptyFile = Path.of("src/test/resources/expectedPlainEmptyFile.rtf");
        String expected = Files.readString(plainEmptyFile);
        assertEquals(expected, Differ.generate(file1JSON, emptyFileJSON, "plain"));
    }


}

