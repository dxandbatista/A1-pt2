package edu.swdev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;


public class SorParserTest {

    private SorParser parser;

    private void init(String fileName) {
        this.parser = new SorParser("resources/" + fileName, 0, -1);
    }

    @Test
    void testSchemaSimple() {
        this.init("test1.txt");
        this.parser.parseFile();

        List<SorType> schema = this.parser.getSchema();
        assertEquals(3, schema.size());
        assertEquals(SorType.BOOL, schema.get(0));
        assertEquals(SorType.FLOAT, schema.get(1));
        assertEquals(SorType.STRING, schema.get(2));
    }

    @Test
    void testMissingFileThrowsException() {
        Exception ex = assertThrows(
                SorParseException.class,
                () -> this.init("imnotreal.txt")
        );

        assertTrue(ex.getMessage().contains("was not found."));
    }

    @Test
    void testInvalidFileThrowsError() {
        this.init("test3.txt");
        Exception ex = assertThrows(
                SorParseException.class,
                () -> this.parser.parseFile()
        );

        assertTrue(ex.getMessage().contains("The schema could not be determined"));
    }

    @Test
    void testGetSchemaBeforeParse() {
        this.init("test3.txt");
        Exception ex = assertThrows(
                SorParseException.class,
                () -> this.parser.getSchema()
        );

        assertTrue(ex.getMessage().contains("No file has been parsed yet"));
    }

    @Test
    void testGetDataBeforeParse() {
        this.init("test3.txt");
        Exception ex = assertThrows(
                SorParseException.class,
                () -> this.parser.getData()
        );

        assertTrue(ex.getMessage().contains("No file has been parsed yet"));
    }

    @Test
    void testGetSchemaOffset() {
        this.init("test1.txt");
        this.parser.parseFile();

        List<SorType> schemaNoOffset = this.parser.getSchema();
        assertEquals(SorType.FLOAT, schemaNoOffset.get(1));

        this.parser = new SorParser("resources/test1.txt", 3, -1);
        this.parser.parseFile();
        List<SorType> schemaOffset = this.parser.getSchema();
        assertEquals(SorType.INT, schemaOffset.get(1));
    }

    @Test
    void testGetDataSimple() {
        this.init("test1.txt");
        this.parser.parseFile();

        List<List<SorValue>> data = this.parser.getData();
        assertEquals(3, data.get(0).size());
        assertEquals(Boolean.FALSE, data.get(0).get(0).getValue());
        assertEquals(Boolean.TRUE, data.get(0).get(1).getValue());
        assertEquals(Boolean.TRUE, data.get(0).get(2).getValue());
        assertEquals(2.3f, data.get(1).get(0).getValue());
        assertNull(data.get(1).get(1).getValue());
        assertNull(data.get(1).get(2).getValue());
        assertEquals("hi", data.get(2).get(0).getValue());
        assertNull(data.get(2).get(1).getValue());
        assertNull(data.get(2).get(2).getValue());
    }

    @Test
    void parseLargeFile() {
        this.init("test-large.txt");
        this.parser.parseFile();

        List<List<SorValue>> data = this.parser.getData();
        assertEquals(6, data.size());
        assertEquals(12145, data.get(0).size());
        assertEquals("hi", data.get(0).get(12144).getValue());
    }

    @Test
    void getOobIndexThrowsException() {
        this.init("test1.txt");
        this.parser.parseFile();

        List<List<SorValue>> data = this.parser.getData();
        assertEquals(3, data.size());
        assertThrows(IndexOutOfBoundsException.class,
                () ->data.get(3)
        );
        assertEquals(3, data.get(1).size());
        assertThrows(IndexOutOfBoundsException.class,
                () ->data.get(1).get(3)
        );
    }
}
