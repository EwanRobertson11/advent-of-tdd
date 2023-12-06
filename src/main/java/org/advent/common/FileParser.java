package org.advent.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class FileParser {

    private final static String FILENAME_PATTERN = "day-%d-part-%d.txt";

    public List<String> getInput(int day, int part) throws IOException {
        String filename = String.format(FILENAME_PATTERN, day, part);
        String path = requireNonNull(getClass().getClassLoader().getResource(filename)).getFile().substring(1);
        try(Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.collect(Collectors.toList());
        }
    }
}
