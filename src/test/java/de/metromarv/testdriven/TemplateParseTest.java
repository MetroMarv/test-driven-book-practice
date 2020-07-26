package de.metromarv.testdriven;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateParseTest {
    
    @Test
    void emptyTemplateResultsInEmptyString() {
        String templateString = "";
        List<String> segments = parseTemplate(templateString);
        
        assertSegments(segments, templateString);
    }
    
    @Test
    void plainTextTemplateResultsInSingleSegment() {
        List<String> segments = parseTemplate("my plain text");
        
        assertSegments(segments, "my plain text");
    }
    
    @Test
    void parseMultipleSegments() {
        List<String> segments = parseTemplate("${one}, ${two}");
        assertSegments(segments, "${one}", ", ", "${two}");
    }
    
    private List<String> parseTemplate(String templateString) {
        TemplateParse parse = new TemplateParse(templateString);
        return parse.parse();
    }
    
    private void assertSegments(List<String> segments, String... expectedSegments) {
        assertThat(segments).hasSize(expectedSegments.length);
        assertThat(segments).isEqualTo(Arrays.asList(expectedSegments));
    }
}
