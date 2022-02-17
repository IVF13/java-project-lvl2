package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String file1;

    @Parameters(index = "1", description = "path to second file")
    private String file2;

    @Option(names = {"-f", "--format"}, description = "The count (default: ${stylish})")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(file1, file2, format));
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        //System.exit(exitCode);
    }
}

// ./build/install/app/bin/app -f stylish src/test/resources/file1.json src/test/resources/file2.json
// ./build/install/app/bin/app -f plain src/test/resources/file1.json src/test/resources/file2.json
