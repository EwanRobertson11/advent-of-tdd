package org.advent.day4;

import org.advent.common.FileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scenario {

    private final FileParser fileParser;

    public Scenario(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public void run() throws IOException {
        List<String> values = fileParser.getInput(4, 1);
        values.forEach(System.out::println);
        double answer = values.stream()
                .map(input -> input.split(": ")[1])
                .map(input -> input.split(" \\| "))
                .map(splitInput -> new Scorecard(Arrays.stream(splitInput[0].split(" ")).toList(), Arrays.stream(splitInput[1].split(" ")).toList()))
                .map(scorecard -> new ScorecardNumber(scorecard.targetNumbers(), scorecard.chosenNumbers()))
                .map(this::reduce)
                .mapToDouble(num -> num)
                .sum();
        System.out.println(answer);
    }

    private double reduce(ScorecardNumber scorecard) {
        long n = scorecard.chosen().stream()
                .map(chosen -> scorecard.target().stream().anyMatch(target -> target.equals(chosen)))
                .filter(t -> t)
                .count();
        return n == 0 ? 0 : Math.pow(2, n - 1);
    }
}
