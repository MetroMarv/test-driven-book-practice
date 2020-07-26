package de.metromarv.learning;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTest {
    
    @Test
    void testFindPlaceholder() {
        Matcher matcher = Pattern.compile("\\$\\{[a-zA-Z]+}").matcher("Template ${one}, ${two}");
        
        // Find first match: ${one}
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.start()).isEqualTo(9);
        assertThat(matcher.end()).isEqualTo(15);
        
        // Find first match: ${two}
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.start()).isEqualTo(17);
        assertThat(matcher.end()).isEqualTo(23);
        
        // Then no more matches
        assertThat(matcher.find()).isFalse();
    }
    
    @Test
    void testFindsTwoPlaceholderNotSeparated() {
        Matcher matcher = Pattern.compile("\\$\\{[a-zA-Z]+}").matcher("Template ${one}${two}");
        
        // Find first match: ${one}
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.start()).isEqualTo(9);
        assertThat(matcher.end()).isEqualTo(15);
        
        // Find first match: ${two}
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.start()).isEqualTo(15);
        assertThat(matcher.end()).isEqualTo(21);
        
        // Then no more matches
        assertThat(matcher.find()).isFalse();
    }
}
