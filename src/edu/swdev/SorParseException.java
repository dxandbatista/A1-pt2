package edu.swdev;

public class SorParseException extends RuntimeException {
    public SorParseException(String msg) {
        super(msg);
    }

    public SorParseException(String msg, Exception ex) {
        super(msg, ex);
    }
}
