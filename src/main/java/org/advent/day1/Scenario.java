package org.advent.day1;

import org.advent.common.FileParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scenario {
    private final FileParser fileParser;

    public Scenario(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public void runPart1() throws IOException {
        List<String> inputValues = fileParser.getInput(1, 1);
        Integer answer;
        answer = inputValues.stream()
                .map(string -> string.replaceAll("[a-z]", ""))
                .filter(string -> !string.isEmpty())
                .map(number -> number.length() > 1 ? "" + number.charAt(0) + number.charAt(number.length() - 1) : number + number)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
        System.out.println("The Answer to Day 1 Part 1 is: " + answer);
    }

    public void runPart2() throws IOException {
        List<String> inputValues = fileParser.getInput(1, 1);
        Integer answer;
        answer = inputValues.stream()
                .map(this::replaceString)
                .map(string -> string.replaceAll("[a-z]", ""))
                .filter(string -> !string.isEmpty())
                .map(number -> number.length() > 1 ? "" + number.charAt(0) + number.charAt(number.length() - 1) : number + number)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
        System.out.println("The Answer to Day 1 Part 2 is: " + answer);
    }

    private String replaceString(String stri) {
        Map<Integer, String> digitMap = new HashMap<>();
        digitMap.put(1, "one");
        digitMap.put(2, "two");
        digitMap.put(3, "three");
        digitMap.put(4, "four");
        digitMap.put(5, "five");
        digitMap.put(6, "six");
        digitMap.put(7, "seven");
        digitMap.put(8, "eight");
        digitMap.put(9, "nine");


        String str = stri;
        for (int i = 1; i < digitMap.size() + 1; i++) {
            str = str.replace(digitMap.get(i), "" + digitMap.get(i).charAt(0) + i + digitMap.get(i).charAt(digitMap.get(i).length() - 1));
        }
        return str;
    }
}
