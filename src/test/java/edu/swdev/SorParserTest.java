package edu.swdev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
}
