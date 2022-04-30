package site.tddmanifesto.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test void should_return_zero_for_empty_string() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test void should_return_number_if_single_input() {
        assertEquals(5, stringCalculator.add(Integer.toString(5)));
    }

    @Test void should_return_sum_if_double_input() {
        assertEquals(9, stringCalculator.add(3+","+6));
    }

   @Test void should_return_sum_of_multiple_inputs() {
        assertEquals(17, stringCalculator.add("3,1,7,2,4"));
    }

}
