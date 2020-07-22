package de.metromarv.testdriven;

import java.util.HashMap;
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
    
    public String evaluate() {
        String result = templateString;
    
        for (Map.Entry<String, String> variable : variables.entrySet()) {
            String regex = "\\$\\{" + variable.getKey() + "}";
            result = result.replaceAll(regex, variable.getValue());
        }
        
        return result;
    }
}
