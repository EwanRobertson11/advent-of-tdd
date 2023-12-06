package org.advent.day4;

import org.advent.common.FileParser;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        FileParser fileParser = new FileParser();
        Scenario scenario = new Scenario(fileParser);

        scenario.run();
    }
}
