package de.metromarv.testdriven;

import java.util.List;

public class TemplateParse {
    
    private String templateString;
    
    public TemplateParse(String templateString) {
        this.templateString = templateString;
    }
    
    public List<String> parse() {
        return List.of(templateString);
    }
}
