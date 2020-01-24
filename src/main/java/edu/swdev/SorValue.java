package edu.swdev;

public class SorValue {
    private SorType type;
    private Integer intValue;
    private String stringValue;
    private Boolean boolValue;
    private Float floatValue;

    // if no value is given to store, default to MISSING
    public SorValue() {
        this.type = SorType.MISSING;
    }

    // constructors based on type of value to store
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

    // return type of SorValue
    public SorType getType() {
        return type;
    }

    // return value of SorValue
    public Object getValue() {
        if (SorType.BOOL.equals(this.type)) {
            return this.boolValue;
        }
        if (SorType.FLOAT.equals(this.type)) {
            return this.floatValue;
        }
        if (SorType.STRING.equals(this.type)) {
            return this.stringValue;
        }
        if (SorType.INT.equals(this.type)) {
            return this.intValue;
        }
        return null;
    }
}
