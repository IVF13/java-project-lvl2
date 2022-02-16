package hexlet.code;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


public class Differ {
    public static String generate(File file1, File file2, String format) throws Exception {
        Map<String, Object> data1 = Parser.toParse(file1);
        Map<String, Object> data2 = Parser.toParse(file2);

        if (!isCollectionMapNullOrEmpty(data1) && !isCollectionMapNullOrEmpty(data2)) {
            AtomicBoolean identicalChecker = new AtomicBoolean(false);

            data2.forEach((key, value) -> {
                if (value == null) {
                    data2.put(key, "null");
                }
                if (!data1.containsKey(key)) {
                    assert value != null;
                    data1.put(key + ":", value.toString());
                    identicalChecker.set(true);
                }
            });

            if (identicalChecker.toString().equals("false")) {
                return "You are trying to compare identical files.";
            }

            Map<String, Object> data1Sorted = data1.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .peek(x -> {
                        if (x.getValue() == null) {
                            data1.put(x.getKey(), "null");
                        }
                    })
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new));

            data1.clear();

            return Formatter.toChooseFormat(format, data1Sorted, data2);

        }

        return "One of the files, or both of them, are empty";
    }

    protected static boolean isCollectionMapNullOrEmpty(final Map<?, ?> m) {
        return m == null || m.isEmpty();
    }

}

