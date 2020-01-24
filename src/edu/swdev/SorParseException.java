package edu.swdev;

// represents type of exception thrown when invalid inputs are given as arguments
public class SorParseException extends RuntimeException {
    public SorParseException(String msg) {
        super(msg);
    }

    public SorParseException(String msg, Exception ex) {
        super(msg, ex);
    }
}
