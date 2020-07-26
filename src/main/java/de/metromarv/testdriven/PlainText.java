package de.metromarv.testdriven;

import java.util.Map;
import java.util.Objects;

public class PlainText implements Segment {
    
    private final String text;
    
    public PlainText(String text) {
        this.text = text;
    }
    
    @Override
    public String evaluate(Map<String, String> variables) {
        return text;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlainText)) {
            return false;
        }
        PlainText that = (PlainText) o;
        return text.equals(that.text);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
