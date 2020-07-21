package de.metromarv.testdriven;

public class Template {
    
    private final String templateString;
    
    private String value;
    
    public Template(String templateString) {
        this.templateString = templateString;
    }
    
    public void set(String variable, String value) {
        this.value = value;
    }
    
    public String evaluate() {
        return templateString.replaceAll("\\$\\{name}", value);
    }
}
