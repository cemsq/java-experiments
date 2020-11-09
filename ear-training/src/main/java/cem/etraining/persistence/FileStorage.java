package cem.etraining.persistence;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FileStorage {

    private final String filePath;
    private final String separator;
    private FileWriter writer;

    public FileStorage(String filePath) {
        this(filePath, "|");
    }

    public FileStorage(String filePath, String separator) {
        this.filePath = filePath;
        this.separator = separator;

    }

    public void delete() {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @SneakyThrows
    public void save() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }

    @SafeVarargs
    public final <T> void writeAll(Collection<T> collection, Function<T, Object>... getter) {
        if (collection != null && !collection.isEmpty()) {
            prepareWriter();
            for (T e : collection) {
                Object[] values = Stream.of(getter)
                        .map(g -> g.apply(e))
                        .toArray();

                writeLine(values);
            }
        }
    }

    public void writeLine(Object ...values) {
        prepareWriter();
        if (values != null) {
            String line = toLine(values);

            try {
                this.writer.write(line);
            } catch (IOException e) {
                log.error("Cannot write line: {}", line, e);
            }
        }
    }

    private String toLine(Object[] values) {
        return Stream.of(values)
                        .map(o -> o != null ? o.toString() : "")
                        .map(String::trim)
                        .collect(Collectors.joining(separator)) + "\n";
    }

    @SneakyThrows
    private void prepareWriter() {
        if (writer == null) {
            this.writer = new FileWriter(filePath);
        }
    }
}
