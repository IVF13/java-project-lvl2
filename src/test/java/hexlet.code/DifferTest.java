package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;

public class DifferTest {
    private File file1 = new File("src/test/resources/file1.json");
    private File file2 = new File("src/test/resources/file2.json");
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
    void testDifferRelativePath() throws Exception {
        assertEquals(expected, Differ.generate(file1, file2));
    }


    @Test
    void testDifferAbsolutePath() throws Exception {
        File file1WthAbsoltuePath = new File(file1.getAbsolutePath());
        File file2WthAbsolutePath = new File(file2.getAbsolutePath());
        assertEquals(expected, Differ.generate(file1WthAbsoltuePath, file2WthAbsolutePath));
    }


}

