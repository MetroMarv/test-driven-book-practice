package de.metromarv.testdriven;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateParseTest {
    
    @Test
    void emptyTemplateResultsInEmptyStringSegment() {
        List<Segment> segments = parseTemplateToSegments("");
        
        assertSegmentsContain(segments, new PlainText(""));
    }
    
    @Test
    void plainTextTemplateResultsInSingleSegment() {
        List<Segment> segments = parseTemplateToSegments("my plain text");
        
        assertSegmentsContain(segments, new PlainText("my plain text"));
    }
    
    @Test
    void parseMultipleSegments() {
        List<Segment> segments = parseTemplateToSegments("${one}, ${two}");
        
        assertSegmentsContain(segments, new Variable("one"), new PlainText(", "), new Variable("two"));
    }
    
    @Test
    void parseLongTemplate() {
        String template = "before text ${variable} in between text ${other} trailing text";
        List<Segment> segments = parseTemplateToSegments(template);
        
        assertSegmentsContain(segments, new PlainText("before text "), new Variable("variable"),
            new PlainText(" in between text "), new Variable("other"), new PlainText(" trailing text"));
    }
    
    private List<Segment> parseTemplateToSegments(String templateString) {
        TemplateParse parser = new TemplateParse(templateString);
        return parser.parse();
    }
    
    private void assertSegmentsContain(List<Segment> segments, Segment... expectedSegments) {
        assertThat(segments).containsExactly(expectedSegments);
    }
}
