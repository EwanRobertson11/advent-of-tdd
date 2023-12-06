package org.advent.day4;

import java.util.List;

public record Scorecard(List<String> target, List<String> chosen){

    public List<Integer> targetNumbers() {
        return toNumbers(target);
    }

    public List<Integer> chosenNumbers() {
        return toNumbers(chosen);
    }

    private List<Integer> toNumbers(List<String> values) {
        return values.stream().filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
    }
}
