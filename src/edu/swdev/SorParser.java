package edu.swdev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

public class SorParser {
    private BufferedReader reader;
    private String fileName;
    private StringBuilder stringBuilder;

    public SorParser(String fileName) {
        this.fileName = fileName;
        this.stringBuilder = new StringBuilder();
        try {
            this.reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File " + fileName + " was not found.", ex);
        }
    }

    private int findSchemaLine() throws IOException {
        int maxIndex = 0;
        int maxCount = 0;
        for (int i = 0; i < 500; i++) {
            String currentRowString = reader.readLine();
            if (currentRowString != null) {
                boolean completePair = true;
                char[] currentRow = currentRowString.toCharArray();
                int currentElementCount = 0;
                for (char c : currentRow) {
                    if (c == '<') {
                        completePair = false;
                    } else if (c == '>' && !completePair) {
                        completePair = true;
                        currentElementCount++;
                    }
                }
                if (currentElementCount > maxCount) {
                    maxCount = currentElementCount;
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    private int getNextField(char[] schemaLine, int index) {
        for (int i = index; i < schemaLine.length; i++) {
            if (schemaLine[i] == '>') {
                return i;
            }
            this.stringBuilder.append(schemaLine[i]);
        }
        throw new IllegalStateException("The schema could not be determined as the SoR file is invalid.");
    }

    private SorType inferFieldType(String field) {
        if (field.length() == 1) {
            if (field.equals("1") || field.equals("0")) {
                return SorType.BOOL;
            }
        }
        try {
            Integer.parseInt(field);
            return SorType.INT;
        } catch (NumberFormatException e) {
            try {
                Float.parseFloat(field);
                return SorType.FLOAT;
            } catch (NumberFormatException ex) {
                return SorType.STRING;
            }
        }
    }
    private List<SorType> inferSchema(int schemaLineIndex) throws IOException {
        // TODO: should we really recreate this
        List<SorType> schema = new ArrayList<>();
        this.reader = new BufferedReader(new FileReader(fileName));
        for (int i = 0; i < schemaLineIndex; i++) {
            this.reader.readLine();
        }
        String schemaLine = this.reader.readLine();
        char[] schemaLineChars = schemaLine.toCharArray();
        int schemaStringPosition = 0;
        while (schemaStringPosition < schemaLineChars.length) {
            if (schemaLineChars[schemaStringPosition] == '<') {
                schemaStringPosition = this.getNextField(schemaLineChars, schemaStringPosition + 1);
                String nextField = this.stringBuilder.toString().trim();
                schema.add(this.inferFieldType(nextField));
                this.stringBuilder.setLength(0);
            }
            schemaStringPosition++;
        }

        return schema;
    }

    private void readLine() {

    }

    private List<List<SorType>> readData(List<SorType> schema) {
        List<List<SorType>> list = new ArrayList<>();
        return list;
    }

    public void parseFile() throws IOException {
        int schemaLineIndex = this.findSchemaLine();
        List<SorType> schema = this.inferSchema(schemaLineIndex);
    }
}
