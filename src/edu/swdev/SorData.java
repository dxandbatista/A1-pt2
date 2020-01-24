package edu.swdev;

import java.util.List;

public interface SorData {
    public void setData(List<List<SorValue>> data);
    public void setSchema(List<SorType> schema);
    public SorValue getValue(int column, int offset);
    public SorType getColType(int column);
}
