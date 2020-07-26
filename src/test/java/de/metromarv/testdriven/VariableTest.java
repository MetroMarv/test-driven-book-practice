package de.metromarv.testdriven;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VariableTest {
    
    @Test
    void variableEvaluatesToItsValue() throws MissingValueException {
        HashMap<String, String> variables = new HashMap<>();
        variables.put("variable", "value");
        
        Variable variable = new Variable("variable");
        assertThat(variable.evaluate(variables)).isEqualTo("value");
    }
    
    @Test
    void throwsExceptionIfVariableNotProvided() {
        Variable variable = new Variable("variable");
        MissingValueException exception = assertThrows(MissingValueException.class,
            () -> variable.evaluate(new HashMap<>()));
        
        assertThat(exception.getMessage()).isEqualTo("No value for placeholder 'variable' provided.");
    }
}