package site.tddmanifesto.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test void summing_with_semicolon_as_separator() {
        assertEquals(4, stringCalculator.add("//;\n1;3"));
    }

}
