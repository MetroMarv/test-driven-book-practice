package de.metromarv.testdriven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {
    
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\$\\{[a-zA-Z]+}");
    
    private final String templateString;
    
    public TemplateParse(String templateString) {
        this.templateString = templateString;
    }
    
    public List<Segment> parse() {
        if (templateString.isEmpty()) {
            return Collections.singletonList(new PlainText(""));
        }
        
        return parseSegments();
    }
    
    private List<Segment> parseSegments() {
        Matcher matcher = VARIABLE_PATTERN.matcher(templateString);
        
        ArrayList<Segment> segments = new ArrayList<>();
        
        int currentIndex = 0;
        
        while (matcher.find()) {
            Optional<PlainText> precedingSegment = createPlainTextForPrecedingText(currentIndex, matcher);
            precedingSegment.ifPresent(segments::add);
            
            segments.add(createVariable(matcher));
            
            currentIndex = matcher.end();
        }
        
        Optional<PlainText> trailingText = createPlainTextForRemainingText(currentIndex);
        trailingText.ifPresent(segments::add);
        
        return segments;
    }
    
    private Optional<PlainText> createPlainTextForPrecedingText(int currentIndex, Matcher matcher) {
        if (matcher.start() > currentIndex) {
            String precedingText = templateString.substring(currentIndex, matcher.start());
            return Optional.of(new PlainText(precedingText));
        }
        
        return Optional.empty();
    }
    
    private Variable createVariable(Matcher matcher) {
        String variableName = templateString.substring(matcher.start() + 2, matcher.end() - 1);
        return new Variable(variableName);
    }
    
    private Optional<PlainText> createPlainTextForRemainingText(int currentIndex) {
        if (currentIndex < templateString.length()) {
            String trailingText = templateString.substring(currentIndex);
            return Optional.of(new PlainText(trailingText));
        }
        
        return Optional.empty();
    }
}
