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
    
    private List<Segment> parseTemplateToSegments(String templateString) {
        TemplateParse parser = new TemplateParse(templateString);
        return parser.parse();
    }
    
    private void assertSegmentsContain(List<Segment> segments, Segment... expectedSegments) {
        assertThat(segments).containsExactly(expectedSegments);
    }
}
