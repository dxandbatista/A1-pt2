package edu.swdev;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = null;
        int startPos = 0;
        int numBytes = -1;
        int colTypeNumber = -1;
        int printColIndex = -1;
        int printColOffset = -1;
        int missingColIndex = -1;
        int missingColOffset = -1;

        // store command line inputs accordingly based on flags present
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-f":
                    if (i + 1 < args.length) {
                        fileName = args[i + 1];
                        i++;
                    } else {
                        System.out.println("Usage: -f [fileName]");
                        System.exit(1);
                    }
                    break;
                case "-from":
                    if (i + 1 < args.length) {
                        try {
                            startPos = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Usage: -from [int]");
                            System.exit(1);
                        }
                        i++;
                    } else {
                        System.out.println("Usage: -from [int]");
                        System.exit(1);
                    }
                    break;
                case "-len":
                    if (i + 1 < args.length) {
                        try {
                            numBytes = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Usage: -len [int]");
                            System.exit(1);
                        }
                        i++;
                    } else {
                        System.out.println("Usage: -len [int]");
                        System.exit(1);
                    }
                    break;
                case "-print_col_type":
                    if (i + 1 < args.length) {
                        try {
                            colTypeNumber = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Usage: -print_col_type [int]");
                            System.exit(1);
                        }
                        i++;
                    } else {
                        System.out.println("Usage: -print_col_type [int]");
                        System.exit(1);
                    }
                    break;
                case "-print_col_idx":
                    if (i + 2 < args.length) {
                        try {
                            printColIndex = Integer.parseInt(args[i + 1]);
                            printColOffset = Integer.parseInt(args[i + 2]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Usage: -print_col_idx [int] [int]");
                            System.exit(1);
                        }
                        i += 2;
                    } else {
                        System.out.println("Usage: -print_col_idx [int] [int]");
                        System.exit(1);
                    }
                    break;
                case "-is_missing_idx":
                    if (i + 2 < args.length) {
                        try {
                            missingColIndex = Integer.parseInt(args[i + 1]);
                            missingColOffset = Integer.parseInt(args[i + 2]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Usage: -is_missing_idx [int] [int]");
                            System.exit(1);
                        }
                        i += 2;
                    } else {
                        System.out.println("Usage: -is_missing_idx [int] [int]");
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("Unrecognized flag. Try again");
                    System.exit(1);
            }
        }

        // create parser in preparation to parse schema and columnar representation of data
        if (fileName == null) {
            System.out.println("A file must be specified. Try again.");
            System.exit(1);
        }
        SorParser parser = new SorParser(fileName, startPos, numBytes);
        // hold data of parsed input file to be accessed via command line methods
        SorData data = new MemoryDataStore();

        // attempt to parse input file, holding parsed data and schema accordingly
        try {
            parser.parseFile();
            data.setData(parser.getData());
            data.setSchema(parser.getSchema());
        } catch (SorParseException ex) {
            System.out.println("Exception occurred during parsing: " + ex.getMessage());
            System.exit(1);
        }

        // check if any method flag and argument was included in command line
        if (colTypeNumber != -1) {
            System.out.println(data.getColType(colTypeNumber));
            return;
        }
        if (printColIndex != -1 || printColOffset != -1) {
            if (printColOffset == -1 || printColIndex == -1) {
                System.out.println("Both print_col_idx and print_col_offset must be set.");
                System.exit(1);
            }
            SorValue valueToPrint = data.getValue(printColIndex, printColOffset);
            if (SorType.STRING.equals(valueToPrint.getType())) {
                System.out.println("\"" + valueToPrint.getValue() + "\"");
            } else {
                System.out.println(valueToPrint.getValue());
            }
        }
        if (missingColIndex != -1 || missingColOffset != -1) {
            if (missingColIndex == -1 || missingColOffset == -1) {
                System.out.println("Both missing_col_idx and missing_col_offset must be set.");
                System.exit(1);
            }
            if (data.getValue(missingColIndex, missingColOffset).getType().equals(SorType.MISSING)) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        }
    }
}
