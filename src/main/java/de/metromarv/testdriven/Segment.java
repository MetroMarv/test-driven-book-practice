package de.metromarv.testdriven;

import java.util.Map;

public interface Segment {
    
    String evaluate(Map<String, String> variables) throws MissingValueException;
}
