package de.metromarv.testdriven;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class PlainTextTest {
    
    @Test
    void rendersAsIs() {
        PlainText plainText = new PlainText("My text");
        assertThat(plainText.evaluate(new HashMap<>())).isEqualTo("My text");
    }
}