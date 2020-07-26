package de.metromarv.testdriven;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateParseTest {
    
    @Test
    void emptyTemplateResultsInEmptyString() {
        TemplateParse parse = new TemplateParse("");
        List<String> segments = parse.parse();
        
        assertThat(segments).hasSize(1);
        assertThat(segments.get(0)).isEqualTo("");
    }
    
    @Test
    void plainTextTemplateResultsInSingleSegment() {
        TemplateParse parse = new TemplateParse("my plain text");
        List<String> segments = parse.parse();
        
        assertThat(segments).hasSize(1);
        assertThat(segments.get(0)).isEqualTo("my plain text");
    }
}
