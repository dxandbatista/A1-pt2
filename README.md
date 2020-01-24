Our code is run in Java. Starting with our main method, we check the command line arguments passed in first where we look for the presence of flags and if there are the correct ensuing arguments. If a flag is present and there are no following arguments, an error is thrown and the program exits. Else if everything is valid, we create our SoRParser object that is responsible for parsing the file based on the file name, starting position (default: 0), and number of bytes to read (default: 0). We thus try to parse the file, storing the outputted parsed data and parsed schema in a MemoryDataStore object for future reference. If there is an error in parsing (i.e. there is an malformed field), an error is thrown and the program exits. After parsing, we check to see if any flags and corresponding arguments were supplied via command line, and execute those methods accordingly based on the parsed data. 

The SoRParser class contains all the methods needed to parse a file. We first find the index of the line that represents the file schema within the first 500 lines of a file (line with most fields) by counting the number of paired brackets in each line. To create our columnar data representation, we get the data types for each field from that line and add ArrayLists of that type to our master ArrayList. We then parse the entire file line by line, creating SoRValues to represent each field and its type and adding them to their respective column's ArrayList if the SoRValue's type matches the column type. If the field type does not match the column type, a default SoRValue of type MISSING is added. If a row has fewer fields than the schema line, then SoRValues of type MISSING are added to the tail ArrayLists (i.e. Row 3 has 4 fields, schema line has 10, thus the last 6 ArrayLists will have MISSING SoRValues added at index 2 for each). If the number of bytes to read was specified and the next line to read will exceed this byte read limit, the parsing stops and the file is successfully parsed.    

We created an enumeration for the different types a field can be (FLOAT, INT, BOOL, STRING, MISSING) which is used when creating our columnar data representation and SoRValues that get added into said columns. A SoRValue represents a field, having fields that represent its type and corresponding value.

Our MemoryDataStore class holds the parsed data and parsed file schema output from parsing the input file. The command line argument methods can be found here, where we query the stored outputs accordingly. An error is thrown if any index for a command line argument is out of bounds. This class implements a SoRData interface for future functionality, in case we ever need to represent other types of SoRData in a different manner. 