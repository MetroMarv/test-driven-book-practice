package de.metromarv.testdriven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplatePerformanceTest {
    
    private final String templateText = "massa ${one} dui ut ornare lectus sit amet est placerat in ${two} erat " +
        "${three} sed ${four} nisi porta lorem mollis aliquam ut ${four} leo a diam ${five} tempor id eu " +
        "nisl nunc mi ipsum ${six} vitae aliquet nec ${seven} sit amet risus ${eight} eget felis eget nunc " +
        "${nine} mattis aliquam ${ten} purus in massa ${eleven} nec ${twelve} nisl ${thirteen} fusce id velit ut tortor " +
        "pretium viverra ${fourteen} potenti nullam ac tortor vitae purus ${fifteen} ornare suspendisse sed nisi lacus " +
        "sed viverra tellus in hac ${sixteen} platea dictumst ${seventeen} rhoncus est ${eighteen} elit ullamcorper " +
        "${nineteen} cras tincidunt ${twenty} feugiat vivamus at";
    
    private Template template;
    
    @BeforeEach
    void setUp() {
        template = new Template(templateText);
        
        template.set("one", "tincidunt");
        template.set("two", "egestas");
        template.set("three", "imperdiet");
        template.set("four", "porttitor");
        template.set("five", "sollicitudin");
        template.set("six", "faucibus");
        template.set("seven", "ullamcorper");
        template.set("eight", "nullam");
        template.set("nine", "lobortis");
        template.set("ten", "faucibus");
        template.set("eleven", "tempor");
        template.set("twelve", "feugiat");
        template.set("thirteen", "pretium");
        template.set("fourteen", "suspendisse");
        template.set("fifteen", "faucibus");
        template.set("sixteen", "habitasse");
        template.set("seventeen", "vestibulum");
        template.set("eighteen", "pellentesque");
        template.set("nineteen", "dignissim");
        template.set("twenty", "lobortis");
    }
    
    @Test
    void evaluateTemplateIn200MsMax() throws MissingValueException {
        long startTime = System.currentTimeMillis();
        template.evaluate();
        long diff = System.currentTimeMillis() - startTime;
        
        assertThat(diff).isLessThanOrEqualTo(200);
    }
}
