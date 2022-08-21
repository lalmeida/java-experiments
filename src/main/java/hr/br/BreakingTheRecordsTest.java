package hr.br;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreakingTheRecordsTest {

    @Test
    public void growingScores() {
        assertEquals(List.of(4, 0), Result.breakingRecords(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void shrinkingScores() {
        assertEquals(List.of(0, 4), Result.breakingRecords(List.of(5, 4, 3, 2, 1)));
    }

    @Test
    public void oneGame() {
        assertEquals(List.of(0, 0), Result.breakingRecords(List.of(5)));
    }

    @Test
    public void mixedScores() {
        assertEquals(List.of(2, 2), Result.breakingRecords(List.of(100, 90, 80, 110, 105, 120, 95, 101)));
    }


}