# Difference Calculator:

[![Actions Status](https://github.com/IVF13/java-project-lvl2/workflows/hexlet-check/badge.svg)](https://github.com/IVF13/java-project-lvl2/actions)
[![Java CI](https://github.com/IVF13/java-project-lvl2/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/IVF13/java-project-lvl2/actions/workflows/github-actions-demo.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/890803fa9fd274697fae/maintainability)](https://codeclimate.com/github/IVF13/java-project-lvl2/maintainability)
<a href="https://codeclimate.com/github/IVF13/java-project-lvl2/test_coverage"><img src="https://api.codeclimate.com/v1/badges/890803fa9fd274697fae/test_coverage" /></a>

The difference calculator is a console utility that can compare the contents of json and yaml files. It has various
output formats: stylish, plain, json. This utility can also be used as a library when writing your own program.

## An example of the program's operation in console:

[![asciicast](https://asciinema.org/a/wKwRWO6ZWbJfSqaM6wKhbLpyS.svg)](https://asciinema.org/a/wKwRWO6ZWbJfSqaM6wKhbLpyS)

### Command:

###### ~/app [-hV] [-f=format] filePath1 filePath2

###### (eg. "./build/install/app/bin/app -f plain src/test/resources/file1.json src/test/resources/file2.json")

## An example of the program's operation as library:

```Java
//Imports:
import hexlet.code.Differ;
import hexlet.code.Builder;
import hexlet.code.Parser;
import hexlet.code.Formatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;

class App {
    public static void main(String... args) {
        String file1JSON = "src/test/resources/file1.json";
        String file2JSON = "src/test/resources/file2.json";
        String result = Differ.generate(file1JSON, file2JSON, "stylish");
        System.out.println(result);
    }
}
```
