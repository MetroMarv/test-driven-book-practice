package de.metromarv.testdriven;

import de.metromarv.testdriven.Template;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TemplateTest {
    
    @Test
    void replacesSinglePlaceholder() {
        // given
        Template template = new Template("Hello, ${name}");
        template.set("name", "Peter");
    
        // when
        String result = template.evaluate();
        
        // then
        assertThat(result).isEqualTo("Hello, Peter");
    }
    
    @Test
    void replacesSinglePlaceholderInOtherTemplate() {
        // given
        Template template = new Template("Tjena, ${name}");
        template.set("name", "Homer");
        
        // when
        String result = template.evaluate();
        
        // then
        assertThat(result).isEqualTo("Tjena, Homer");
    }
    
    @Test
    void replaceMultiplePlaceholders() {
        // given
        Template template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
        
        // when
        String result = template.evaluate();
        
        // then
        assertThat(result).isEqualTo("1, 2, 3");
    }
}