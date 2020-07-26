package de.metromarv.testdriven;

import java.util.Map;
import java.util.Objects;

public class Variable implements Segment {
    
    private final String variableName;
    
    public Variable(String variableName) {
        this.variableName = variableName;
    }
    
    @Override
    public String evaluate(Map<String, String> variables) throws MissingValueException {
        if (!variables.containsKey(variableName)) {
            throw new MissingValueException("No value for placeholder '" + variableName + "' provided.");
        }
        return variables.get(variableName);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Variable)) {
            return false;
        }
        Variable variable = (Variable) o;
        return variableName.equals(variable.variableName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
