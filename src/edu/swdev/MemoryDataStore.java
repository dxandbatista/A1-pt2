package edu.swdev;

import java.util.ArrayList;
import java.util.List;

public class MemoryDataStore implements SorData {
    private List<List<SorValue>> data;
    private List<SorType> schema;

    public MemoryDataStore() {
        this.data = new ArrayList<>();
        this.schema = new ArrayList<>();
    }

    @Override
    public SorType getColType(int column) {
        return this.schema.get(column);
    }

    @Override
    public SorValue getValue(int column, int offset) {
        if (column >= this.data.size()) {
            throw new IndexOutOfBoundsException("Column " + column + " is out of bounds.");
        }
        if (offset >= this.data.get(column).size()) {
            throw new IndexOutOfBoundsException("Offset " + offset + " in column " + column + " is out of bounds.");
        }
        return this.data.get(column).get(offset);
    }

    @Override
    public void setData(List<List<SorValue>> data) {
        this.data = data;
    }

    @Override
    public void setSchema(List<SorType> schema) {
        this.schema = schema;
    }

    public void printData() {
        this.data.forEach(col -> {
            System.out.println("new col");
            col.stream().map(SorValue::getValue).forEach(System.out::println);
        });
    }
}
