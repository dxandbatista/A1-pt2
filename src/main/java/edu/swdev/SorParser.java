package edu.swdev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SorParser {
    private RandomAccessFile file;
    private StringBuilder stringBuilder;
    private List<List<SorValue>> data;
    private List<SorType> schema;
    private int startPos;
    private int numBytes;

    // constructor; initialize fields
    public SorParser(String fileName, int startPos, int numBytes) {
        this.startPos = startPos;
        this.numBytes = numBytes;
        this.stringBuilder = new StringBuilder("");
        this.data = new ArrayList<>();
        this.schema = new ArrayList<>();
        try {
            this.file = new RandomAccessFile(fileName, "r");
        } catch (FileNotFoundException ex) {
            throw new SorParseException("File " + fileName + " was not found.", ex);
        }
    }

    /**
     * Parses the file by determining schema first, then parsing based on the schema
     */
    public void parseFile() {
        int schemaLineIndex = this.findSchemaLine();
        this.inferSchema(schemaLineIndex);
        this.readData();
    }

    /**
     * Returns the schema for the parsed file
     */
    public List<SorType> getSchema() {
        if (this.schema.size() == 0) {
            throw new SorParseException("No file has been parsed yet.");
        }
        return this.schema;
    }

    /**
     * Returns the data for the parsed file as a 2D array
     */
    public List<List<SorValue>> getData() {
        if (this.data.size() == 0) {
            throw new SorParseException("No file has been parsed yet.");
        }
        return this.data;
    }

    // find index of line that contains the most elements (to represent file schema)
    private int findSchemaLine() {
        int maxIndex = 0;
        int maxCount = 0;
        char[] currentRow;
        for (int i = 0; i < 500; i++) {
            if ((currentRow = this.readNextLine()) != null && currentRow.length > 0) {
                boolean completePair = true;
                int currentElementCount = 0;
                // count # of elements in current row based on pair of brackets
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

    // store all the chars at a certain open bracket's position of a file line, up to a closed bracket
    // returns index of corresponding closed bracket
    private int getNextField(char[] schemaLine, int index) {
        for (int i = index; i < schemaLine.length; i++) {
            if (schemaLine[i] == '>') {
                return i;
            }
            this.stringBuilder.append(schemaLine[i]);
        }
        throw new SorParseException("The schema could not be determined as the SoR file is invalid.");
    }

    // check to see if a String has quotes at its beginning and end
    private static boolean isQuotedString(String s) {
        return s.length() > 1 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"';
    }

    // resets the file reader to its starting position
    private void resetFile() {
        try {
            this.file.seek(this.startPos);
        }  catch (IOException ex) {
            throw new SorParseException("Failed to reset reader.", ex);
        }
    }

    // reads and stores the file's next line (if it exists)
    private char[] readNextLine() {
        char[] res;
        String lineString;
        try {
            if (this.startPos != 0) {
                this.file.readLine();
            }
            if ((lineString = this.file.readLine()) != null) {
                res = lineString.toCharArray();
            } else {
                res = null;
            }
            return res;
        } catch (IOException ex) {
            throw new SorParseException("Failed to read the next line.", ex);
        }
    }

    // creates a SorValue based on the type of input data; to be stored in the file's parsed data output
    // order of data checks: BOOL, INT, FLOAT, STRING
    private SorValue parseField(String rawField) {
        String field = rawField.trim();
        if (field.length() == 0) {
            return new SorValue();
        }
        if (field.length() == 1) {
            if ("1".equals(field)) {
                return new SorValue(true);
            } if ("0".equals(field)) {
                return new SorValue(false);
            }
        }
        boolean isQuotedString = isQuotedString(field);
        try {
            Integer integer = Integer.parseInt(field);
            return new SorValue(integer);
        } catch (NumberFormatException e) {
            try {
                Float fl = Float.parseFloat(field);
                return new SorValue(fl);
            } catch (NumberFormatException ex) {
                if (field.contains(" ")) {
                    if (!isQuotedString) {
                        return new SorValue();
                    }
                }
                if (isQuotedString) {
                    return new SorValue(field.substring(1, field.length() - 1));
                }
                return new SorValue(field);
            }
        }
    }

    // get file schema based on line with most elements
    private void inferSchema(int schemaLineIndex) {
        this.resetFile();
        for (int i = 0; i < schemaLineIndex; i++) {
            this.readNextLine();
        }
        char[] schemaLineChars = this.readNextLine();
        int schemaStringPosition = 0;
        while (schemaStringPosition < schemaLineChars.length) {
            if (schemaLineChars[schemaStringPosition] == '<') {
                // sets next scanning position of schema line to be at an element's close bracket
                schemaStringPosition = this.getNextField(schemaLineChars, schemaStringPosition + 1);
                String nextField = this.stringBuilder.toString();
                schema.add(this.parseField(nextField).getType());
                data.add(new ArrayList<>());
                this.stringBuilder.setLength(0);
            }
            schemaStringPosition++;
        }
    }

    // parses a file line based on the file schema
    // if their SoR types match, element is added to its respective data column (else MISSING is added)
    private void parseLine(char[] lineCharArray) {
        int linePosition = 0;
        int fieldCounter = 0;
        while (linePosition < lineCharArray.length) {
            if (lineCharArray[linePosition] == '<') {
                // sets next scanning position of schema line to be at an element's close bracket
                linePosition = this.getNextField(lineCharArray, linePosition + 1);
                String nextField = this.stringBuilder.toString();
                SorValue field = this.parseField(nextField);
                if (fieldCounter < this.schema.size()) {
                    if (field.getType().equals(schema.get(fieldCounter))) {
                        data.get(fieldCounter).add(field);
                    } else {
                        data.get(fieldCounter).add(new SorValue());
                    }
                }
                this.stringBuilder.setLength(0);
                fieldCounter++;
            }
            linePosition++;
        }
        // if a file line doesn't have enough elements to match the schema length, MISSINGs are added
        for (int i = fieldCounter; i < this.schema.size(); i++) {
            this.data.get(i).add(new SorValue());
        }
    }

    // read next line of file data if it doesn't exceed maximum number of bytes to read
    private void readData() {
        this.resetFile();
        char[] nextLine;
        while ((nextLine = this.readNextLine()) != null) {
            try {
                if (this.numBytes > 0 && this.file.getFilePointer() + nextLine.length > this.numBytes) {
                    return;
                }
                this.parseLine(nextLine);
            } catch (IOException ex) {
                throw new SorParseException("Failed to read next line of the file.", ex);
            }
        }
    }

}
