package site.tddmanifesto.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test void should_sum_with_semicolon_as_separator() {
        assertEquals(4, stringCalculator.add("//;\n1;3"));
    }

    @Test void should_sum_with_pipe_as_separator() {
        assertEquals(6, stringCalculator.add("//|\n1|2|3"));
    }

    @Test void should_sum_with_string_as_separator() {
        assertEquals(7, stringCalculator.add("//sep\n2sep5"));
    }

    @Test void when_using_wrong_separator_then_should_show_error_message_with_error_position() {
        try {
            stringCalculator.add("//|\n1|2,3");
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            assertEquals("'|' expected, but ',' found at position 3.", e.getMessage());
        }
    }

}
