package de.metromarv.testdriven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
    
    private final String templateString;
    
    private final Map<String, String> variables = new HashMap<>();
    
    public Template(String templateString) {
        this.templateString = templateString;
    }
    
    public void set(String variable, String value) {
        this.variables.put(variable, value);
    }
    
    public String evaluate() throws MissingValueException {
        TemplateParse templateParse = new TemplateParse(templateString);
        List<String> segments = templateParse.parse();
        return concatenate(segments);
    }
    
    private String concatenate(List<String> segments) throws MissingValueException {
        StringBuilder resultBuilder = new StringBuilder();
        
        for (String segment : segments) {
            resultBuilder.append(evaluateSegment(segment));
        }
        
        return resultBuilder.toString();
    }
    
    private String evaluateSegment(String segment) throws MissingValueException {
        if (isVariable(segment)) {
            return evaluateVariable(segment);
        } else {
            return segment;
        }
    }
    
    private String evaluateVariable(String segment) throws MissingValueException {
        String varName = segment.substring(2, segment.length() - 1);
        
        if (!variables.containsKey(varName)) {
            throw new MissingValueException("No value for placeholder ${" + varName + "} provided.");
        }
        
        return variables.get(varName);
    }
    
    private boolean isVariable(String segment) {
        return segment.startsWith("${") && segment.endsWith("}");
    }
}
