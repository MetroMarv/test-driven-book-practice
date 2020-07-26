package de.metromarv.testdriven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TemplateTest {
    
    private Template template;
    
    @BeforeEach
    void setUp() {
        template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }
    
    @Test
    void replaceMultiplePlaceholders() {
        assertTemplateEvaluatesTo("1, 2, 3");
    }
    
    @Test
    void ignoresUnusedVariable() {
        template.set("unused", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3");
    
    }
    
    private void assertTemplateEvaluatesTo(String expectedValue) {
        String result = template.evaluate();
        
        assertThat(result).isEqualTo(expectedValue);
    }
}