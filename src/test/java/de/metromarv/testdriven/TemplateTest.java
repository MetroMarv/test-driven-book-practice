package de.metromarv.testdriven;

import org.junit.jupiter.api.Assertions;
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
    void replaceMultiplePlaceholders() throws MissingValueException {
        assertTemplateEvaluatesTo("1, 2, 3");
    }
    
    @Test
    void ignoresUnusedVariable() throws MissingValueException {
        template.set("unused", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3");
    
    }
    
    @Test
    void throwsExceptionIfPlaceholderNotProvided() {
        Template template = new Template("Hello, ${name}");
    
        MissingValueException exception = Assertions.assertThrows(MissingValueException.class,
            template::evaluate);
    
        assertThat(exception).hasMessage("No value for placeholder ${name} provided.");
    }
    
    @Test
    void templateIsJustProcessedOnce() throws MissingValueException {
        template.set("one", "${one}");
        template.set("two", "${three}");
        template.set("three", "${two}");
        
        assertTemplateEvaluatesTo("${one}, ${three}, ${two}");
    }
    
    private void assertTemplateEvaluatesTo(String expectedValue) throws MissingValueException {
        String result = template.evaluate();
        
        assertThat(result).isEqualTo(expectedValue);
    }
}