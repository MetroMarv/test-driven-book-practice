package de.metromarv.testdriven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {
    
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{[a-zA-Z]+}");
    
    private String templateString;
    
    public TemplateParse(String templateString) {
        this.templateString = templateString;
    }
    
    public List<String> parse() {
        if (templateString.isEmpty()) {
            return Collections.singletonList("");
        }
        
        return parseSegments();
    }
    
    private ArrayList<String> parseSegments() {
        Matcher matcher = VARIABLE_PATTERN.matcher(templateString);
        
        ArrayList<String> segments = new ArrayList<>();
        
        int currentIndex = 0;
        
        while (matcher.find()) {
            addPrecedingPlainText(segments, currentIndex, matcher);
            addVariable(segments, matcher);
            
            currentIndex = matcher.end();
        }
        
        addRemainingPlainText(segments, currentIndex);
        
        return segments;
    }
    
    private void addPrecedingPlainText(ArrayList<String> segments, int currentIndex, Matcher matcher) {
        if (matcher.start() > currentIndex) {
            segments.add(templateString.substring(currentIndex, matcher.start()));
        }
    }
    
    private void addVariable(ArrayList<String> segments, Matcher matcher) {
        String segment = templateString.substring(matcher.start(), matcher.end());
        segments.add(segment);
    }
    
    private void addRemainingPlainText(ArrayList<String> segments, int currentIndex) {
        if (currentIndex < templateString.length()) {
            segments.add(templateString.substring(currentIndex));
        }
    }
}
