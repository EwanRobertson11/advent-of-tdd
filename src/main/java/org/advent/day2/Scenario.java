package org.advent.day2;

import org.advent.common.FileParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scenario {
    private final FileParser fileParser;

    public Scenario(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public void runPart1() throws IOException {
        // 12 red cubes, 13 green cubes, and 14 blue cubes
        List<String> inputValues = fileParser.getInput(2, 1);
        Map<Integer, List<String>> valueMap = new HashMap<>();

        inputValues.forEach(string -> valueMap.putAll(toMap(string)));
        int answer = valueMap.entrySet()
                .stream()
                .filter(entry -> possibleCubeCount("blue", entry.getValue(), 14))
                .filter(entry -> possibleCubeCount("red", entry.getValue(), 12))
                .filter(entry -> possibleCubeCount("green", entry.getValue(), 13))
                .map(Map.Entry::getKey)
                .reduce(0, Integer::sum);
        System.out.println("The answer to day 2 part 1 is: " + answer);
    }

    public void runPart2() throws IOException {
        List<String> inputValues = fileParser.getInput(2, 2);
        Map<Integer, List<String>> valueMap = new HashMap<>();

        inputValues.forEach(string -> valueMap.putAll(toMap(string)));
        int answer = valueMap.values()
                .stream()
                .map(this::getGamePower)
                .reduce(0, Integer::sum);
        System.out.println("The answer to day 2 part 2 is: " + answer);

    }

    private Map<Integer, List<String>> toMap(String str) {
        Integer index = Integer.parseInt(str.split(": ")[0].replaceAll("Game ", ""));
        String cubes = str.split(": ")[1];
        List<String> gameList = Arrays.stream(cubes.split(";")).toList();
        gameList = gameList.stream().map(game -> game.replaceAll(";", "")).toList();
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(index, gameList);
        return map;
    }

    private Boolean possibleCubeCount(String colour, List<String> games, int maxCubeCount) {
        for (String game : games) {
            String[] cubeList = game.split(",");
            long cubesExist = Arrays.stream(cubeList).filter(str -> str.contains(colour)).count();
            int cubeCount = cubesExist > 0 ? Integer.parseInt(Arrays.stream(cubeList).filter(str -> str.contains(colour)).map(str -> str.replaceAll("[a-z]", "").replaceAll(" ", "")).toList().get(0)) : 0;
            if (cubeCount > maxCubeCount) {
                return false;
            }
        }
        return true;
    }

    private int getGamePower(List<String> games) {
        String[] colours = {"blue", "green", "red"};
        int power = 1;
        for (String colour : colours) {
            int largestCubCount = 0;
            for (String game : games) {
                String[] cubeList = game.split(",");
                long cubesExist = Arrays.stream(cubeList).filter(str -> str.contains(colour)).count();
                int cubeCount = cubesExist > 0 ? Integer.parseInt(Arrays.stream(cubeList).filter(str -> str.contains(colour)).map(str -> str.replaceAll("[a-z]", "").replaceAll(" ", "")).toList().get(0)) : 0;
                largestCubCount = Math.max(cubeCount, largestCubCount);
            }
            power = power * largestCubCount;
        }
        return power;
    }

}
