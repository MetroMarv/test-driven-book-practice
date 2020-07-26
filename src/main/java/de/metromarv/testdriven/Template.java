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
        List<Segment> segments = templateParse.parse();
        return concatenate(segments);
    }
    
    private String concatenate(List<Segment> segments) throws MissingValueException {
        StringBuilder resultBuilder = new StringBuilder();
        
        for (Segment segment : segments) {
            resultBuilder.append(segment.evaluate(variables));
        }
        
        return resultBuilder.toString();
    }
}
