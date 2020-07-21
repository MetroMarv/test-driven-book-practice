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
}