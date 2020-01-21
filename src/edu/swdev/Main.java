package edu.swdev;

import java.io.IOException;

public class Main {

    // TODO: Command line args
    public static void main(String[] args) {
        String fileName = args[1];
        System.out.println(fileName);
        SorParser parser = new SorParser(fileName);
        try {
            parser.parseFile();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to parse file.");
        }
    }
}
