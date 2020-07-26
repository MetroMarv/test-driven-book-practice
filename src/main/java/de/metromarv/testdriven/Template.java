package de.metromarv.testdriven;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String result = replaceVariables();
        checkForMissingValues(result);
    
        return result;
    }
    
    private String replaceVariables() {
        String result = templateString;
        
        for (Map.Entry<String, String> variable : variables.entrySet()) {
            String regex = "\\$\\{" + variable.getKey() + "}";
            result = result.replaceAll(regex, variable.getValue());
        }
        return result;
    }
    
    private void checkForMissingValues(String result) throws MissingValueException {
        Matcher matcher = Pattern.compile("\\$\\{.*}").matcher(result);
        if (matcher.find()) {
            throw new MissingValueException("No value for placeholder " + matcher.group() + " provided.");
        }
    }
}
