package site.tddmanifesto.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FizzBuzzTest {

    FizzBuzz fizzBuzz = new FizzBuzz();

    final static Object[] expectedResults = {
            0, "0", 1, "1", 2, "2", 3, "fizz", 4, "4", 5, "buzz", 6, "fizz", 7, "7", 8, "8", 9, "fizz",
            10,"buzz", 11, "11", 12, "fizz", 13, "13", 14, "14", 15, "fizzbuzz"
    };

    @Test
    void shouldMatchTableOfExpectedResults() {
        int numberOfTests  = expectedResults.length / 2;
        // Todo: make unit tests independent so that all of them run even if one fails
        for (int i = 0; i < numberOfTests; i++ )
            assertEquals(expectedResults[(i*2)+1], fizzBuzz.format((Integer) expectedResults[i*2]));
    }


}
