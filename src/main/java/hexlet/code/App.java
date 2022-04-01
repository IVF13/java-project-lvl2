package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "The count (default: ${stylish})")
    private final String format = "stylish";

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filePath1, filePath2, format));
        return 0;
    }
}

// ./build/install/app/bin/app -h
// ./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json
// ./build/install/app/bin/app -f stylish src/test/resources/file1.json src/test/resources/file2.json
// ./build/install/app/bin/app -f plain src/test/resources/file1.json src/test/resources/file2.json
// ./build/install/app/bin/app -f json src/test/resources/file1.json src/test/resources/file2.json
// ./build/install/app/bin/app src/test/resources/file1.yml src/test/resources/file2.yml
// ./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.yml
// ./build/install/app/bin/app -f lol src/test/resources/file1.json src/test/resources/file2.yml
// ./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file1.json
// ./build/install/app/bin/app src/test/resources/file1.json src/test/resources/emptyFile.json
