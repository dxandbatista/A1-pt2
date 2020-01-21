package edu.swdev;

public class SorValue {
    private SorType type;
    private Integer intValue;
    private String stringValue;
    private Boolean boolValue;
    private Float floatValue;

    public SorValue(Integer intValue) {
        this.type = SorType.INT;
        this.intValue = intValue;
    }

    public SorValue(Float floatValue) {
        this.type = SorType.FLOAT;
        this.floatValue = floatValue;
    }

    public SorValue(String stringValue) {
        this.type = SorType.STRING;
        this.stringValue = stringValue;
    }

    public SorValue(Boolean boolValue) {
        this.type = SorType.BOOL;
        this.boolValue = boolValue;
    }

    public SorType getType() {
        return type;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }
}
